package org.firstinspires.ftc.teamcode;

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

          public static final double kPPR = 3895.9;// Calculated using a 139:1 ratio motor
//          public static final double kPPR = 2786.2;// Calculated using a 99.5:1 ratio motor
          public static final double kTicksToDegrees = kPPR / 360;

          //Shoulder Positions

          public static final double kHome = 0;
          public static final double kUp = 100;
          public static final double kClimb = 50;
          public static final double[] kReadyToIntakePosition = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
          public static final double[] kIntakePickupPosition = {0.0, 0.0, 0.0, 30.0, 32.50, 32.50};
          public static final double[] kReadyToDeployPosition = {0.0
                  ,120.0
                  ,105.0
                  ,100
                  ,97.5
                  ,100
                  ,102.5
                  ,102.5
                  ,110};// level 8 was 120
//          public static final double kPushIntoBackdrop = 10;
          public static final double[] kPushIntoBackdrop = {0
               ,10 //1
               ,10 //2
               ,10 //3
               ,10 //4
               ,10 //5
               ,10 //6
               ,10 //7
               ,10 //8
          };
          public static final double kPreDeployment = 50;
          public static final double kDroneLaunch = 55;
          public static final double kUpright = 200;
          public static final double kBackOff = 15;


     }
     public static final class ElbowConstants{
          public static final double kMaxVelocityDegreesPerSecond = 720;//360
          public static final double kMaxAccelerationDegreesPerSecond = 1200;//360
          public static final double kOffsetDegrees = 0;
          public static final double kMaxPower = 1;
          public static final double kP = 5.0;//good but slow
          public static final double kI = 0;
          public static final double kD = 0;
          public static final double kF = 0;

          public static final double kPPR = 2786.2;// Calculated using a 99.5:1 ratio motor
          public static final double kTicksToDegrees = kPPR / 360;

          //Elbow Positions
          //Bigger absolute number = farther from robot, - = back  of robot
          public static final double kHome = 0;
          public static final double kParallel = -45; //-20;
          public static final double[] kReadyToIntakePosition =  {0.0,0.0,-20,-20.0,-20.0,-20.0};
          public static final double[] kIntakePickupPosition = {0.0,0.0,54.0,54.0,54.0,54.0};
          public static final double[] kReadyToDeployPosition = {0.0, -77.5, -90, -110, -120.0, -135, -150, -170, -220};//0, -75, -87.5, -95, -110
          public static final double[] kPushIntoBackdrop = {0
                  ,7 //1
                  ,7 //2
                  ,5 //3
                  ,5 //4
                  ,5 //5
                  ,5 //6
                  ,5 //7
                  ,5 //8
          };
          public static final double kBackOff = 5;
//          public static final double kPushIntoBackdrop = 5;
     }
     public static final class WristConstants{
          public static final double kIntakePivotHome = 0.5;
          public static final double kIntakePivotDeploy = 0.18;
          public static final double[] kPivotRotation = {
               0.0,//right slant
               0.18,//horizontal
               0.325,//left slant
               0.5,//vertical
               0.65,//right slant reversed
               0.8,//horizontal reversed
               0.95,//left slant reversed
          };
          public static final double kPincherOpen = 0.10;
          public static final double kPincherClose = 0.85;
          public static final double kPincherDoubleGrab = .55;
          public static final double kWristOffset = 0;
          // Wrist Positions

          //starts from 0 pixel stack and goes to level 5
          public static final double[] kReadyToIntakePosition = {0.0,0.0,.4,0.0,0.0,0.0};
          public static final double[] kIntakePickupPosition = {0.0,0.51,0.51,0.51,0.51,0.51};
          public static final double kHome = 0.375;
          //larger value points farther down when deploying
          public static final double[] kReadyToDeployPosition = {0.0, 0.0, 0.11, 0.2, 0.25, 0.27, 0.3, 0.4, 0.5};//0, .25, .35, .45, .5
          public static final double kBackOff = 0.25;
     }

     public static final class droneLauncherConstants{
          public static final double kClosed = .225;
          public static final double kOpen = .5;

     }

     public static final class IntakeConstants{
          public static final double kConveyorOn = 1.0;
          public static final double kConveyorOff = 0.0;
          public static final double kConveyorReverse = -1.0;

          public static final double kPivotServoHome = 0.1;
          public static final double kPivotServoFiveStack = 0.35;
          public static final double kPivotServoOut = 0.55;

          public static final double kMiddleServoOn = 0.5;
          public static final double kMiddleServoOff = 0.0;
          public static final double kMiddleServoReverse = -1.0;
     }
}

