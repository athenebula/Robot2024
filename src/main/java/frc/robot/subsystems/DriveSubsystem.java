package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class DriveSubsystem extends SubsystemBase {

    public final WPI_VictorSPX driveBackLeftMotor = new WPI_VictorSPX(DriveConstants.kBackLeftMotorPort);
    public final WPI_VictorSPX driveTopLeftMotor = new WPI_VictorSPX(DriveConstants.kTopLeftMotorPort);
    public final WPI_VictorSPX driveBackRightMotor = new WPI_VictorSPX(DriveConstants.kBackRightMotorPort);
    public final WPI_VictorSPX driveTopRightMotor = new WPI_VictorSPX(DriveConstants.kTopRightMotorPort);

    public static ADXRS450_Gyro m_gyro = new ADXRS450_Gyro(DriveConstants.kGyroPort);

    private final Encoder leftEncoder = new Encoder(//
            DriveConstants.kLeftEncoderChannelA,
            DriveConstants.kLeftEncoderChannelB);
    private final Encoder rightEncoder = new Encoder(//
            DriveConstants.kRightEncoderChannelA,
            DriveConstants.kRightEncoderChannelB);

    public double getEncoderMeters() {
        return (leftEncoder.get() + -rightEncoder.get()) / 2 * DriveConstants.kEncoderTick2Meter;
    }

    DifferentialDrive differentialDrive = null;

    public DriveSubsystem() {
        
        driveTopLeftMotor.set(ControlMode.Follower, driveBackLeftMotor.getDeviceID());
        driveTopLeftMotor.setInverted(InvertType.FollowMaster);

        driveTopRightMotor.set(ControlMode.Follower, driveBackRightMotor.getDeviceID());
        driveBackRightMotor.setInverted(true);
        driveTopRightMotor.setInverted(InvertType.FollowMaster);

        differentialDrive = new DifferentialDrive(driveBackLeftMotor, driveBackRightMotor);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        differentialDrive.tankDrive(leftSpeed, rightSpeed);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Drive encoder value", getEncoderMeters());
    }

}
