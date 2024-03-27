package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.SPI;

public final class Constants {

    public static final class DriveConstants {
        public static final int kBackLeftMotorPort = 3;
        public static final int kTopLeftMotorPort = 1;
        public static final int kBackRightMotorPort = 2;
        public static final int kTopRightMotorPort = 0;
        public static final SPI.Port kGyroPort = SPI.Port.kOnboardCS0;
        public static final int kLeftEncoderChannelA = 0;
        public static final int kLeftEncoderChannelB = 1;
        public static final int kRightEncoderChannelA = 2;
        public static final int kRightEncoderChannelB = 3;
        public static final double kEncoderTick2Meter = 1.0 / 4096.0 * 0.128 * Math.PI;

        public static final double kAutoDriveForwardSpeed = 0.5;
        public static final double kAutoDriveForwardDistance = 1.5;

        public static final double kTrackWidth = Units.inchesToMeters(18.75);
        public static final DifferentialDriveKinematics kKinematics = new DifferentialDriveKinematics(kTrackWidth);
    }

    public static final class ClimberConstants {
        public static final int kRightMotorPort = 4;
        public static final int kLeftMotorPort = 5;
        public static final double kExtendSpeed = -1;
        public static final double kRetractSpeed = 1;
    }

    public static final class IntakeConstants {
        public static final int kTopMotorPort = 1;
        public static final int kBottomMotorPort = 0;
        public static final double kTopOpenSpeed = -0.3;
        public static final double kTopCloseSpeed = 0;
        public static final double kBottomOpenSpeed = -0.8;
        public static final double kBottomCloseSpeed = 0;
    }

    public static final class ShooterConstants {
        public static final int kTopMotorPort = 2;
        public static final int kBottomMotorPort = 6;
        public static final double kSpeakerSpeed = 1;
        public static final double kAmpSpeed = 0.7;
    }

    public static final class OIConstants {
        public static final int kDriverJoystickPort = 0;

        public static final int kClimberExtendButtonIdx = 2;
        public static final int kClimberRetractButtonIdx = 3;
        public static final int kIntakeCloseButtonIdx  = 4;
        public static final int kAmpButtonIdx = 5;
        public static final int kSpeakerButtonIdx = 6;
    }

    public static final class AutoConstants {
        public static final double kLeftSlow = 0.3;
        public static final double kRightSlow = 0.3;
        public static final double kRotateSpeed = 0.35;
        public static final double kRotateSpeedSlow = 0.25;
    }

    public static final class VisionConstants {
        /** Physical location of the apriltag camera on the robot, relative to the center of the robot. */
        public static final Transform3d kCameraLocation = new Transform3d(
            new Translation3d(-0.06, 0.2, -0.2127),
            new Rotation3d(0.0, Units.degreesToRadians(15.0), Units.degreesToRadians(-3.0)));
        
        public static final double kFieldLengthMeters = 16.54175;
        public static final double kFieldWidthMeters = 8.21055;
    
        // Pose on the opposite side of the field. Use with `relativeTo` to flip a pose to the opposite alliance
        public static final Pose2d kFlippingPose = new Pose2d(
            new Translation2d(kFieldLengthMeters, kFieldWidthMeters),
            new Rotation2d(Math.PI));
    
        /** Minimum target ambiguity. Targets with higher ambiguity will be discarded */
        public static final double kApriltagAmbiguityThreshold = 0.2;
      }
}
