package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class TankDriveCmd extends Command {

    public TankDriveCmd(){
        addRequirements(RobotContainer.driveSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double leftSpeed = RobotContainer.xboxController.getLeftY();
        double rightSpeed = RobotContainer.xboxController.getRightY();

        RobotContainer.driveSubsystem.tankDrive(leftSpeed, rightSpeed);
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
