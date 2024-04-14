package frc.robot;

import frc.robot.commands.ClimberSetCmd;
import frc.robot.commands.DriveForwardCmd;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.IntakeSetCmd;
import frc.robot.commands.RocketDriveCmd;
import frc.robot.commands.ShooterSetCmd;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
    public static DriveSubsystem driveSubsystem = new DriveSubsystem();
    private final ClimberSubsystem climberSubsystem = new ClimberSubsystem();
    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    private final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
    public static CommandXboxController xboxController = new CommandXboxController(OIConstants.kDriverJoystickPort);
    public static Joystick joystick1 = new Joystick(OIConstants.kDriverJoystickPort);

    public RobotContainer() {
        
        driveSubsystem.setDefaultCommand(new RocketDriveCmd(driveSubsystem));
        climberSubsystem.setDefaultCommand(new ClimberSetCmd(climberSubsystem, false, false));
        intakeSubsystem.setDefaultCommand(new IntakeSetCmd(intakeSubsystem, false));
        shooterSubsystem.setDefaultCommand(new ShooterSetCmd(shooterSubsystem, false, false));

    }

    public void configureButtonBindings() {
        new JoystickButton(joystick1, OIConstants.kIntakeCloseButtonIdx);
        new JoystickButton(joystick1, OIConstants.kSpeakerButtonIdx);
        new JoystickButton(joystick1, OIConstants.kAmpButtonIdx);
        new JoystickButton(joystick1, OIConstants.kClimberExtendButtonIdx);
        new JoystickButton(joystick1, OIConstants.kClimberRetractButtonIdx);
        new JoystickButton(joystick1, OIConstants.kIndexButtonIdx);
    }

    public Command getAutonomousCommand() {

        return new SequentialCommandGroup(
                new DriveForwardCmd(driveSubsystem),
                new ParallelCommandGroup(
                        new IntakeSetCmd(intakeSubsystem, false),
                        new ClimberSetCmd(climberSubsystem, false, false),
                        new ShooterSetCmd(shooterSubsystem, false, false)
                )
        );
    }
    
}
