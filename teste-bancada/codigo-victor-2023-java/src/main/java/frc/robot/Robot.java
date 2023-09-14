// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  WPI_VictorSPX m_garra = new WPI_VictorSPX(7);

  WPI_VictorSPX m_leftFrontal = new WPI_VictorSPX(4);
  WPI_VictorSPX m_leftBack = new WPI_VictorSPX(1);
  WPI_VictorSPX m_rightFrontal = new WPI_VictorSPX(2);
  WPI_VictorSPX m_rightBack = new WPI_VictorSPX(3);

  WPI_VictorSPX m_estica = new WPI_VictorSPX(5);
  WPI_VictorSPX m_elevador = new WPI_VictorSPX(6);

  //FIM DE CURSO
  DigitalInput travaSuperior = new DigitalInput(0);
  DigitalInput travaInferior = new DigitalInput(1);

  MotorControllerGroup m_left = new MotorControllerGroup(m_leftBack, m_leftFrontal);
  MotorControllerGroup m_right = new MotorControllerGroup(m_rightBack, m_rightFrontal);

  DifferentialDrive chassi = new DifferentialDrive(m_left, m_right);

  private XboxController m_driveStick;
  private XboxController m_garraStick;

  @Override
  public void robotInit() {

    m_rightBack.setInverted(true);
    m_rightFrontal.setInverted(true);
    m_leftBack.setInverted(false);
    m_leftFrontal.setInverted(false);

    SmartDashboard.putBoolean("Inverted: ", m_rightFrontal.getInverted());
    
    m_elevador.setNeutralMode(NeutralMode.Brake);
    m_garra.setNeutralMode(NeutralMode.Brake);

    m_driveStick = new XboxController(0);
    m_garraStick = new XboxController(1);

    //CLASSE FIM DE CURSO - Procurar
    //boolean travaSuperior;
    //boolean travaInferior;
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    //FIM DE CURSO
    boolean estadoFimCursoSuperior = travaSuperior.get();
    boolean estadoFimCursoInferior = travaInferior.get();

    //MOVIMENTACAO
    chassi.tankDrive(m_driveStick.getLeftY(), m_driveStick.getRightY());

    //GARRA
    m_elevador.set(m_garraStick.getRightY()); //Sug: Limitar velocidade multiplicando por decimais.

    m_garra.set(m_garraStick.getRightTriggerAxis()*0.6);
    m_garra.set(-m_garraStick.getLeftTriggerAxis()*0.6);
    
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
