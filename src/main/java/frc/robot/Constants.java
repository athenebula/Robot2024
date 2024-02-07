package frc.robot;

import edu.wpi.first.wpilibj.SPI;

public final class Constants {

    public static final class DriveConstants {
        public static final int kBackLeftMotorPort = 0;
        public static final int kTopLeftMotorPort = 2;
        public static final int kBackRightMotorPort = 1;
        public static final int kTopRightMotorPort = 3;
        public static final SPI.Port kGyroPort = SPI.Port.kOnboardCS0;
        public static final int kLeftEncoderChannelA = 0;
        public static final int kLeftEncoderChannelB = 1;
        public static final int kRightEncoderChannelA = 2;
        public static final int kRightEncoderChannelB = 3;
        public static final double kEncoderTick2Meter = 1.0 / 4096.0 * 0.128 * Math.PI;

        public static final double kAutoDriveForwardSpeed = 0.5;
        public static final double kAutoDriveForwardDistance = 1.5;
    }

    public static final class ElevatorConstants {
        public static final int kMotorPort = 2;
        public static final int kEncoderChannelA = 4;
        public static final int kEncoderChannelB = 5;
        public static final double kEncoderTick2Meter = 1.0 / 4096.0 * 0.1 * Math.PI;
        public static final double kP = 3;
        public static final double kI = 0;
        public static final double kD = 0.8;

        public static final double kRaisedPosition = 1.2;
        public static final double kLoweredPosition = 0;
        public static final double kJoystickMaxSpeed = 0.5;
    }

    public static final class IntakeConstants {
        public static final int kLeftMotorPort = 0;
        public static final int kRightMotorPort = 1;
        public static final double kOpenSpeed = -1;
        public static final double kCloseSpeed = 1;
    }

    public static final class OIConstants {
        public static final int kDriverJoystickPort = 0;

        public static final int kElevatorPIDRaiseButtonIdx = 1;
        public static final int kElevatorPIDLowerButtonIdx = 2;
        public static final int kElevatorJoystickRaiseButtonIdx = 3;
        public static final int kElevatorJoystickLowerButtonIdx = 4;
        public static final int kIntakeCloseButtonIdx = 5;
    }

    public static final class AutoConstants {
        public static final double leftSlow = 0.24;
        public static final double rightSlow = -0.24;
        public static final double rotateSpeed = 0.35;
        public static final double rotateSpeedSlow = 0.25;
    }
}
