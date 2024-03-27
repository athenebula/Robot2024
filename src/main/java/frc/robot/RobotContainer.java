package frc.robot;

import frc.robot.commands.ClimberSetCmd;
import frc.robot.commands.DriveForwardCmd;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.TankDriveCmd;
import frc.robot.commands.IntakeSetCmd;
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
        
        driveSubsystem.setDefaultCommand(new TankDriveCmd());
        climberSubsystem.setDefaultCommand(new ClimberSetCmd(climberSubsystem, true));
        intakeSubsystem.setDefaultCommand(new IntakeSetCmd(intakeSubsystem, true));
        shooterSubsystem.setDefaultCommand(new ShooterSetCmd(shooterSubsystem, true));

    }

    public void configureButtonBindings() {
//        new JoystickButton(joystick1, OIConstants.kElevatorPIDRaiseButtonIdx)
//                .whileTrue(new ElevatorPIDCmd(elevatorSubsystem, ElevatorConstants.kRaisedPosition));
//        new JoystickButton(joystick1, OIConstants.kElevatorPIDLowerButtonIdx)
//                .whileTrue(new ElevatorPIDCmd(elevatorSubsystem, ElevatorConstants.kLoweredPosition));
//        new JoystickButton(joystick1, OIConstants.kElevatorJoystickRaiseButtonIdx)
//               .whileTrue(new ElevatorJoystickCmd(elevatorSubsystem, ElevatorConstants.kJoystickMaxSpeed));
//        new JoystickButton(joystick1, OIConstants.kElevatorJoystickLowerButtonIdx)
//               .whileTrue(new ElevatorJoystickCmd(elevatorSubsystem, -ElevatorConstants.kJoystickMaxSpeed));
        new JoystickButton(joystick1, OIConstants.kIntakeCloseButtonIdx);
        new JoystickButton(joystick1, OIConstants.kSpeakerButtonIdx);
        new JoystickButton(joystick1, OIConstants.kAmpButtonIdx);
    }

    public Command getAutonomousCommand() {

        return new SequentialCommandGroup( //
                new DriveForwardCmd(driveSubsystem, DriveConstants.kAutoDriveForwardDistance), //
                new ParallelCommandGroup( //
                        new IntakeSetCmd(intakeSubsystem, false)//
                )//
        );
    }
    
}
