package frc.robot;

import edu.wpi.first.wpilibj.SPI;

public final class Constants {

    public static final class DriveConstants {
        public static final int kBackLeftMotorPort = 3;
        public static final int kTopLeftMotorPort = 1;
        public static final int kBackRightMotorPort = 2;
        public static final int kTopRightMotorPort = 0;
        public static final SPI.Port kGyroPort = SPI.Port.kOnboardCS0;

        public static final int kFilter = 1;

        public static final double kAutoDriveForwardSpeed = 0.5;
        public static final double kAutoDriveTime = 2;
    }

    public static final class ClimberConstants {
        public static final int kRightMotorPort = 6;
        public static final int kLeftMotorPort = 7;
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
        public static final int kLeft1MotorPort = 4;
        public static final int kLeft2MotorPort = 5;
        public static final int kRightMotorPort = 8;
        public static final double kSpeakerSpeed = 1;
        public static final double kAmpSpeed = 0.7;
        public static final double kIndexSpeed = -0.75;
    }

    public static final class OIConstants {
        public static final int kDriverJoystickPort = 0;

        public static final int kIndexButtonIdx = 1;
        public static final int kClimberExtendButtonIdx = 2;
        public static final int kClimberRetractButtonIdx = 3;
        public static final int kIntakeCloseButtonIdx  = 4;
        public static final int kAmpButtonIdx = 5;
        public static final int kSpeakerButtonIdx = 6;
    }

}
