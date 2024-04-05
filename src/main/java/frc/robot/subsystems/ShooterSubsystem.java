package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase {

    public WPI_VictorSPX shooterLeft1Motor = new WPI_VictorSPX(ShooterConstants.kLeft1MotorPort);
    public WPI_VictorSPX shooterLeft2Motor = new WPI_VictorSPX(ShooterConstants.kLeft2MotorPort);
    public WPI_TalonSRX shooterRightMotor = new WPI_TalonSRX(ShooterConstants.kRightMotorPort);


    public ShooterSubsystem() {
    }

    @Override
    public void periodic() {
    }

    public void setPosition(boolean speaker, boolean amp) {
        if (speaker) {
            shooterLeft1Motor.set(ControlMode.PercentOutput, ShooterConstants.kSpeakerSpeed);
            shooterLeft2Motor.set(ControlMode.PercentOutput, ShooterConstants.kSpeakerSpeed);
            shooterRightMotor.set(ControlMode.PercentOutput, ShooterConstants.kSpeakerSpeed);
            } 
        else if (amp) {
            shooterLeft1Motor.set(ControlMode.PercentOutput, ShooterConstants.kAmpSpeed);
            shooterLeft2Motor.set(ControlMode.PercentOutput, ShooterConstants.kAmpSpeed);
            shooterRightMotor.set(ControlMode.PercentOutput, ShooterConstants.kAmpSpeed);
            }
        else {
            shooterLeft1Motor.set(ControlMode.PercentOutput, 0);
            shooterLeft2Motor.set(ControlMode.PercentOutput, 0);
            shooterRightMotor.set(ControlMode.PercentOutput, 0);
            }
    }
}