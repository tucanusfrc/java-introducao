package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

  WPI_VictorSPX m_leftFrontal = new WPI_VictorSPX(4);
  WPI_VictorSPX m_leftBack = new WPI_VictorSPX(1);
  WPI_VictorSPX m_rightFrontal = new WPI_VictorSPX(2);
  WPI_VictorSPX m_rightBack = new WPI_VictorSPX(3);

  WPI_VictorSPX m_garra = new WPI_VictorSPX(7);
  WPI_VictorSPX m_elevador = new WPI_VictorSPX(6);
  WPI_VictorSPX m_estica = new WPI_VictorSPX(5);

  MotorControllerGroup m_left = new MotorControllerGroup(m_leftBack, m_leftFrontal);
  MotorControllerGroup m_right = new MotorControllerGroup(m_rightBack, m_rightFrontal);

  DifferentialDrive chassi = new DifferentialDrive(m_left, m_right);

  public XboxController controllerDrive;
  public XboxController controllerGarra;

  DigitalInput travaSuperior = new DigitalInput(8);
  DigitalInput travaInferior = new DigitalInput(0);

  @Override
  public void robotInit() {

    m_rightBack.setInverted(true);
    m_rightFrontal.setInverted(true);
    m_leftBack.setInverted(false);
    m_leftFrontal.setInverted(false);
    
    m_estica.setNeutralMode(NeutralMode.Brake);
    m_garra.setNeutralMode(NeutralMode.Brake);

    controllerDrive = new XboxController(0);
    controllerGarra = new XboxController(1);

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

    //DRIVE
    chassi.tankDrive(-(controllerDrive.getLeftY() * 0.7), -(controllerDrive.getRightY() * 0.7));

    //GARRA
    if(controllerGarra.getLeftTriggerAxis() > 0){
      m_garra.set(-controllerGarra.getLeftTriggerAxis());
    } 
    else if (controllerGarra.getRightTriggerAxis() > 0) {
      m_garra.set(controllerGarra.getRightTriggerAxis());
    } 
    else {
      m_garra.set(0);
    }

    //ESTICA
    if (controllerGarra.getAButton()) {
      m_estica.set(1);
    } 
    else if (controllerGarra.getBButton()) {
      m_estica.set(-1);
    } 
    else {
      m_estica.set(0);
    }

    //ELEVADOR
    SmartDashboard.putBoolean("Trava Superior: ", travaSuperior.get());
    SmartDashboard.putBoolean("Trava Inferior: ", travaInferior.get());

    if(controllerGarra.getRightY()>0 && !travaSuperior.get()) {
      m_elevador.set(controllerGarra.getRightY());
    } 
    else if (controllerGarra.getRightY()<0 && !travaInferior.get()) {
      m_elevador.set(controllerGarra.getRightY());
    } 
    else {
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
