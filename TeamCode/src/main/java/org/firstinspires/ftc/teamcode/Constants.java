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
          public static final double kClimb = 75;
//          public static final double[] kReadyToIntakePosition = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
         public static final double kReadyToIntakePosition = 0.0;

//         public static final double[] kIntakePickupPosition = {0.0, 0.0, 0.0, 30.0, 32.50, 32.50};
         public static final double kIntakePickupPosition = 0.0;

         public static final double[] kReadyToDeployPosition = {0.0
                  ,105.0//110
                  ,100.0//100
                  ,85.0//97.5
                  ,85.0//97.5
                  ,97.5//97.5
                  ,100.0//100
                  ,110.0//120
                  ,110.0};//120
          public static final double[] kPushIntoBackdrop = {0
               ,115.0 //1
               ,107.5 //2, -10
               ,107.5//3, -10
               ,105.0//4, -10
               ,100.0 //5, -10
               ,115.0 //6, -10
               ,125.0//7, -10
               ,115 //8, -10
          };
//          public static final double[] kPushIntoBackdrop = {0
//                  ,10 //1
//                  ,10 //2
//                  ,10 //3
//                  ,10 //4
//                  ,10 //5
//                  ,10 //6
//                  ,10 //7
//                  ,10 //8
//          };
          public static final double kPreDeployment = 50;
          public static final double kDroneLaunch = 55;
          public static final double kUpright = 200;
          public static final double kBackOff = 20;


     }
     public static final class ElbowConstants{
          public static final double kMaxVelocityDegreesPerSecond = 720;//720
          public static final double kMaxAccelerationDegreesPerSecond = 2400;//1200
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
//          public static final double[] kReadyToIntakePosition =  {-15.0,-15.0,-15.0,-15.0,-15.0,-15.0};
         public static final double kReadyToIntakePosition =  -15.0;
//         public static final double[] kIntakePickupPosition = {54.0,54.0,54.0,54.0,54.0,54.0};
         public static final double kIntakePickupPosition = 54.0;

         public static final double[] kReadyToDeployPosition = {0.0, -50, -65, -80, -100, -120, -155, -185, -200};
//        public static final double[] kReadyToDeployPosition = {0.0, -50, -65, -85, -100, -125, -140, -185, -200};
          public static final double[] kPushIntoBackdrop = {0
                  ,-55 //1
                  ,-75 //2
                  ,-95 //3
                  ,-122.5 //4
                  ,-120 //5
                  ,-155 //6
                  ,-185//7
                  ,-210 //8
          };
//          public static final double[] kPushIntoBackdrop = {0
//                  ,7 //1
//                  ,7 //2
//                  ,5 //3
//                  ,5 //4
//                  ,5 //5
//                  ,5 //6
//                  ,0 //7
//                  ,0 //8
//          };
          public static final double kBackOff = 5;
          public static final double kBackOffHighLevels = -10;
//          public static final double kPushIntoBackdrop = 5;
          public static final double kLiftOffConveyor = -10;
     }
     public static final class WristConstants{
          public static final double kPivotHome = 0.5;
          public static final double kPivotDeploy = 0.18;
          public static final double[] kPivotRotation = {
               0.0,//right slant
               0.18,//horizontal
               0.325,//left slant
               0.5,//vertical
               0.65,//right slant reversed
               0.8,//horizontal reversed
               0.95,//left slant reversed
          };
          public static final double kPincherOpen = 0.1;//.15
          public static final double kPincherClose = 1.0;//.85
          public static final double kPincherDoubleGrab = .67;//.55
          public static final double kWristOffset = 0;
          // Wrist Positions

          //starts from 0 pixel stack and goes to level 5
//          public static final double[] kReadyToIntakePosition = {0.0,0.0,.75,0.0,0.0,0.0};
         public static final double kReadyToIntakePosition = .75;
//         public static final double[] kIntakePickupPosition = {0.0,0.835,0.835,0.835,0.835,0.835};
         public static final double kIntakePickupPosition = 0.835;
          public static final double kHome = 0.7;//.375
          //larger value points farther down when deploying
          public static final double[] kReadyToDeployPosition = {0.0, 0.25, 0.28, 0.38, 0.47, 0.53, 0.61, 0.68, 0.755};
//        public static final double[] kReadyToDeployPosition = {0.0, 0.29, 0.4, 0.5, 0.59, 0.65, 0.73, 0.8, 0.875};
          public static final double kBackOff = 0.15;
          public static final double[] kPushIntoBackDrop = {0.0, .3, .3, .3, .3, .3, .3, .3};
     }

     public static final class droneLauncherConstants{
          public static final double kClosed = .225;
          public static final double kOpen = .5;

     }

     public static final class IntakeConstants{
          public static final double kConveyorOn = 1.0;
          public static final double kConveyorOff = 0.0;
          public static final double kConveyorReverse = -1.0;

          public static final double kPivotServoHome = 0.25;//.1
          public static final double kPivotServoAutoHome = .1;
          public static final double[] kPivotServoStackPosition = {0.0, 0.48, 0.43, 0.425, 0.4, 0.39};
          public static final double kPivotServoOut = 0.48;

          public static final double kMiddleServoOn = 0.5;
          public static final double kMiddleServoOff = 0.0;
          public static final double kMiddleServoReverse = -1.0;
     }

     public static final class AutoDropOffConstants{
          public static final double kX = 48;
     }

     public static final class FieldConstants{

     }
}

