// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralReleaser extends SubsystemBase {
  /*
   * Creates a Sparkmax Object so we can control the Sparkmax that is plugged into the motor
   * The name of the object is dropper, the ID is 5 and the motor is brushed (it's a CIM motor)
   */
  SparkMax Dropper = new SparkMax(5,MotorType.kBrushless);
  SparkClosedLoopController coralController = new Dropper.SparkClosedLoopController();
  /*
   *   Hint: Objects and variables are declared here
   */


  public CoralReleaser() {
    /*
     * Hint: This is where the NEO's and Sparkmax's settings and/or configurations are set up.
     * Anything that interacts with the NEOs and Sparkmax goes here too
     */
  
    // For Elastic and Advtange Scope
    SmartDashboard.putNumber("PID/kP", kP);
    SmartDashboard.putNumber("PID/kI", kI);
    SmartDashboard.putNumber("PID/kD", kD);
    SmartDashboard.putNumber("Target RPM", targetRPM);
  }

  /*
   * Hint: New Commands and Methods go here
   */

  @Override
  // This method will be called once per scheduler run
  public void periodic() {
    // For Elastic and Advtange Scope
    double newP = SmartDashboard.getNumber("PID/kP", kP);
    double newI = SmartDashboard.getNumber("PID/kI", kI);
    double newD = SmartDashboard.getNumber("PID/kD", kD);
    double newTargetRPM = SmartDashboard.getNumber("Target RPM", targetRPM);
   
    SmartDashboard.putNumber("Dropper Setpoint", targetRPM);   // Setpoint
    SmartDashboard.putNumber("Dropper Velocity", coralEncoder.getVelocity()); // Actual velocity
 
  }
}
