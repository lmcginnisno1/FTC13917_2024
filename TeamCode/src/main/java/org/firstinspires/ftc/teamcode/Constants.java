package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import java.util.HashMap;

public class Constants {
     public static final class ShoulderConstants{
          public static final double kMaxVelocityDegreesPerSecond = 1800;//1500
          public static final double kMaxAccelerationDegreesPerSecond = 1200;//500
          public static final double kOffsetDegrees = 0;
          public static final double kMaxPower = 1;
          public static final double kP = 5;
          public static final double kI = 0;
          public static final double kD = 0;
          public static final double kF = 0;
          // Calculated using a 99.5:1 ratio motor
          public static final double kTicksToDegrees = 7.7394444444444444444444444444444; // PPR / 360

          //Shoulder Positions
          public static final double kHome = 0;
          public static final double kReadyIntake = 37.5;//30.0
          public static final double kReadyIntakeLevelFive = 45;
          public static final double kReadyIntakeLevelFour = 40;
          public static final double kDropIntake = 27.5;//27.5
          public static final double kDropIntakeLevelFive = 32.5;
          public static final double kDropIntakeLevelFour = 27;
          public static final double kUp = 100;
          public static final double kClimb = 50;
          public static final double kLevelOne = 125;//110
          public static final double kLevelTwo = 110;//110
          public static final double kLevelThree = 97.5;//100
          public static final double kLevelFour = 97.5;
          public static final double kDroneLaunch = 55;
          public static final double kUpright = 200;

     }
     public static final class ElbowConstants{
          public static final double kMaxVelocityDegreesPerSecond = 720;//360
          public static final double kMaxAccelerationDegreesPerSecond = 1200;//360
          public static final double kOffsetDegrees = 0;
          public static final double kMaxPower = 1;
          public static final double kP = 13.0;//good but slow
          public static final double kI = 0;
          public static final double kD = 0;
          public static final double kF = 0;
          // Calculated using a 71.2:1 ratio motor
          public static final double kTicksToDegrees = 5.53777777778; // PPR / 360

          //Elbow Positions
          //Bigger absolute number = farther from robot, - = back  of robot
          public static final double kHome = 0;
          public static final double kParallel = -20;
          public static final double kReadyIntake = 47.5;//52.5
          public static final double kReadyIntakeLevelFive = 50;
          public static final double kReadyIntakeLevelFour = 50;
          public static final double kLevelOne = -45;//-30
          public static final double kLevelTwo = -57.5;//-65
          public static final double kLevelThree = -70;//-75
          public static final double kLevelFour = -85;

     }
     public static final class WristConstants{
          public static final double kClawAOpen = 0.0;
          public static final double kClawBOpen = 0.048;
          public static final double kClawBHalfOpen = 0.25;
          public static final double kClawAClose = 0.5;
          public static final double kClawBClose = 0.38;
          public static final double kWristOffset = -0.035;
          public static final double kClawAOffset = 0.1;
          public static final double kClawBOffset = 0.1;
          // Wrist Positions
          public static final double kReadyIntake = 0.54;//0.55
          public static final double kReadyIntakeLevelFive = .53;
          public static final double kReadyIntakeLevelFour = .525;
          public static final double kHome = 0.0;
          public static final double kLevelOne = 0.275;//0.3
          public static final double kLevelTwo = 0.4;//0.4
          public static final double kLevelThree = 0.45;//.45
          public static final double kLevelFour = 0.5;
     }

     public static final class droneLauncherConstants{
          public static final double kClosed = .225;
          public static final double kOpen = .5;

     }
}

