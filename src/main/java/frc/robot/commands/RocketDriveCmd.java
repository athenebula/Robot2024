package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;

public class RocketDriveCmd extends Command {

    public RocketDriveCmd(DriveSubsystem driveSubsystem){
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        
        double trigger = RobotContainer.xboxController.getRightTriggerAxis() - RobotContainer.xboxController.getLeftTriggerAxis();
        double speed = trigger * (1 - 2 * (Math.abs(RobotContainer.xboxController.getLeftX())));

            if(RobotContainer.xboxController.getLeftX() > 0.1) {
                DriveSubsystem.driveBackLeftMotor.set(speed);
                DriveSubsystem.driveBackRightMotor.set(trigger);
                DriveSubsystem.driveTopLeftMotor.set(speed);
                DriveSubsystem.driveTopRightMotor.set(trigger);

            } else if(RobotContainer.xboxController.getLeftX() < -0.1) {
                DriveSubsystem.driveBackLeftMotor.set(trigger);
                DriveSubsystem.driveBackRightMotor.set(speed);
                DriveSubsystem.driveTopLeftMotor.set(trigger);
                DriveSubsystem.driveTopRightMotor.set(speed);

            } else {
                DriveSubsystem.driveBackLeftMotor.set(trigger);
                DriveSubsystem.driveBackRightMotor.set(trigger);
                DriveSubsystem.driveTopLeftMotor.set(trigger);
                DriveSubsystem.driveTopRightMotor.set(trigger);
            }
    }

    @Override
    public void end(boolean interrupted) {
        DriveSubsystem.driveBackLeftMotor.set(0);
        DriveSubsystem.driveBackRightMotor.set(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
