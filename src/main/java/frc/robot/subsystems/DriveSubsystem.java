package frc.robot.subsystems;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.MutableMeasure;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.Velocity;
import edu.wpi.first.units.Voltage;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
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

    public final static Encoder leftEncoder = new Encoder(
            DriveConstants.kLeftEncoderChannelA,
            DriveConstants.kLeftEncoderChannelB);
    public final static Encoder rightEncoder = new Encoder(
            DriveConstants.kRightEncoderChannelA,
            DriveConstants.kRightEncoderChannelB);

    public static Servo servoCam = new Servo(7);

    public SlewRateLimiter filter = new SlewRateLimiter(0.55);

    public double getEncoderMeters() {
        return (leftEncoder.get() + -rightEncoder.get()) / 2 * DriveConstants.kEncoderTick2Meter;
    }
    
    // Mutable holder for unit-safe voltage values, persisted to avoid reallocation.
    private final MutableMeasure<Voltage> m_appliedVoltage = MutableMeasure.zero(Units.Volts);
    // Mutable holder for unit-safe linear distance values, persisted to avoid reallocation.
    private final MutableMeasure<Distance> m_distance = MutableMeasure.zero(Units.Meters);
    // Mutable holder for unit-safe linear velocity values, persisted to avoid reallocation.
    private final MutableMeasure<Velocity<Distance>> m_velocity = MutableMeasure.zero(Units.MetersPerSecond);
    
    private final SysIdRoutine m_sysIdRoutine =
      new SysIdRoutine(
          // Empty config defaults to 1 volt/second ramp rate and 7 volt step voltage.
          new SysIdRoutine.Config(),
          new SysIdRoutine.Mechanism(
              // Tell SysId how to plumb the driving voltage to the motors.
              (Measure<Voltage> volts) -> {
                driveBackLeftMotor.setVoltage(volts.in(Units.Volts));
                driveBackRightMotor.setVoltage(volts.in(Units.Volts));
              },
              // Tell SysId how to record a frame of data for each motor on the mechanism being
              // characterized.
              log -> {
                // Record a frame for the left motors.  Since these share an encoder, we consider
                // the entire group to be one motor.
                log.motor("drive-left")
                    .voltage(
                        m_appliedVoltage.mut_replace(
                            driveBackLeftMotor.get() * RobotController.getBatteryVoltage(), Units.Volts))
                    .linearPosition(m_distance.mut_replace(leftEncoder.getDistance(), Units.Meters))
                    .linearVelocity(
                        m_velocity.mut_replace(leftEncoder.getRate(), Units.MetersPerSecond));
                // Record a frame for the right motors.  Since these share an encoder, we consider
                // the entire group to be one motor.
                log.motor("drive-right")
                    .voltage(
                        m_appliedVoltage.mut_replace(
                            driveBackRightMotor.get() * RobotController.getBatteryVoltage(), Units.Volts))
                    .linearPosition(m_distance.mut_replace(rightEncoder.getDistance(), Units.Meters))
                    .linearVelocity(
                        m_velocity.mut_replace(rightEncoder.getRate(), Units.MetersPerSecond));
              },
              // Tell SysId to make generated commands require this subsystem, suffix test state in
              // WPILog with this subsystem's name ("drive")
              this));
    
    public DifferentialDrive differentialDrive = null;

    public DriveSubsystem() {
        
        driveTopLeftMotor.set(ControlMode.Follower, driveBackLeftMotor.getDeviceID());
        driveTopLeftMotor.setInverted(InvertType.FollowMaster);

        driveTopRightMotor.set(ControlMode.Follower, driveBackRightMotor.getDeviceID());
        driveBackRightMotor.setInverted(true);
        driveTopRightMotor.setInverted(InvertType.FollowMaster);

        differentialDrive = new DifferentialDrive(driveBackLeftMotor, driveBackRightMotor);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        differentialDrive.tankDrive(-filter.calculate(leftSpeed), -filter.calculate(rightSpeed));
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Drive encoder value", getEncoderMeters());
    }

    public Command sysIdQuasistatic(SysIdRoutine.Direction direction) {
        return m_sysIdRoutine.quasistatic(direction);
      }
    
    public Command sysIdDynamic(SysIdRoutine.Direction direction) {
        return m_sysIdRoutine.dynamic(direction);
      }

}
