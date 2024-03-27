package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;

public class TankDriveCmd extends Command {

    public TankDriveCmd(){
        addRequirements(RobotContainer.driveSubsystem);
    }

    @Override
    public void initialize() {
        DriveSubsystem.m_gyro.calibrate();
    }

    @Override
    public void execute() {

        double leftSpeed = RobotContainer.xboxController.getLeftY();
        double rightSpeed = RobotContainer.xboxController.getRightY();

        RobotContainer.driveSubsystem.tankDrive(leftSpeed, rightSpeed);

        // System.out.println(Math.round(DriveSubsystem.m_gyro.getAngle()));
        // System.out.println(Math.round(DriveSubsystem.leftEncoder.get()));

        DriveSubsystem.servoCam.set(0.5 - DriveSubsystem.m_gyro.getAngle()/175);

    // RobotContainer.xboxController
    //     .a()
    //     .and(RobotContainer.xboxController.rightBumper())
    //     .whileTrue(RobotContainer.driveSubsystem.sysIdQuasistatic(SysIdRoutine.Direction.kForward));
    // RobotContainer.xboxController
    //     .b()
    //     .and(RobotContainer.xboxController.rightBumper())
    //     .whileTrue(RobotContainer.driveSubsystem.sysIdQuasistatic(SysIdRoutine.Direction.kReverse));
    // RobotContainer.xboxController
    //     .x()
    //     .and(RobotContainer.xboxController.rightBumper())
    //     .whileTrue(RobotContainer.driveSubsystem.sysIdDynamic(SysIdRoutine.Direction.kForward));
    // RobotContainer.xboxController
    //     .y()
    //     .and(RobotContainer.xboxController.rightBumper())
    //     .whileTrue(RobotContainer.driveSubsystem.sysIdDynamic(SysIdRoutine.Direction.kReverse));
    }

    @Override
    public void end(boolean interrupted) {
        RobotContainer.driveSubsystem.tankDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
