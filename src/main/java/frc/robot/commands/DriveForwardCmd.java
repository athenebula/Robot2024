
package frc.robot.commands;

import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class DriveForwardCmd extends Command {
    private final DriveSubsystem driveSubsystem;

    public DriveForwardCmd(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        DriveSubsystem.m_gyro.calibrate();
    }

    @Override
    public void execute() {
        driveSubsystem.differentialDrive.tankDrive(DriveConstants.kAutoDriveForwardSpeed, DriveConstants.kAutoDriveForwardSpeed);

        Commands.waitSeconds(DriveConstants.kAutoDriveTime);

        driveSubsystem.differentialDrive.tankDrive(0, 0);
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.driveBackLeftMotor.set(0);
        driveSubsystem.driveBackRightMotor.set(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
