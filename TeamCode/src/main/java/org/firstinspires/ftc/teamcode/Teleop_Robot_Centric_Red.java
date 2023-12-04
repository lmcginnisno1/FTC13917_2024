package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.*;

import org.firstinspires.ftc.teamcode.ftclib.command.ConditionalCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.WaitCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.button.Button;
import org.firstinspires.ftc.teamcode.ftclib.command.button.GamepadButton;
import org.firstinspires.ftc.teamcode.ftclib.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.ftclib.gamepad.GamepadKeys;
import org.firstinspires.ftc.teamcode.ftclib.command.Command;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Teleop Red", group ="teleop red")
public class Teleop_Robot_Centric_Red extends LinearOpMode {

     public RobotContainer m_robot;
     private GamepadEx m_driverOp;
     private GamepadEx m_toolOp;
     private boolean m_setFieldCentric = false;

     private static ElapsedTime m_runTime = new ElapsedTime();
     private ElapsedTime m_releaseTimeout = new ElapsedTime();

     public void initialize() {
          telemetry.clearAll();
          telemetry.addData("init complete", true);

          m_runTime.reset();
     }

     @Override
     public void runOpMode() throws InterruptedException {
          initializeSubsystems();
          m_robot.m_shoulder.resetAngle();
          m_robot.m_elbow.resetAngle();
          // waitForStart();
          while (!opModeIsActive() && !isStopRequested()) {

               telemetry.update();
          }

          m_runTime.reset();
          while (!isStopRequested() && opModeIsActive()) {
               m_robot.run(); // run the scheduler

               m_robot.drivetrain.update();
               Pose2d poseEstimate = m_robot.drivetrain.getPoseEstimate();
               telemetry.addData("Position:","x[%3.2f] y[%3.2f] heading(%3.2f)", poseEstimate.getX(), poseEstimate.getY(), Math.toDegrees(poseEstimate.getHeading()));//
//            // Angles
//            telemetry.addData("Shoulder Angle: ", "%f", m_robot.m_shoulder.getAngle());
//            telemetry.addData("Elbow Angle: ", "%f", m_robot.m_elbow.getAngle());
//
               // States
               telemetry.addData("Robot State", m_robot.m_variables.getRobotState().name());
               telemetry.addData("Scoring Level", m_robot.m_variables.getScoringLevel());
               telemetry.addData("intake level", m_robot.m_variables.getIntakeLevel());
//
//            //trigger
//            telemetry.addData("Pixel Sensor", m_robot.m_pixelGuide.get());
//
//            //motor encoders
//               telemetry.addData("LF shoulder motor", m_robot.m_shoulder.getLeftMotorTicks());
//               telemetry.addData("RT shoulder motor", m_robot.m_shoulder.getRightMotorTicks());
//               telemetry.addData("elbow motor", m_robot.m_elbow.getElbowTicks());
//
//               telemetry.addData("rotation", m_robot.m_variables.getRotation());
//               telemetry.addData("pixel stack height", m_robot.m_variables.getStackHeight());
//               telemetry.addData("pincher open", m_robot.m_wrist.getIsPincherOpen());

               telemetry.update();
          }

          //
          endOfOpMode();
          m_robot.reset();
     }

     public void endOfOpMode() {

     }

     public void initializeSubsystems() {
          m_robot = new RobotContainer(this);
          m_driverOp = new GamepadEx(gamepad1);
          m_toolOp = new GamepadEx(gamepad2);

          setSide();

          //drivetrain initialization
          //        m_robot.drivetrain.setPoseEstimate(GlobalVariables.m_autonomousEndPose);
          m_robot.drivetrain.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(-180)));
          m_robot.drivetrain.setFieldCentric(false);
          m_robot.drivetrain.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
          m_robot.drivetrain.setDefaultCommand(new RR_MecanumDriveDefault(m_robot.drivetrain, m_driverOp,0.0,0.01));

          //camera initialization
          m_robot.m_backCamera.setProcessorDisabled(m_robot.m_autonomousDetect);

          //subsytem initialization
          m_robot.m_intake.pivotServoHome();
          m_robot.m_wrist.setPosition(Constants.WristConstants.kHome);
          m_robot.m_droneLauncher.close();

          //button bindings and global variables initialization
          configureButtonBindings();
          m_robot.m_variables.setRobotState(GlobalVariables.RobotState.Home);

     }

     public void configureButtonBindings() {

          // Driver controls

          // set ready to climb
          AddButtonCommand(m_driverOp, GamepadKeys.Button.START, new SequentialCommandGroup(
                  new CMD_PrepareToClimb(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                  , new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.Climb)
          ));

          // activate climb
          AddButtonCommand(m_driverOp, GamepadKeys.Button.DPAD_RIGHT, new ConditionalCommand(
                  new CMD_Climb(m_robot.m_shoulder),
                  new InstantCommand(),
                  () -> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Climb)
          ));

          // release pixel and return to home
//        AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.LEFT_BUMPER, new ConditionalCommand(
//                 //release the second pixel and return home
//                        new CMD_DeployPixel(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables)
//                        ,new InstantCommand()
//                        ,() -> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToDeploy)
//        ));

          // set ready to Intake level 2- normal
          AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.RIGHT_BUMPER, new ConditionalCommand(
                  new ConditionalCommand(
                          new CMD_SetReadyToIntakeOff(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables, m_robot.m_intake)
                          , new CMD_SetReadyToIntake(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables, m_robot.m_intake)
                          , () -> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToIntake)
                  )
                  ,new InstantCommand()
                  ,() -> (m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Home)
                  || m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Stow)
                  || m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToIntake))
          ));


          // Go back to home from deploying position.  If we missed the pickup, or only have 1 pixel.
          AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.BACK, new ConditionalCommand(
                  new SequentialCommandGroup(
                          new InstantCommand(() -> m_robot.m_wrist.openPincher())
                          , new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                          , new InstantCommand(() -> m_robot.m_variables.setRobotState(GlobalVariables.RobotState.Home))
                  )
                  , new InstantCommand()
                  , () -> (m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToDeploy))
          ));

          //allows driver to go to ready to intake from deploy if a pixel wasn't grabbed whilst deploying
          AddButtonCommand(m_driverOp, GamepadKeys.Button.A, new ConditionalCommand(
                  new CMD_SetReadyToIntakeFromDeploy(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables, m_robot.m_intake)
                  , new InstantCommand()
                  , () -> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToDeploy)
          ));

          AddButtonCommand(m_driverOp, GamepadKeys.Button.LEFT_BUMPER, new ConditionalCommand(
                  new CMD_AutoDropOff_Steps(m_robot.drivetrain, m_robot.m_shoulder, m_robot.m_elbow,
                          m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables)
                  , new InstantCommand()
                  , () -> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToDeploy)
          ));

          // set Ready to Deploy at the previous deployed level and update robot pose
          AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.DPAD_DOWN, new ConditionalCommand(
                  new SequentialCommandGroup(
                          new VisionUpdatePose(m_robot.m_backCamera, m_robot.drivetrain)
                          , new ConditionalCommand(
                          new CMD_SetReadyToDeploy(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables)
                          , new InstantCommand()
                          , () -> m_robot.drivetrain.getPoseEstimate().getX() <= 60
                  )
                  )
                  , new InstantCommand(),
                  () -> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Stow)
                          || m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Home)
                          || m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToDeploy)
          ));

          // level down the deploy arm
          AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.X, new ConditionalCommand(
                  new SequentialCommandGroup(
                          new InstantCommand(() -> m_robot.m_variables.decreaseScoringLevel())
                          , new InstantCommand(() -> m_robot.m_shoulder.setTargetAngle(Constants.ShoulderConstants.kReadyToDeployPosition[m_robot.m_variables.getScoringLevel()] - 10))
                          , new WaitCommand(500)
                          , new CMD_ArmChangeLevel(m_robot.m_shoulder, m_robot.m_elbow,
                          m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables)
                  )
                  , new InstantCommand()
                  , () -> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToDeploy)
          ));

          // level up the deploy arm
          AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.Y, new ConditionalCommand(
                  new SequentialCommandGroup(
                          new InstantCommand(() -> m_robot.m_variables.increaseScoringLevel())
                          , new InstantCommand(() -> m_robot.m_shoulder.setTargetAngle(Constants.ShoulderConstants.kReadyToDeployPosition[m_robot.m_variables.getScoringLevel()] - 10))
                          , new WaitCommand(500)
                          , new CMD_ArmChangeLevel(m_robot.m_shoulder, m_robot.m_elbow,
                          m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables)
                  )
                  , new InstantCommand()
                  , () -> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToDeploy)
          ));

          // launch drone
          AddButtonCommand(m_driverOp, GamepadKeys.Button.DPAD_LEFT,
                  new ConditionalCommand(
                          new SequentialCommandGroup(
                                  new InstantCommand(() -> m_robot.m_droneLauncher.releaseTheDrone())
                                  , new Sleep(500)
                                  , new InstantCommand(() -> m_robot.m_droneLauncher.close())
                          )
                          , new InstantCommand()
                          , () -> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToLaunch)
                  )
          );

          AddButtonCommand(m_driverOp, GamepadKeys.Button.LEFT_STICK_BUTTON, new SequentialCommandGroup(
                  new InstantCommand(()-> m_robot.m_variables.increaseRotation())
                  ,new InstantCommand(()-> m_robot.m_wrist.movePivotServo(Constants.WristConstants.kPivotRotation[m_robot.m_variables.getRotation()]))
          ));

          AddButtonCommand(m_driverOp, GamepadKeys.Button.RIGHT_STICK_BUTTON, new SequentialCommandGroup(
                  new InstantCommand(()-> m_robot.m_variables.decreaseStackHeight())
                  ,new InstantCommand(()-> m_robot.m_intake.setPivotPosition(Constants.IntakeConstants.kPivotServoStackPosition[m_robot.m_variables.getStackHeight()]))
          ));

          AddButtonCommand(m_driverOp, GamepadKeys.Button.B, new ConditionalCommand(
                  new ConditionalCommand(
                          new CMD_IntakeSpitOutPixels(m_robot.m_intake)
                          ,new InstantCommand()
                          ,()-> !m_robot.m_intake.getConveyorReversed()
                  )
                  ,new InstantCommand()
                  ,()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToIntake)
          ));

          AddButtonCommand(m_driverOp, GamepadKeys.Button.DPAD_UP, new ConditionalCommand(
             new InstantCommand(()-> m_robot.m_wrist.closePincher())
             ,new InstantCommand(()-> m_robot.m_wrist.openPincher())
             ,()-> m_robot.m_wrist.getIsPincherOpen()
          ));

          // Operator

          // set ready to intake level 3
//         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.A, new ConditionalCommand(
//                 new CMD_SetReadyToIntake(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables, m_robot.m_intake, 3)
//                 , new InstantCommand()
//                 ,()-> (m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToIntake))
//         ));

          // set ready to intake level 4
//         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.B, new ConditionalCommand(
//                 new SequentialCommandGroup(
//                         new CMD_SetIntakePixel(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables)
//                         ,new CMD_IntakeOff(m_robot.m_intake)
//                         ,new CMD_IntakeConveyorOff(m_robot.m_intake)
//                 )
//                 , new InstantCommand()
//                 ,()-> (m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToIntake))
//         ));

          // set ready to intake level 5
//         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.Y, new ConditionalCommand(
//                 new CMD_SetReadyToIntake(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables, m_robot.m_intake, 5)
//                 , new InstantCommand()
//                 ,()-> (m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToIntake))
//         ));

          // update robot pose
          AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.X, new VisionUpdatePose(m_robot.m_backCamera, m_robot.drivetrain));

          // upright a fallen robot
          AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.START, new SequentialCommandGroup(
                  new InstantCommand(() -> m_robot.m_variables.setRobotState(GlobalVariables.RobotState.Transitioning))
                  , new CMD_Upright(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                  , new InstantCommand(() -> m_robot.m_variables.setRobotState(GlobalVariables.RobotState.Home))
          ));

          // ready to launch drone
          AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.DPAD_UP, new SequentialCommandGroup(
                  new CMD_ReadyToLaunch(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                  , new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.ReadyToLaunch)
          ));

          // Home Arm
          AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.DPAD_DOWN, new ConditionalCommand(
                  new SequentialCommandGroup(
                          new CMD_ArmSetLevelHomeFromIntake(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                          , new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.Home)
                  )
                  , new SequentialCommandGroup(
                  new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                  , new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.Home)
          )
                  , () -> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToIntake)
          ));

          // reset arm encoder
          AddButtonCommand(m_toolOp, GamepadKeys.Button.BACK, new ConditionalCommand(
                  new ParallelCommandGroup((
                          new InstantCommand(() -> m_robot.m_shoulder.resetAngle()))
                          , new InstantCommand(() -> m_robot.m_elbow.resetAngle()))
                  , new InstantCommand()
                  , () -> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Home)
          ));
     }

     public double setSideMultiplier(double value) {
          return (m_robot.m_red ? 1 : -1) * value;
     }

     public boolean getRedSide() {
          return m_robot.m_red;
     }

     public void setRedSide() {
          m_robot.m_red = true;
     }

     public void setBlueSide() {
          m_robot.m_red = false;
     }

     public double redSide(double value) {
          return m_robot.m_red ? value : -value;
     }

     public double redSide(double value, double blue) {
          if (m_robot.m_red)
               return value;
          else
               return blue;
     }

     public double blueSide(double value) {
          return m_robot.m_red ? -value : value;
     }

     public double blueSide(double value, double red) {
          if (m_robot.m_red)
               return red;
          else
               return value;
     }

     public void setSide(){
          m_robot.m_red = true;
     }

     public Button AddButtonCommand(GamepadEx gamepad, GamepadKeys.Button button, Command command) {
          return (new GamepadButton(gamepad, button)).whenPressed(command);
     }

     public Button AddButtonCommandNoInt(GamepadEx gamepad, GamepadKeys.Button button, Command command) {
          return (new GamepadButton(gamepad, button)).whenPressed(command, false);
     }
}