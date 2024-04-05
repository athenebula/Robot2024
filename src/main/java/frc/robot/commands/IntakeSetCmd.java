package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OIConstants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeSetCmd extends Command {

    private final IntakeSubsystem intakeSubsystem;
    public final boolean open;

    public IntakeSetCmd(IntakeSubsystem intakeSubsystem, boolean open) {
        this.open = open;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if(RobotContainer.joystick1.getRawButton(OIConstants.kIntakeCloseButtonIdx)) {
            intakeSubsystem.setPosition(false);
        } else {
            intakeSubsystem.setPosition(true);
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
