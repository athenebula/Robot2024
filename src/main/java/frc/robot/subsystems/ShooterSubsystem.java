package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {

    private PWMSparkMax shooterTopMotor = new PWMSparkMax(ShooterConstants.kTopMotorPort);
    private WPI_VictorSPX shooterBottomMotor = new WPI_VictorSPX(ShooterConstants.kBottomMotorPort);

    public ShooterSubsystem() {
    }

    @Override
    public void periodic() {
    }

    public void setPosition(boolean open, boolean speaker) {
        if (open) {
                shooterTopMotor.set(0);
                shooterBottomMotor.set(0);
        } else {
            if (speaker) {
                shooterTopMotor.set(ShooterConstants.kSpeakerSpeed);
                shooterBottomMotor.set(ShooterConstants.kSpeakerSpeed);
            } else {
                shooterTopMotor.set(ShooterConstants.kAmpSpeed);
                shooterBottomMotor.set(ShooterConstants.kAmpSpeed);
            }
        }
    }
}