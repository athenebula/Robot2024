package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberSetCmd extends Command {

    private final ClimberSubsystem climberSubsystem;
    public final boolean extend;
    public final boolean retract;

    public ClimberSetCmd(ClimberSubsystem climberSubsystem, boolean extend, boolean retract) {
        this.extend = extend;
        this.retract = retract;
        this.climberSubsystem = climberSubsystem;
        addRequirements(climberSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if(RobotContainer.joystick1.getRawButton(OIConstants.kClimberExtendButtonIdx)) {
            climberSubsystem.setPosition(true, false);
        } 

        else {
            if(RobotContainer.joystick1.getRawButton(OIConstants.kClimberRetractButtonIdx)) {
                climberSubsystem.setPosition(false, true);
            } 
            else {
                if(Math.abs(RobotContainer.xboxController.getRightY()) > 0.1) {
                    climberSubsystem.setPosition(false, false);
                    climberSubsystem.leftClimberMotor.set(ClimberConstants.kExtendSpeed * RobotContainer.xboxController.getRightY());
                    climberSubsystem.rightClimberMotor.set(ClimberConstants.kExtendSpeed * RobotContainer.xboxController.getRightY());
                }
                else {
                    climberSubsystem.setPosition(false, false);
                }
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
