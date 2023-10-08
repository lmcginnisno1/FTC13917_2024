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
          /**
           * Temporary storage for magic numbers in code
           * Home: 0
           * Intake: 35
           * Straight Up: 96
           * Level One: 141
           */
          public static final double kMaxVelocityDegreesPerSecond = 720;
          public static final double kMaxAccelerationDegreesPerSecond = 240;
          public static final double kOffsetDegrees = 0;
          public static final double kMaxPower = 1;
          public static final double kP = 10;
          public static final double kI = 0;
          public static final double kD = 0;
          public static final double kF = 0;

          // Calculated using a 50.9:1 ratio motor
          public static final double kTicksToDegrees = 3.95861; // PPR / 360
     }
     public static final class ElbowConstants{
          /**
           * Temporary storage for magic numbers in code
           * Home: 0
           * 2nd Arm Parallel with 1st: -22
           * Intake: 48
           * Level One: -75
           */
          public static final double kMaxVelocityDegreesPerSecond = 720;
          public static final double kMaxAccelerationDegreesPerSecond = 360;
          public static final double kOffsetDegrees = 0;
          public static final double kMaxPower = 1;
          public static final double kP = 10;
          public static final double kI = 0;
          public static final double kD = 0;
          public static final double kF = 0;

          // Calculated using a 50.9:1 ratio motor
          public static final double kTicksToDegrees = 3.95861; // PPR / 360
     }
     public static final class WristConstants{
          public static final double m_openPosition = 0.6;
          public static final double m_closedPosition = 0.0;
     }
}

