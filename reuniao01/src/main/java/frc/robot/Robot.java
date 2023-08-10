package frc.robot;


// PACOTES
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {

// ATRIBUTOS DOS SPARKMAX E SEUS IDS
  private final int Drive0ID = 2;
  private final int Drive1ID = 3;
  private final int Drive2ID = 4;
  private final int Drive3ID = 5;
  private final int ArmID = 6;

// CLASSE PARA DEFINIR ENCODER E SUA PORTA DIO
  DutyCycleEncoder armEncoder = new DutyCycleEncoder(8);

  Pigeon pigeon = new Pigeon(7);

// DEFINIÇÃO DO OBJETO PID
  PIDController pidController = new PIDController(0.025, 0.012, 0);
  pidController.Setsetpoint(4); //ponto de ajuste para o pidController

// DEFINIÇÃO DO OBJETO PID UTILIZADO PARA MOVIMENTACAO
  PIDController driveTrainPIDController = new PIDController(0.2, 0.05, 0.1);

// DEFINIÇÃO DOS SPARKMAX UTILIZANDO OS ATRIBUTOS COM AS ID's
  CANSparkMax Drive0 = new CANSparkMax(Drive0ID);
  CANSparkMax Drive1 = new CANSparkMax(Drive1ID);
  CANSparkMax Drive2 = new CANSparkMax(Drive2ID);
  CANSparkMax Drive3 = new CANSparkMax(Drive3ID);
  CANSparkMax Arm = new CANSparkMax(ArmID);

  // AGRUPAMENTO DOS MOTORES EM RIGHT E LEFT
  MotorControllerGroup leftDrive = new MotorControllerGroup(Drive0, Drive1);
  MotorControllerGroup rightDrive = new MotorControllerGroup(Drive2, Drive3);

  /* CLASSE UTILIZADA PARA FAZER DIVERSAS FUNCÕES DE MOVIMENTAÇÃO DOS MOTORES.
   * Caso necessário, inverta os motores antes de instânciar o objeto.
   */
  DifferentialDrive chassis = new DifferentialDrive(leftDrive, rightDrive);

  // DECLARACAO DOS JOYSTICKS E SEUS ID's
  XboxController controllerDrive = new XboxController(0);
  XboxController controllerArm = new XboxController(1);
  GenericHID controllerGeneric = new GenericHID(2); //Controle genérico.

  // INVERTER VALORES DOS SPARKMAX
  public void reverseSparkMax(){
    Drive2.setInverted(true);
    Drive3.setInverted(true);
  }

  
  // UTILIZACAO DO SENSOR ENCODER INTEGRADO AO PID
  public void goToEncoder(double distance){
    double encoderVal = Drive0.getAlternateEncoder().getPosition(); //pega os valores fornecidos pelo encoder.
    driveTrainPIDController.setSetpoint(encoderVal + distance); //define o ponto de ajuste do pid para o valor do encoder + distancia necessária
    driveTrainPIDController.setTolerance(3); //tolerância para que o pid funcione
    while (!driveTrainPIDController.isAtSetpoint()){ // verifica se o movimento está de acordo com o ponto de ajuste do pid.
      encoderVal = Drive0.getAlternateEncoder().getPosition(); //sobrescreve os valores do encoder até que estejam certos.
      chassis.arcadeDrive(driveTrainPIDController.calculate(encoderVal)); //.calculate -> retorna a próxima saída do controlador PID.
    }
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   */

  @Override
  public void autonomousInit() {
    
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    goToEncoder(100); //fornece 100 counts(unidade de medida encoder) para o método.

    /* getAbsolutePosition -> fornecerá uma posição absoluta do encoder em relação ao último reset. 
     * 
    */
    Arm.set(pidController.calculate(armEncoder.getAbsolutePosition()));

  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    reverseSparkMax();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
  // DECLARACAO DOS TIPOS DE MOVIMENTO (OS DOIS TIPOS - USAR SOMENTE UM)
    chassis.tankDrive(controllerDrive.getLeftY(), controllerDrive.getRightY()); 
    chassis.arcadeDrive(pidController.calculate(pigeon.getPitch()), 0); 

  // MOVIMENTACAO DO MOTOR UTILIZANDO OS JOYSTICKS E SEUS PARÂMETROS
    Arm.set(controllerArm.getLeftY());
    Arm.set(pidController.calculate(armEncoder.getAbsolutePosition())); //.set -> O motor irá se movimentar de acordo com os parâmetros do método(?) 
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}