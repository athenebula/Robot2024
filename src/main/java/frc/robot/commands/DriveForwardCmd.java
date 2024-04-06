
package frc.robot.commands;

import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.Command;

public class DriveForwardCmd extends Command {

    public DriveForwardCmd(DriveSubsystem driveSubsystem) {
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        DriveSubsystem.driveBackLeftMotor.set(ControlMode.PercentOutput, DriveConstants.kAutoDriveForwardSpeed);
        DriveSubsystem.driveBackRightMotor.set(ControlMode.PercentOutput, DriveConstants.kAutoDriveForwardSpeed);
        DriveSubsystem.driveTopLeftMotor.set(ControlMode.PercentOutput, DriveConstants.kAutoDriveForwardSpeed);
        DriveSubsystem.driveTopRightMotor.set(ControlMode.PercentOutput, DriveConstants.kAutoDriveForwardSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        DriveSubsystem.driveBackLeftMotor.set(0);
        DriveSubsystem.driveBackRightMotor.set(0);
        DriveSubsystem.driveTopLeftMotor.set(0);
        DriveSubsystem.driveTopRightMotor.set(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
