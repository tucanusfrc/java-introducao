// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

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

  CANSparkMax m_garra = new CANSparkMax(7, MotorType.kBrushless);
  
  CANSparkMax m_leftFrontal = new CANSparkMax(4, MotorType.kBrushless);
  CANSparkMax m_leftBack = new CANSparkMax(1, MotorType.kBrushless);
  CANSparkMax m_rightFrontal = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkMax m_rightBack = new CANSparkMax(3, MotorType.kBrushless);

  CANSparkMax m_estica = new CANSparkMax(5, MotorType.kBrushless);
  CANSparkMax m_elevador = new CANSparkMax(6, MotorType.kBrushless);

  //FIM DE CURSO
  DigitalInput travaSuperior = new DigitalInput(0);
  DigitalInput travaInferior = new DigitalInput(1);

  MotorControllerGroup m_left = new MotorControllerGroup(m_leftBack, m_leftFrontal);
  MotorControllerGroup m_right = new MotorControllerGroup(m_rightBack, m_rightFrontal);

  DifferentialDrive chassi =  new DifferentialDrive(m_left, m_right);

  private XboxController m_driveStick;
  private XboxController m_garraStick;

  @Override
  public void robotInit() {

    m_rightBack.setInverted(true);
    m_rightFrontal.setInverted(true);
    m_leftBack.setInverted(false);
    m_leftFrontal.setInverted(false);

    m_estica.setIdleMode(IdleMode.kBrake);
    m_elevador.setIdleMode(IdleMode.kBrake);
    m_garra.setIdleMode(IdleMode.kBrake); 

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
