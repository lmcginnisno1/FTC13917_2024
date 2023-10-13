package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import java.util.HashMap;

public class Constants {
     public static final class ArmConstants{
          public enum ArmLevel {
             Home
             ,One
          }
          static ArmLevel m_armLevel = ArmLevel.Home;
          public static ArmLevel getArmLevel(){
               return m_armLevel;
          }
          public static void setArmLevel(ArmLevel p_ArmLevel){
               m_armLevel = p_ArmLevel;
          }

     }
     public static final class ShoulderConstants{
          public static final double kMaxVelocityDegreesPerSecond = 100;
          public static final double kMaxAccelerationDegreesPerSecond = 25;
          public static final double kOffsetDegrees = 0;
          public static final double kMaxPower = 1;
          public static final double kP = 10;
          public static final double kI = 0;
          public static final double kD = 0;
          public static final double kF = 0;
          // Calculated using a 50.9:1 ratio motor
          public static final double kTicksToDegrees = 7.73944; // PPR / 360

          //Shoulder Positions
          public static final double kHome = 0;
          public static final double kReadyIntake = 39;
          public static final double kDropIntake = 30;
          public static final double kUp = 90;

          public static final double kLevelOne = 110;
     }
     public static final class ElbowConstants{
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

     public static final class intakeConstants{
          public static final double kForwardPower = 1;
          public static final double kReversePower = -1;
     }

     public static final class robotConstants{
          public enum robotState{
               Home
               ,Ready2Intake
               ,Intake
               ,Stow
               ,Score
          }

          static robotState m_robotState = robotState.Home;

          public static robotState getRobotState(){
               return m_robotState;
          }

          public static void setRobotState(robotState p_robotState){
               m_robotState = p_robotState;
          }
     }
}

