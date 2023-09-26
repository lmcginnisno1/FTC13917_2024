package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.pipelines.Pipeline_DetectJunctionCenter;
import org.firstinspires.ftc.teamcode.pipelines.Pipeline_Detect3ColorInSamePlace;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class Robot_Auto extends LinearOpMode {

    public RobotContainer m_robot;
    public int m_Analysis;
    SequentialCommandGroup tasks;
    Pipeline_Detect3ColorInSamePlace m_detectSignalCone;

    private ElapsedTime m_runTime = new ElapsedTime();
    // GlobalVariables globalVariables = GlobalVariables.getInstance();

    public void initialize() {


        telemetry.clearAll();
        telemetry.addData("init complete", "BaseRobot");
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initializeSubsystems();

        prebuildTasks();

        // waitForStart();
        while (!opModeIsActive() && !isStopRequested()) {
            m_robot.run(); // run the scheduler

            telemetry.addData("Analysis: ",m_detectSignalCone.getAnalysis());
            telemetry.update();
        }

        m_Analysis = m_detectSignalCone.getAnalysis();
//        m_Analysis = 1;
        buildTasks(m_Analysis);

        // switch pipeline to detect junction center
        m_robot.frontCamera.setPipeline(m_detectSignalCone);

        m_runTime.reset();

        while (!isStopRequested() && opModeIsActive()) {
            m_robot.run(); // run the scheduler

            m_robot.drivetrain.update();
            Pose2d poseEstimate = m_robot.drivetrain.getPoseEstimate();
            telemetry.addData("Position:","x[%3.2f] y[%3.2f] heading(%3.2f)", poseEstimate.getX(), poseEstimate.getY(), poseEstimate.getHeading());
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
        m_detectSignalCone = new Pipeline_Detect3ColorInSamePlace();

        m_robot.frontCamera.setPipeline(m_detectSignalCone);
        m_robot.frontCamera.startStreaming(640,480);

    }

    public abstract SequentialCommandGroup buildTasks(int p_analysis);
    public abstract void prebuildTasks();

}
