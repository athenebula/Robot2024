package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

public class ClimberSubsystem extends SubsystemBase {

    private WPI_TalonSRX leftClimberMotor = new WPI_TalonSRX(ClimberConstants.kLeftMotorPort);
    private WPI_TalonSRX rightClimberMotor = new WPI_TalonSRX(ClimberConstants.kRightMotorPort);

    public ClimberSubsystem() {
    }

    @Override
    public void periodic() {
    }

    public void setPosition(boolean extend, boolean retract) {
        if (extend) {
            leftClimberMotor.set(ControlMode.PercentOutput, ClimberConstants.kExtendSpeed);
            rightClimberMotor.set(ControlMode.PercentOutput, ClimberConstants.kExtendSpeed);
        } else 
            if (retract) {
                leftClimberMotor.set(ControlMode.PercentOutput, ClimberConstants.kRetractSpeed);
                rightClimberMotor.set(ControlMode.PercentOutput, ClimberConstants.kRetractSpeed);
            }
            else {
                leftClimberMotor.set(ControlMode.PercentOutput, 0);
                rightClimberMotor.set(ControlMode.PercentOutput, 0);
            }
    }
}
