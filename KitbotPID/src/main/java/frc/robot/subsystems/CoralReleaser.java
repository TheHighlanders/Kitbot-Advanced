// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralReleaser extends SubsystemBase {

double kP = 0.0;
double kI = 0.0;
double kD = 0.0;
double targetRPM = 0.0;
  /*

   * Creates a Sparkmax Object so we can control the Sparkmax that is plugged into the motor
   * The name of the object is dropper, the ID is 5 and the motor is brushed (it's a CIM motor)
   */
  private SparkMax Dropper = new SparkMax(5,MotorType.kBrushless);
  /*
   *   Hint: Objects and variables are declared here
   */
  Dropper.SparkClosedLoopController coralController = new Dropper.SparkClosedLoopController();
    public CoralReleaser() {
      RelativeEncoder coralEncoder;
      coralEncoder = Dropper.getEncoder();

    /*
     * Hint: This is where the NEO's and Sparkmax's settings and/or configurations are set up.
     * Anything that interacts with the NEOs and Sparkmax goes here too
  
     */
    SparkMaxConfig DropperConfig = new SparkMaxConfig();
     // Set PID gains
    DropperConfig.closedLoop
  .p(kP).i(kI).d(kD);
    
    
    CoralReleaser.configure(coralEncoder, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    
    // Initialize the motor (Flex/MAX are setup the same way)
    
   
    



  
    // For Elastic and Advtange Scope
    // SmartDashboard.putNumber("PID/kP", kP);
    // SmartDashboard.putNumber("PID/kI", kI);
    // SmartDashboard.putNumber("PID/kD", kD);
    // SmartDashboard.putNumber("Target RPM", targetRPM);
  }

  /*
   * Hint: New Commands and Methods go here
   */
   
public Command PIDCMD(double newtargetRPM){
  return runOnce(
    () -> {
      coralController.setReference(newtargetRPM, ControlType.kVelocity);
    }
  );
}

  @Override
  // This method wll be called once per scheduler run
  public void periodic() {
    // For Elastic and Advtange Scope
    // double newP = SmartDashboard.getNumber("PID/kP", kP);
    // double newI = SmartDashboard.getNumber("PID/kI", kI);
    // double newD = SmartDashboard.getNumber("PID/kD", kD);
    // double newTargetRPM = SmartDashboard.getNumber("Target RPM", targetRPM);
   
    // SmartDashboard.putNumber("Dropper Setpoint", targetRPM);   // Setpoint
    // SmartDashboard.putNumber("Dropper Velocity", coralEncoder.getVelocity()); // Actual velocity
 
  }
 
}


