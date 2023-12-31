// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

public class Robot extends TimedRobot {

  WPI_VictorSPX m_estica = new WPI_VictorSPX(5);
  public XboxController controllerGarra;

  @Override
  public void robotInit() {
    
    controllerGarra = new XboxController(0);
    m_estica.setNeutralMode(NeutralMode.Brake);
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
    if (controllerGarra.getAButton()) {
      m_estica.set(1);
    } else if (controllerGarra.getBButton()) {
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
