package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {

    private PWMSparkMax shooterTopMotor = new PWMSparkMax(ShooterConstants.kTopMotorPort);
    private PWMSparkMax shooterBottomMotor = new PWMSparkMax(ShooterConstants.kBottomMotorPort);

    public ShooterSubsystem() {
    }

    @Override
    public void periodic() {
    }

    public void setPosition(boolean open) {
        if (open) {
            shooterTopMotor.set(ShooterConstants.kOpenSpeed);
            shooterBottomMotor.set(-ShooterConstants.kOpenSpeed);
        } else {
            shooterTopMotor.set(ShooterConstants.kCloseSpeed);
            shooterBottomMotor.set(-ShooterConstants.kCloseSpeed);
        }
    }
}
