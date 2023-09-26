// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
  
  WPI_VictorSPX m_leftFrontal = new WPI_VictorSPX(4);
  WPI_VictorSPX m_leftBack = new WPI_VictorSPX(1);
  WPI_VictorSPX m_rightFrontal = new WPI_VictorSPX(2);
  WPI_VictorSPX m_rightBack = new WPI_VictorSPX(3);

  WPI_VictorSPX m_estica = new WPI_VictorSPX(5);
  WPI_VictorSPX m_elevador = new WPI_VictorSPX(6);
  WPI_VictorSPX m_garra = new WPI_VictorSPX(7);

  //FIM DE CURSO
  DigitalInput travaSuperior = new DigitalInput(8);
  DigitalInput travaInferior = new DigitalInput(0);

  MotorControllerGroup m_left = new MotorControllerGroup(m_leftBack, m_leftFrontal);
  MotorControllerGroup m_right = new MotorControllerGroup(m_rightBack, m_rightFrontal);

  DifferentialDrive chassi =  new DifferentialDrive(m_left, m_right);

  private XboxController m_driveStick;
  private XboxController m_garraStick;

  private Timer timer = new Timer();

  @Override
  public void robotInit() {
    SmartDashboard.putString("Iniciado: ", "true");

    m_rightBack.setInverted(true);
    m_rightFrontal.setInverted(true);
    m_leftBack.setInverted(false);
    m_leftFrontal.setInverted(false);
    
    m_estica.setNeutralMode(NeutralMode.Brake);
    m_garra.setNeutralMode(NeutralMode.Brake);

    m_driveStick = new XboxController(0);
    m_garraStick = new XboxController(1);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    timer.restart();
    timer.start();
  }

  @Override
  public void autonomousPeriodic() {
    if(timer.get()<3){
      chassi.tankDrive(0.5, 0.5);
    } else if (timer.get()>3 && timer.get()<6){
      chassi.tankDrive(0.2, -0.2);
    } else{
      chassi.tankDrive(0, 0);
    }
  }

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    //FIM DE CURSO
    boolean estadoFimCursoSuperior = travaSuperior.get();
    boolean estadoFimCursoInferior = travaInferior.get();
    double v_elevador = m_garraStick.getRightY();

    SmartDashboard.putBoolean("travaSuperior: ", estadoFimCursoSuperior);
    SmartDashboard.putBoolean("travaInferior: ", estadoFimCursoInferior);
    
    if (v_elevador < 0 && !estadoFimCursoInferior){
      m_elevador.set(v_elevador);
    } else{
      m_elevador.set(0);
    }

    if(v_elevador > 0 && !estadoFimCursoSuperior){
      m_elevador.set(v_elevador);
    } else{
      m_elevador.set(0);
    }

    //MOVIMENTACAO
    chassi.tankDrive(-m_driveStick.getLeftY(), -m_driveStick.getRightY());

    //GARRA
    m_garra.set(m_garraStick.getRightTriggerAxis());
    m_garra.set(-m_garraStick.getLeftTriggerAxis());
    
    if(m_garraStick.getXButton()) {
      m_estica.set(1);
    } else {
      m_estica.set(0);
    }

    if(m_garraStick.getBButton()) {
      m_estica.set(-1);
    } else {
      m_estica.set(0);
    }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
