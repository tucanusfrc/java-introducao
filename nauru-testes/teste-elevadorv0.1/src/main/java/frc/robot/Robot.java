package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

  WPI_VictorSPX m_elevador = new WPI_VictorSPX(6);

  DigitalInput travaSuperior = new DigitalInput(8);
  DigitalInput travaInferior = new DigitalInput(0);

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
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    SmartDashboard.putBoolean("Trava Superior: ", travaSuperior.get());
    SmartDashboard.putBoolean("Trava Inferior: ", travaInferior.get());
    
    if(controllerGarra.getRightY()>0 && !travaSuperior.get()) {
      m_elevador.set(controllerGarra.getRightY());
    } else if (controllerGarra.getRightY()<0 && !travaInferior.get()) {
      m_elevador.set(controllerGarra.getRightY());
    } else {
      m_elevador.set(0);
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
