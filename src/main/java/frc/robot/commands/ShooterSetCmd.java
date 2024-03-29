package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OIConstants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterSetCmd extends Command {

    private final ShooterSubsystem shooterSubsystem;
    public final boolean open;

    public ShooterSetCmd(ShooterSubsystem shooterSubsystem, boolean open) {
        this.open = open;
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if(RobotContainer.joystick1.getRawButton(OIConstants.kShooterCloseButtonIdx)) {
            shooterSubsystem.setPosition(false);
        } else {
            shooterSubsystem.setPosition(open);
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
