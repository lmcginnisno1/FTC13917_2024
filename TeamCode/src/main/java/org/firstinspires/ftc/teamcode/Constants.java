package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import java.util.HashMap;

public class Constants {
     public static final class ShoulderConstants{
          public static final double kMaxVelocityDegreesPerSecond = 1500;
          public static final double kMaxAccelerationDegreesPerSecond = 500;
          public static final double kOffsetDegrees = 0;
          public static final double kMaxPower = 1;
          public static final double kP = 10;
          public static final double kI = 0;
          public static final double kD = 0;
          public static final double kF = 0;
          // Calculated using a 99.5:1 ratio motor
          public static final double kTicksToDegrees = 7.7394444444444444444444444444444; // PPR / 360

          //Shoulder Positions
          public static final double kHome = 0;
          public static final double kPreReadyIntake = 43;
          public static final double kReadyIntake = 33;
          public static final double kDropIntake = 29;
          public static final double kUp = 100;
          public static final double kClimb = 50;
          public static final double kLevelOne = 110;
          public static final double kLevelTwo = 110;
          public static final double kLevelThree = 100;
          public static final double kDroneLaunch = 50;
          public static final double kUpright = 200;

     }
     public static final class ElbowConstants{
          public static final double kMaxVelocityDegreesPerSecond = 360;
          public static final double kMaxAccelerationDegreesPerSecond = 360;
          public static final double kOffsetDegrees = 0;
          public static final double kMaxPower = 1;
          public static final double kP = 20;
          public static final double kI = 0;
          public static final double kD = 0;
          public static final double kF = 0;
          // Calculated using a 71.2:1 ratio motor
          public static final double kTicksToDegrees = 5.53777777778; // PPR / 360

          //Elbow Positions
          //Bigger number = farther from robot
          public static final double kHome = 0;
          public static final double kParallel = -20;
          public static final double kReadyFinal = 42.5;//40.0
          public static final double kReadyIntake = 37.5;
          public static final double kPreDrop = 43;
          public static final double kDropIntake = 60;
          public static final double kLevelOne = -40;
          public static final double kLevelTwo = -65;
          public static final double kLevelThree = -75;
     }
     public static final class WristConstants{
          public static final double kClawAOpen = 0.0;
          public static final double kClawBOpen = 0.175;
          public static final double kClawAClose = 0.5;
          public static final double kClawBClose = 0.38;
          public static final double kWristOffset = -0.035;
          public static final double kClawAOffset = 0.1;
          public static final double kClawBOffset = 0.1;
          // Wrist Positions
          public static final double kReadyIntake = 0.7;
          public static final double kDropIntake = 0.55;
          public static final double kHome = 0.0;
          public static final double kLevelOne = 0.3;
          public static final double kLevelTwo = 0.40;
          public static final double kLevelThree = 0.45;
     }

     public static final class intakeConstants{
          public static final double kForwardPower = 1;
          public static final double kReversePower = -1;
     }

     public static final class droneLauncherConstants{
          public static final double kClosed = .225;
          public static final double kOpen = .5;

     }
}

