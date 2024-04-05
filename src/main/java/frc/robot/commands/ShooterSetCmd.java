package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OIConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterSetCmd extends Command {

    private final ShooterSubsystem shooterSubsystem;
    public final boolean speaker;
    public final boolean amp;

    public ShooterSetCmd(ShooterSubsystem shooterSubsystem, boolean speaker, boolean amp) {
        this.speaker = speaker;
        this.amp = amp;
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if(RobotContainer.joystick1.getRawButton(OIConstants.kSpeakerButtonIdx)) {
            shooterSubsystem.setPosition(true, false);
            } 
        else if(RobotContainer.joystick1.getRawButton(OIConstants.kAmpButtonIdx)) {
            shooterSubsystem.setPosition(false, true);
            } 
        else if(RobotContainer.joystick1.getRawButton(OIConstants.kIndexButtonIdx)){
            shooterSubsystem.shooterLeft2Motor.set(ControlMode.PercentOutput, ShooterConstants.kIndexSpeed);
        }
        else {
            shooterSubsystem.setPosition(false, false);
            }
        }    

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}
