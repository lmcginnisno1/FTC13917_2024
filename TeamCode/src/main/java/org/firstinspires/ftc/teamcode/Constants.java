package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import java.util.HashMap;

public class Constants {
     public static final class ArmConstants{
          enum ArmLevel {
             Home
             ,One
             ,Two
             ,Three
          }
          static ArmLevel m_armLevel = ArmLevel.Home;
          public static ArmLevel getArmLevel(){
               return m_armLevel;
          }
          public void setArmLevel(ArmLevel p_ArmLevel){
               m_armLevel = p_ArmLevel;
          }

     }
     public static final class ShoulderConstants{
          public static final double kMaxVelocityDegreesPerSecond = 1500;
          public static final double kMaxAccelerationDegreesPerSecond = 1500;
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
          public static final double kReadyIntake = 43;
          public static final double kDropIntake = 30;
          public static final double kUp = 90;

          public static final double kLevelOne = 110;
     }
     public static final class ElbowConstants{
          public static final double kMaxVelocityDegreesPerSecond = 720;
          public static final double kMaxAccelerationDegreesPerSecond = 720;
          public static final double kOffsetDegrees = 0;
          public static final double kMaxPower = 1;
          public static final double kP = 10;
          public static final double kI = 0;
          public static final double kD = 0;
          public static final double kF = 0;
          // Calculated using a 50.9:1 ratio motor
          public static final double kTicksToDegrees = 3.95861; // PPR / 360

          //Elbow Positions
          public static final double kHome = 0;
          public static final double kParallel = -20;
          public static final double kReadyIntake = 55;
          public static final double kDropIntake = 57;
          public static final double kLevelOne = -55;
     }
     public static final class WristConstants{
          public static final double kClawAOpen = 0.0;
          public static final double kClawBOpen = 0.0;
          public static final double kClawAClose = 0.5;
          public static final double kClawBClose = 0.38;
          public static final double kWristOffset = -0.035;
          public static final double kClawAOffset = 0.1;
          public static final double kClawBOffset = 0.1;
          // Wrist Positions
          public static final double kReadyIntake = 0.5;
          public static final double kHome = 0.17;
          public static final double kLevelOne = 0.40;
     }
}

