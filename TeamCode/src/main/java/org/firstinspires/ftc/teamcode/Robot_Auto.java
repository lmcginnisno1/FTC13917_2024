package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.pipelines.Pipeline_DetectColorIn3PlacesCenterStage;
import org.firstinspires.ftc.teamcode.pipelines.Pipeline_DetectJunctionCenter;
import org.firstinspires.ftc.teamcode.pipelines.Pipeline_Detect3ColorInSamePlace;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class Robot_Auto extends LinearOpMode {

    public RobotContainer m_robot;
    public int m_Analysis;
    private boolean m_findRed;
    SequentialCommandGroup tasks;
    Pipeline_DetectColorIn3PlacesCenterStage m_detectColorIn3PlacesCenterstage;

    private ElapsedTime m_runTime = new ElapsedTime();

    public Robot_Auto(boolean m_findRed) {
        this.m_findRed = m_findRed;
    }

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

            telemetry.addData("Analysis: ", m_detectColorIn3PlacesCenterstage.getAnalysis());
            telemetry.update();
        }

        m_Analysis = m_detectColorIn3PlacesCenterstage.getAnalysis();
        m_Analysis = 2;
        buildTasks(m_Analysis);

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
        m_detectColorIn3PlacesCenterstage = new Pipeline_DetectColorIn3PlacesCenterStage(m_findRed);

        m_robot.frontCamera.setPipeline(m_detectColorIn3PlacesCenterstage);
        m_robot.frontCamera.startStreaming(1280,720);
    }

    public abstract SequentialCommandGroup buildTasks(int m_Analysis);
    public abstract void prebuildTasks();

}