package frc.robot;

import frc.robot.commands.DriveForwardCmd;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.TankDriveCmd;
import frc.robot.commands.ElevatorJoystickCmd;
import frc.robot.commands.ElevatorPIDCmd;
import frc.robot.commands.IntakeSetCmd;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
    public static DriveSubsystem driveSubsystem = new DriveSubsystem();
    private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    public static CommandXboxController xboxController = new CommandXboxController(OIConstants.kDriverJoystickPort);
    public static Joystick joystick1 = new Joystick(OIConstants.kDriverJoystickPort);
    public static Trigger aButton = xboxController.a();

    public RobotContainer() {
        
        driveSubsystem.setDefaultCommand(new TankDriveCmd());
        elevatorSubsystem.setDefaultCommand(new ElevatorJoystickCmd(elevatorSubsystem, 0));
        intakeSubsystem.setDefaultCommand(new IntakeSetCmd(intakeSubsystem, true));
    }

    public void configureButtonBindings() {
        new JoystickButton(joystick1, OIConstants.kElevatorPIDRaiseButtonIdx)
                .whileTrue(new ElevatorPIDCmd(elevatorSubsystem, ElevatorConstants.kRaisedPosition));
        new JoystickButton(joystick1, OIConstants.kElevatorPIDLowerButtonIdx)
                .whileTrue(new ElevatorPIDCmd(elevatorSubsystem, ElevatorConstants.kLoweredPosition));
        new JoystickButton(joystick1, OIConstants.kElevatorJoystickRaiseButtonIdx)
                .whileTrue(new ElevatorJoystickCmd(elevatorSubsystem, ElevatorConstants.kJoystickMaxSpeed));
        new JoystickButton(joystick1, OIConstants.kElevatorJoystickLowerButtonIdx)
                .whileTrue(new ElevatorJoystickCmd(elevatorSubsystem, -ElevatorConstants.kJoystickMaxSpeed));
        new JoystickButton(joystick1, OIConstants.kIntakeCloseButtonIdx);
    }

    public Command getAutonomousCommand() {

        return new SequentialCommandGroup( //
                new DriveForwardCmd(driveSubsystem, DriveConstants.kAutoDriveForwardDistance), //
                new ParallelCommandGroup( //
                        new IntakeSetCmd(intakeSubsystem, false), //
                        new ElevatorPIDCmd(elevatorSubsystem, ElevatorConstants.kRaisedPosition) //
                )//
        );
    }
}
