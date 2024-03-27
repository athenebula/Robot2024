package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.IntakeConstants;

public class ClimberSubsystem extends SubsystemBase {

    private WPI_TalonSRX leftClimberMotor = new WPI_TalonSRX(ClimberConstants.kLeftMotorPort);
    private WPI_TalonSRX rightClimberMotor = new WPI_TalonSRX(ClimberConstants.kRightMotorPort);

    public ClimberSubsystem() {
    }

    @Override
    public void periodic() {
    }

    public void setPosition(boolean open, boolean extend) {
        if (open) {
            leftClimberMotor.set(0);
            rightClimberMotor.set(0);
        } else {
            if (extend) {
                leftClimberMotor.set(ClimberConstants.kExtendSpeed);
                rightClimberMotor.set(ClimberConstants.kExtendSpeed);
            } else {
                leftClimberMotor.set(ClimberConstants.kRetractSpeed);
                rightClimberMotor.set(ClimberConstants.kRetractSpeed);
            }
        }
    }
}
