
package frc.robot.commands;

import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class DriveForwardCmd extends Command {
    private final DriveSubsystem driveSubsystem;
    private final double distance;

    public DriveForwardCmd(DriveSubsystem driveSubsystem, double distance) {
        this.driveSubsystem = driveSubsystem;
        this.distance = driveSubsystem.getEncoderMeters() + distance;
        addRequirements(driveSubsystem);
    }

// https://docs.wpilib.org/en/stable/docs/software/kinematics-and-odometry/differential-drive-odometry.html

    @Override
    public void initialize() {
        DriveSubsystem.m_gyro.calibrate();
    }

    @Override
    public void execute() {
        if (Math.abs(DriveSubsystem.m_gyro.getAngle()) <= 3) {
            driveSubsystem.driveBackLeftMotor.set(AutoConstants.kLeftSlow - (DriveSubsystem.m_gyro.getAngle()) / 15);
            driveSubsystem.driveBackRightMotor.set(AutoConstants.kRightSlow - (DriveSubsystem.m_gyro.getAngle()) / 15);
           } else if (Math.abs(DriveSubsystem.m_gyro.getAngle()) < 10) {
            if (DriveSubsystem.m_gyro.getAngle() > 0) {
             driveSubsystem.driveBackLeftMotor.set(AutoConstants.kLeftSlow);
             driveSubsystem.driveBackRightMotor.set(1.1 * AutoConstants.kRightSlow);
            } else if (DriveSubsystem.m_gyro.getAngle() < 0) {
             driveSubsystem.driveBackLeftMotor.set(1.1 * AutoConstants.kLeftSlow);
             driveSubsystem.driveBackRightMotor.set(AutoConstants.kRightSlow);
            }
           } else
            if (DriveSubsystem.m_gyro.getAngle() > 0) {
             while (DriveSubsystem.m_gyro.getAngle() > 10) {
              driveSubsystem.driveBackLeftMotor.set(-AutoConstants.kRotateSpeed);
              driveSubsystem.driveBackRightMotor.set(-AutoConstants.kRotateSpeed);
             }
            while (DriveSubsystem.m_gyro.getAngle() > 0) {
             driveSubsystem.driveBackLeftMotor.set(-AutoConstants.kRotateSpeedSlow);
             driveSubsystem.driveBackRightMotor.set(-AutoConstants.kRotateSpeedSlow);
            }
            while (DriveSubsystem.m_gyro.getAngle() < 0) {
             driveSubsystem.driveBackLeftMotor.set(AutoConstants.kRotateSpeedSlow);
             driveSubsystem.driveBackRightMotor.set(AutoConstants.kRotateSpeedSlow);
            }
           } else {
            while (DriveSubsystem.m_gyro.getAngle() < -10) {
             driveSubsystem.driveBackLeftMotor.set(AutoConstants.kRotateSpeed);
             driveSubsystem.driveBackRightMotor.set(AutoConstants.kRotateSpeed);
            }
            while (DriveSubsystem.m_gyro.getAngle() < 0) {
             driveSubsystem.driveBackLeftMotor.set(AutoConstants.kRotateSpeedSlow);
             driveSubsystem.driveBackRightMotor.set(AutoConstants.kRotateSpeedSlow);
            }
            while (DriveSubsystem.m_gyro.getAngle() > 0) {
             driveSubsystem.driveBackLeftMotor.set(-AutoConstants.kRotateSpeedSlow);
             driveSubsystem.driveBackRightMotor.set(-AutoConstants.kRotateSpeedSlow);
            }
           }
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.driveBackLeftMotor.set(0);
        driveSubsystem.driveBackRightMotor.set(0);
    }

    @Override
    public boolean isFinished() {
        if (driveSubsystem.getEncoderMeters() > distance)
            return true;
        else
            return false;
    }
}
