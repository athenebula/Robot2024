package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class DriveSubsystem extends SubsystemBase {

    public final static WPI_VictorSPX driveBackLeftMotor = new WPI_VictorSPX(DriveConstants.kBackLeftMotorPort);
    public final static WPI_VictorSPX driveTopLeftMotor = new WPI_VictorSPX(DriveConstants.kTopLeftMotorPort);
    public final static WPI_VictorSPX driveBackRightMotor = new WPI_VictorSPX(DriveConstants.kBackRightMotorPort);
    public final static WPI_VictorSPX driveTopRightMotor = new WPI_VictorSPX(DriveConstants.kTopRightMotorPort);

    public DriveSubsystem() {
        driveBackRightMotor.setInverted(true);
        driveTopRightMotor.setInverted(true);
    }

    @Override
    public void periodic() {
    }
}
