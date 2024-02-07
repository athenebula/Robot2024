package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorPIDCmd extends Command {
    private final ElevatorSubsystem elevatorSubsystem;
    private final PIDController pidController;

    public ElevatorPIDCmd(ElevatorSubsystem elevatorSubsystem, double setpoint) {
        this.elevatorSubsystem = elevatorSubsystem;
        this.pidController = new PIDController(//
                ElevatorConstants.kP, ElevatorConstants.kI, ElevatorConstants.kD);
        pidController.setSetpoint(setpoint);
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        pidController.reset();
    }

    @Override
    public void execute() {
        double speed = pidController.calculate(elevatorSubsystem.getEncoderMeters());
        elevatorSubsystem.setMotor(speed);
    }

    @Override
    public void end(boolean interrupted) {
        elevatorSubsystem.setMotor(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
