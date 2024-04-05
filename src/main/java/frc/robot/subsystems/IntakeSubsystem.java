package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {

    private PWMSparkMax intakeTopMotor = new PWMSparkMax(IntakeConstants.kTopMotorPort);
    public PWMSparkMax intakeBottomMotor = new PWMSparkMax(IntakeConstants.kBottomMotorPort);

    public IntakeSubsystem() {
    }

    @Override
    public void periodic() {
    }

    public void setPosition(boolean open) {
        if (open) {
            intakeTopMotor.set(IntakeConstants.kTopOpenSpeed);
            intakeBottomMotor.set(-IntakeConstants.kBottomOpenSpeed);
        } else {
            intakeTopMotor.set(IntakeConstants.kTopCloseSpeed);
            intakeBottomMotor.set(-IntakeConstants.kBottomCloseSpeed);
        }
    }
}
