package frc.robot.subsystems;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
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

    public SlewRateLimiter leftFilter = new SlewRateLimiter(DriveConstants.kFilter);
    public SlewRateLimiter rightFilter = new SlewRateLimiter(DriveConstants.kFilter);
    
    public DifferentialDrive differentialDrive = null;

    public DriveSubsystem() {
        
        driveTopLeftMotor.set(ControlMode.Follower, DriveConstants.kBackLeftMotorPort);
        driveTopLeftMotor.setInverted(InvertType.FollowMaster);

        driveTopRightMotor.set(ControlMode.Follower, DriveConstants.kBackRightMotorPort);
        driveBackRightMotor.setInverted(true);
        driveTopRightMotor.setInverted(InvertType.FollowMaster);

        differentialDrive = new DifferentialDrive(driveBackLeftMotor, driveBackRightMotor);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        if((Math.abs(RobotContainer.xboxController.getLeftY()) <= 0.05) & (Math.abs(RobotContainer.xboxController.getRightY()) <= 0.05)) {
            differentialDrive.tankDrive(leftSpeed, rightSpeed);

        } else {
            differentialDrive.tankDrive(leftFilter.calculate(leftSpeed), rightFilter.calculate(rightSpeed));
        }
    }

    @Override
    public void periodic() {
    }
}
