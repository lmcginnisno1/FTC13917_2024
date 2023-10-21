package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.*;

import org.firstinspires.ftc.teamcode.ftclib.command.ConditionalCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SelectCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.button.Button;
import org.firstinspires.ftc.teamcode.ftclib.command.button.GamepadButton;
import org.firstinspires.ftc.teamcode.ftclib.gamepad.GamepadEx;
import org.firstinspires.ftc.teamcode.ftclib.gamepad.GamepadKeys;
import org.firstinspires.ftc.teamcode.ftclib.command.Command;
import org.firstinspires.ftc.teamcode.subsystems.*;
import org.firstinspires.ftc.teamcode.commands.button.*;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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

            //timer
            telemetry.addData("time", m_runTime.time(TimeUnit.SECONDS));

            //pixelGuide
            telemetry.addData("piel Guide State", m_robot.m_pixelGuide.getState());

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

        m_robot.m_wrist.moveWrist(Constants.WristConstants.kHome);
        m_robot.m_wrist.openClawA();
        m_robot.m_wrist.openClawB();


        configureButtonBindings();
        m_robot.m_variables.setRobotState(GlobalVariables.RobotState.Home);
    }

    public void configureButtonBindings() {
         Trajectory m_nextSpotLeft = m_robot.drivetrain.trajectoryBuilder(m_robot.drivetrain.getPoseEstimate(), false)
                 .lineTo(new Vector2d(m_robot.drivetrain.getPoseEstimate().getX(), m_robot.drivetrain.getPoseEstimate().getY() + 2))
                 .build();

         SelectCommand deployArm = new SelectCommand(
                 new HashMap<Object, Command>(){{
                    put(GlobalVariables.ScoringLevel.One, new CMD_ArmSetLevelOne(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank));
                    put(GlobalVariables.ScoringLevel.Two, new CMD_ArmSetLevelTwo(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank));
                    put(GlobalVariables.ScoringLevel.Three, new CMD_ArmSetLevelThree(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank));
                 }},
                 m_robot.m_variables::getScoringLevel
         );


         SelectCommand toggleArmUp = new SelectCommand(
                new HashMap<Object, Command>() {{
                    put(GlobalVariables.ScoringLevel.One, new SequentialCommandGroup(
                            new CMD_SetScoringLevel(m_robot.m_variables, GlobalVariables.ScoringLevel.Two),
                            new CMD_ArmSetLevelTwo(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                    ));
                    put(GlobalVariables.ScoringLevel.Two, new SequentialCommandGroup(
                            new CMD_SetScoringLevel(m_robot.m_variables, GlobalVariables.ScoringLevel.Three),
                            new CMD_ArmSetLevelThree(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                    ));
                    put(GlobalVariables.ScoringLevel.Three, (new InstantCommand(()->m_robot.m_blank.doNothing())));
                }},
                m_robot.m_variables::getScoringLevel
         );

        SelectCommand toggleArmDown = new SelectCommand(
                new HashMap<Object, Command>() {{
                    put(GlobalVariables.ScoringLevel.Three, new SequentialCommandGroup(
                            new CMD_SetScoringLevel(m_robot.m_variables, GlobalVariables.ScoringLevel.Two),
                            new CMD_ArmSetLevelTwo(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
                    ));
                    put(GlobalVariables.ScoringLevel.Two, new SequentialCommandGroup(
                            new CMD_SetScoringLevel(m_robot.m_variables, GlobalVariables.ScoringLevel.One),
                            new CMD_ArmSetLevelOne(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist,m_robot.m_blank)
                    ));
                    put(GlobalVariables.ScoringLevel.One, new InstantCommand(()-> m_robot.m_blank.doNothing()));
                }},
                m_robot.m_variables::getScoringLevel
        );

        AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.BACK, new SequentialCommandGroup(
           new CMD_PrepareToClimb(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank),
           new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.Climb)
        ));

        AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.Y, new ConditionalCommand(
          new CMD_Climb(m_robot.m_shoulder),
          new InstantCommand(()-> m_robot.m_blank.doNothing()),
          ()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Climb)
        ));

        AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.LEFT_BUMPER, new ConditionalCommand(
           new CMD_WristReleaseClaw(m_robot.m_wrist),
           new CMD_WristReleaseOutsideClaw(m_robot.m_wrist),
           () -> m_robot.m_wrist.getIsClawBOpen()
        ));

        AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.RIGHT_BUMPER, new CMD_Upright(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank));

        AddButtonCommandNoInt(m_driverOp, GamepadKeys.Button.BACK, new InstantCommand(()->  m_robot.m_droneLauncher.close()));


         //driver 2

        AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.LEFT_BUMPER, new ConditionalCommand(
                toggleArmDown,
                new InstantCommand(()-> m_robot.m_blank.doNothing()),
                ()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Score)
        ));
        AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.RIGHT_BUMPER, new ConditionalCommand(
                toggleArmUp,
                new InstantCommand(()-> m_robot.m_blank.doNothing()),
                ()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Score)
        ));

         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.A, new ConditionalCommand(
               new SequentialCommandGroup(
                    new CMD_ArmSetReadyIntake(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank),
                    new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.ReadyToIntake)
               ),
               new InstantCommand(()-> m_robot.m_blank.doNothing()),
               ()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Home) || m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Stow)
         ));

         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.B, new ConditionalCommand(
               new SequentialCommandGroup(
                    new CMD_ArmDropIntake(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank),
                    new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.Stow)
               ),
               new InstantCommand(()-> m_robot.m_blank.doNothing()),
               ()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToIntake)
         ));

         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.X, new ConditionalCommand(
                 new SequentialCommandGroup(
                    deployArm,
                    new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.Score)
                 ),
                 new InstantCommand(()-> m_robot.m_blank.doNothing()),
                 ()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Stow)
         ));

         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.Y, new ConditionalCommand(
                 new SequentialCommandGroup(
                    new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank),
                    new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.Home)
                 ),
                 new InstantCommand(()-> m_robot.m_blank.doNothing()),
                 ()-> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.Score)
         ));

         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.START, new SequentialCommandGroup(
                 new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.ReadyToLaunch),
                 new CMD_ReadyToLaunch(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank)
         ));

         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.DPAD_LEFT,
                 new ConditionalCommand(
                         new InstantCommand(()-> m_robot.m_droneLauncher.releaseTheDrone()),
                         new InstantCommand(()-> m_robot.m_blank.doNothing()),
                         () -> m_robot.m_variables.isRobotState(GlobalVariables.RobotState.ReadyToLaunch)
                 )
         );

         AddButtonCommandNoInt(m_toolOp, GamepadKeys.Button.DPAD_RIGHT, new SequentialCommandGroup(
               new CMD_ArmSetLevelHome(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank),
               new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.Stow)
         ));


         //trigger
        addTriggerCommand(m_robot.m_pixelGuide, m_robot.m_variables, "readyToIntake",
                new SequentialCommandGroup(
                   new CMD_ArmDropIntake(m_robot.m_shoulder, m_robot.m_elbow, m_robot.m_wrist, m_robot.m_blank),
                   new CMD_SetRobotState(m_robot.m_variables, GlobalVariables.RobotState.Stow)
                ));

    }

    public void AddButtonCommand(GamepadEx gamepad, GamepadKeys.Button button
            , TriggerSubsystemBase m_FiniteStateMachine, String m_robotState
            , Command command) {

        (new GamepadButton(gamepad, button))
                .and(new TRG_Subsystem(m_FiniteStateMachine, m_robotState))
                .whenActive(command);
    }

     public void addTriggerCommand(TriggerSensorBase sensor
             , TriggerSubsystemBase m_FiniteStateMachine, String m_robotState
             , Command command) {

          (new TRG_Sensor(sensor))
                  .and(new TRG_Subsystem(m_FiniteStateMachine, m_robotState))
                  .whenActive(command);
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