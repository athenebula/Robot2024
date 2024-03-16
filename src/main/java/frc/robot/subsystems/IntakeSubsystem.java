package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {

    private PWMSparkMax intakeTopMotor = new PWMSparkMax(IntakeConstants.kTopMotorPort);
    private PWMSparkMax intakeBottomMotor = new PWMSparkMax(IntakeConstants.kBottomMotorPort);

    public IntakeSubsystem() {
    }

    @Override
    public void periodic() {
    }

    public void setPosition(boolean open) {
        if (open) {
            intakeTopMotor.set(IntakeConstants.kOpenSpeed);
            intakeBottomMotor.set(-IntakeConstants.kOpenSpeed);
        } else {
            intakeTopMotor.set(IntakeConstants.kCloseSpeed);
            intakeBottomMotor.set(-IntakeConstants.kCloseSpeed);
        }
    }
}
