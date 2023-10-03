package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {

  WPI_VictorSPX m_garra = new WPI_VictorSPX(7);
  public XboxController controllerGarra;

  @Override
  public void robotInit() {
    controllerGarra = new XboxController(0);

  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    SmartDashboard.putNumber("ID garra: ", 7);
  }

  @Override
  public void teleopPeriodic() {

    if(controllerGarra.getLeftTriggerAxis() > 0){
    m_garra.set(-controllerGarra.getLeftTriggerAxis());
  } 
    else if (controllerGarra.getRightTriggerAxis() > 0) {
    m_garra.set(controllerGarra.getRightTriggerAxis());
  } else {
    m_garra.set(0);
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
