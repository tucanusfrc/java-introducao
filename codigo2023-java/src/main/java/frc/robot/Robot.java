// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  CANSparkMax m_leftFrontal = new CANSparkMax(4, MotorType.kBrushless);
  CANSparkMax m_leftBack = new CANSparkMax(1, MotorType.kBrushless);
  CANSparkMax m_rightFrontal = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkMax m_rightBack = new CANSparkMax(3, MotorType.kBrushless);

  CANSparkMax m_estica = new CANSparkMax(5, MotorType.kBrushless);
  CANSparkMax m_elevador = new CANSparkMax(6, MotorType.kBrushless);
  CANSparkMax m_garra = new CANSparkMax(7, MotorType.kBrushless);

  MotorControllerGroup m_left = new MotorControllerGroup(m_leftBack, m_leftFrontal);
  MotorControllerGroup m_right = new MotorControllerGroup(m_rightBack, m_rightFrontal);

  DifferentialDrive chassi =  new DifferentialDrive(m_left, m_right);

  @Override
  public void robotInit() {
   
    //m_rightBack.setInverted(true);
    //m_rightFrontal.setInverted(true);


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
  public void teleopPeriodic() {}

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
