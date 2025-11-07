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
  private SparkMax Dropper = new SparkMax(5,MotorType.kBrushless);
  /*
   *   Hint: Objects and variables are declared here
   */
  SparkClosedLoopController CoralController = new Dropper.SparkClosedLoopController();
  RelativeEncoder coralEncoder;
  double KP = 0.1;
  double KI = 0.0;
  double KD = 0.0;
 

  public CoralReleaser() {
    
     // Hint: This is where the NEO's and Sparkmax's settings and/or configurations are set up.
      SparkMaxConfig DropperConfig = new SparkMaxConfig();
  coralEncoder = Dropper.getEncoder();
    
   DropperConfig.closedLoop
    .p(KP)
    .i(KI)
    .d(KD);
    

     Dropper.configure(DropperConfig, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
  

     //Anything that interacts with the NEOs and Sparkmax goes here too
  

    // For Elastic and Advtange Scope
    SmartDashboard.putNumber("PID/kP", KP);
    SmartDashboard.putNumber("PID/kI", KI);
    SmartDashboard.putNumber("PID/kD", KD);
    //SmartDashboard.putNumber("Target RPM", targetRPM);
  }

  
    //Hint: New Commands and Methods go here
    public Command PIDCMD(double targetRPM){
        return runOnce(
        ()-> {
        CoralController.setReference(targetRPM, ControlType.kVelocity);
      });
    }
    
    }
   

  @Override
  // This method will be called once per scheduler run
  public void periodic() {
    // For Elastic and Advtange Scope
    double newP = SmartDashboard.getNumber("PID/kP", KP);
    double newI = SmartDashboard.getNumber("PID/kI", KI);
    double newD = SmartDashboard.getNumber("PID/kD", KD);
    //double newTargetRPM = SmartDashboard.getNumber("Target RPM", targetRPM);
   
    //SmartDashboard.putNumber("Dropper Setpoint", targetRPM);   // Setpoint
    SmartDashboard.putNumber("Dropper Velocity", coralEncoder.getVelocity()); // Actual velocity
 
  }
}
