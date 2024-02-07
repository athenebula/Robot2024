
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

    @Override
    public void initialize() {
        DriveSubsystem.m_gyro.reset();
    }

    @Override
    public void execute() {
        if (Math.abs(DriveSubsystem.m_gyro.getAngle()) <= 3) {
            driveSubsystem.driveBackLeftMotor.set(AutoConstants.leftSlow - (DriveSubsystem.m_gyro.getAngle()) / 15);
            driveSubsystem.driveBackRightMotor.set(AutoConstants.rightSlow - (DriveSubsystem.m_gyro.getAngle()) / 15);
           } else if (Math.abs(DriveSubsystem.m_gyro.getAngle()) < 10) {
            if (DriveSubsystem.m_gyro.getAngle() > 0) {
             driveSubsystem.driveBackLeftMotor.set(AutoConstants.leftSlow);
             driveSubsystem.driveBackRightMotor.set(1.1 * AutoConstants.rightSlow);
            } else if (DriveSubsystem.m_gyro.getAngle() < 0) {
             driveSubsystem.driveBackLeftMotor.set(1.1 * AutoConstants.leftSlow);
             driveSubsystem.driveBackRightMotor.set(AutoConstants.rightSlow);
            }
           } else
            if (DriveSubsystem.m_gyro.getAngle() > 0) {
             while (DriveSubsystem.m_gyro.getAngle() > 10) {
              driveSubsystem.driveBackLeftMotor.set(-AutoConstants.rotateSpeed);
              driveSubsystem.driveBackRightMotor.set(-AutoConstants.rotateSpeed);
             }
            while (DriveSubsystem.m_gyro.getAngle() > 0) {
             driveSubsystem.driveBackLeftMotor.set(-AutoConstants.rotateSpeedSlow);
             driveSubsystem.driveBackRightMotor.set(-AutoConstants.rotateSpeedSlow);
            }
            while (DriveSubsystem.m_gyro.getAngle() < 0) {
             driveSubsystem.driveBackLeftMotor.set(AutoConstants.rotateSpeedSlow);
             driveSubsystem.driveBackRightMotor.set(AutoConstants.rotateSpeedSlow);
            }
           } else {
            while (DriveSubsystem.m_gyro.getAngle() < -10) {
             driveSubsystem.driveBackLeftMotor.set(AutoConstants.rotateSpeed);
             driveSubsystem.driveBackRightMotor.set(AutoConstants.rotateSpeed);
            }
            while (DriveSubsystem.m_gyro.getAngle() < 0) {
             driveSubsystem.driveBackLeftMotor.set(AutoConstants.rotateSpeedSlow);
             driveSubsystem.driveBackRightMotor.set(AutoConstants.rotateSpeedSlow);
            }
            while (DriveSubsystem.m_gyro.getAngle() > 0) {
             driveSubsystem.driveBackLeftMotor.set(-AutoConstants.rotateSpeedSlow);
             driveSubsystem.driveBackRightMotor.set(-AutoConstants.rotateSpeedSlow);
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
