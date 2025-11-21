// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralReleaserSIM extends SubsystemBase {
  /** Creates a new CoralReleaserSIM. */
    /*
   * Ignore: These are for the robot sim
   */
  private double VelocitySim = 0.0;
  private double SetpointSim = 0.0;
  private double integral = 0.0;
  private double pervError = 0.0;
  private final double looptime = 0.02;
  private final double motorstuff = 0.1;
  // end of Sim set up

  private double kP = 0.0;
  private double kI = 0.0;
  private double kD = 0.0;

  public CoralReleaserSIM() {
    SmartDashboard.putNumber("PID/kP", kP);
    SmartDashboard.putNumber("PID/kI", kI);
    SmartDashboard.putNumber("PID/kD", kD);
    SmartDashboard.putNumber("Target RPM", 0.0);
  }

    public Command pidCMD(double targetRPM) {
    return runOnce(() -> {
      SetpointSim = targetRPM;
    });
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    kP = SmartDashboard.getNumber("PID/kP", kP);
    kI = SmartDashboard.getNumber("PID/kI", kI);
    kD = SmartDashboard.getNumber("PID/kD", kD);

        /*
     * Ignore: Sim Set up
     */
    SetpointSim = SmartDashboard.getNumber("Target RPM", SetpointSim);
    double error = SetpointSim - VelocitySim;
    integral += error * looptime;
    double math = (error - pervError) / looptime;
    pervError = error;
    double output = kP * error + kI * integral + kD * math;
    VelocitySim += (output - VelocitySim) * motorstuff;
    SmartDashboard.putNumber("Setpoint", SetpointSim);
    SmartDashboard.putNumber("Velocity", VelocitySim);
    SmartDashboard.putNumber("PID Output", output);
    // end of Sim set up
  }
}
