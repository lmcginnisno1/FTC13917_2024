package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.*;

import org.firstinspires.ftc.teamcode.ftclib.command.ConditionalCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.button.Button;
import org.firstinspires.ftc.teamcode.ftclib.command.button.GamepadButton;
import org.firstinspires.ftc.teamcode.ftclib.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.ftclib.gamepad.GamepadKeys;
import org.firstinspires.ftc.teamcode.ftclib.command.Command;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Robot Teleop", group ="teleop")
public class Robot_Teleop extends LinearOpMode {

    public RobotContainer m_robot;
    private GamepadEx m_driverOp;
    private GamepadEx m_toolOp;

    private static ElapsedTime m_runTime = new ElapsedTime();

    public void initialize() {
        telemetry.clearAll();
        telemetry.addData("init complete", "BaseRobot");

        m_runTime.reset();

    }


     @Override
    public void runOpMode() throws InterruptedException {
        initializeSubsystems();

        // waitForStart();
        while (!opModeIsActive() && !isStopRequested()) {

            telemetry.update();
        }

        m_runTime.reset();
        while (!isStopRequested() && opModeIsActive()) {
            m_robot.run(); // run the scheduler

            m_robot.drivetrain.update();
            Pose2d poseEstimate = m_robot.drivetrain.getPoseEstimate();
            telemetry.addData("Position:","x[%3.2f] y[%3.2f] heading(%3.2f)", poseEstimate.getX(), poseEstimate.getY(), poseEstimate.getHeading());

            // Angles
            telemetry.addData("Shoulder Angle: ", "%f", m_robot.m_shoulder.getAngle());
            telemetry.addData("Elbow Angle: ", "%f", m_robot.m_elbow.getAngle());

            // States
            telemetry.addData("Robot State", m_robot.m_variables.getRobotState().name());
            telemetry.addData("Scoring Level", m_robot.m_variables.getScoringLevel().name());
            telemetry.addData("intake level", m_robot.m_variables.getIntakeLevel().name());

            //trigger
            telemetry.addData("Pixel Sensor", m_robot.m_pixelGuide.get());

            //motor encoders
//            telemetry.addData("left shoulder motor", m_robot.m_shoulder.getLeftMotorTicks());
//            telemetry.addData("right shoulder motor", m_robot.m_shoulder.getRightMotorTicks());
//            telemetry.addData("elbow shoulder motor", m_robot.m_elbow.getElbowTicks());

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

        m_robot.drivetrain.setFieldCentric(false);
        m_robot.drivetrain.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        m_robot.drivetrain.setDefaultCommand(new RR_MecanumDriveDefault(m_robot.drivetrain, m_driverOp,0.0,0.01));

        m_robot.m_wrist.setPosition(Constants.WristConstants.kHome);
        m_robot.m_wrist.closeClawA();
        m_robot.m_wrist.closeClawB();


        configureButtonBindings();
        m_robot.m_variables.setRobotState(GlobalVariables.RobotState.Home);
    }

    public void configureButtonBindings() {
         Trajectory m_nextSpotLeft = m_robot.drivetrain.trajectoryBuilder(m_robot.drivetrain.getPoseEstimate(), false)
                 .lineTo(new Vector2d(m_robot.drivetrain.getPoseEstimate().getX(), m_robot.drivetrain.getPoseEstimate().getY() + 2))
                 .build();

        AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.BACK, new SequentialCommandGroup(
           new CMD_PrepareToClimb(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
           ,new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.Climb)
        ));

        AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.Y, new ConditionalCommand(
          new CMD_Climb(m_robot.m_shoulder),
          new InstantCommand(),
          ()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Climb)
        ));

        AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.LEFT_BUMPER, new ConditionalCommand(
           new CMD_WristReleaseClaw(m_robot.m_wrist)
           ,new CMD_WristReleaseOutsideClaw(m_robot.m_wrist)
           ,() -> m_robot.m_wrist.getIsClawBOpen()
        ));

        AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.DPAD_LEFT, new InstantCommand()).whenHeld(new CMD_Drive(m_robot.drivetrain, 0, .1, 0));
        AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.DPAD_RIGHT, new InstantCommand()).whenHeld(new CMD_Drive(m_robot.drivetrain, 0, -.1, 0));


         AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.RIGHT_BUMPER, new CMD_Upright(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank));

        AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.BACK, new InstantCommand(()->  m_robot.m_droneLauncher.close()));


         //driver 2

        AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.LEFT_BUMPER, new ConditionalCommand(
                new SequentialCommandGroup(
                        new CMD_DecreaseScoringLevel(m_robot.m_variables)
                        ,new CMD_DeployArm(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables)
                )
                ,new InstantCommand()
                ,()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Score)
        ));
        AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.RIGHT_BUMPER, new ConditionalCommand(
                new SequentialCommandGroup(
                        new CMD_IncreaseScoringLevel(m_robot.m_variables)
                        ,new CMD_DeployArm(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables)                )
                ,new InstantCommand()
                ,()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Score)
        ));

         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.A, new ConditionalCommand(
               new SequentialCommandGroup(
                    new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.ReadyToIntake)
                    ,new CMD_SetReadyIntake(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables)
               )
               ,new InstantCommand()
               ,()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Home) || m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Stow)
         ));

         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.B, new ConditionalCommand(
               new SequentialCommandGroup(
                    new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.Stow)
                    ,new CMD_SetDropIntake(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables)
                    ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
               )
               ,new InstantCommand()
               ,()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToIntake)
         ));

         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.X, new ConditionalCommand(
                 new SequentialCommandGroup(
                    new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.Score)
                    ,new CMD_DeployArm(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables))
                 ,new InstantCommand(),
                 ()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Stow) || m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Home)
         ));

         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.Y, new ConditionalCommand(
                 new SequentialCommandGroup(
                    new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.Home)
                    ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                 )
                 ,new InstantCommand()
                 ,()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Score)
         ));

         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.START, new SequentialCommandGroup(
                 new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.ReadyToLaunch)
                 ,new CMD_ReadyToLaunch(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
         ));

         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.DPAD_LEFT,
                 new ConditionalCommand(
                         new InstantCommand(()-> m_robot.m_droneLauncher.releaseTheDrone())
                         ,new InstantCommand()
                         ,() -> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToLaunch)
                 )
         );

         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.DPAD_RIGHT, new SequentialCommandGroup(
            new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.Stow)
            ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
         ));

         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.DPAD_UP, new ConditionalCommand(
            new SequentialCommandGroup(
                    new CMD_IncreaseIntakeLevel(m_robot.m_variables)
                    ,new CMD_SetReadyIntake(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables)
            )
            ,new InstantCommand()
            ,()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToIntake)
         ));

         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.DPAD_DOWN, new ConditionalCommand(
                 new SequentialCommandGroup(
                         new CMD_DecreaseIntakeLevel(m_robot.m_variables)
                         ,new CMD_SetReadyIntake(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables)
                 )
                 ,new InstantCommand()
                 ,()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToIntake)
         ));

         //trigger
//         m_robot.m_pixelGuide.whenActive(new ConditionalCommand(
//                 new SequentialCommandGroup(
//                       new CMD_SetRobotState(m_robot.m_variables ,GlobalVariables.RobotState.Stow)
//                       ,new CMD_SetDropIntake(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank, m_robot.m_variables)
//                       ,new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
//                 )
//                 ,new InstantCommand()
//                 ,()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToIntake)
//         ));
    }

    public double setSideMultiplier(double value) {
        return (m_robot.m_red ? 1 : -1) * value;
    }

    public boolean getRedSide() {
        return (m_robot.m_red ? 1 : -1) == 1;
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

    public Button AddButtonCommand(GamepadEx gamepad, GamepadKeys.Button button, Command command) {
        return (new GamepadButton(gamepad, button)).whenPressed(command);
    }

    public Button AddButtonCommandNoInt(GamepadEx gamepad, GamepadKeys.Button button, Command command) {
        return (new GamepadButton(gamepad, button)).whenPressed(command, false);
    }

}