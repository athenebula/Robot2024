package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OIConstants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberSetCmd extends Command {

    private final ClimberSubsystem climberSubsystem;
    public final boolean open;

    public ClimberSetCmd(ClimberSubsystem climberSubsystem, boolean open) {
        this.open = open;
        this.climberSubsystem = climberSubsystem;
        addRequirements(climberSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if(RobotContainer.joystick1.getRawButton(OIConstants.kClimberExtendButtonIdx)) {
            climberSubsystem.setPosition(false, true);
        } else {
            if(RobotContainer.joystick1.getRawButton(OIConstants.kClimberRetractButtonIdx)) {
                climberSubsystem.setPosition(false, false);
            } else {
                climberSubsystem.setPosition(open, false);
        }
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
