package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.ftclib.command.Command;
import org.firstinspires.ftc.teamcode.ftclib.command.Robot;
import org.firstinspires.ftc.teamcode.subsystems.*;

public class RobotContainer {
    public boolean m_red = true;
    public Robot m_robot = new Robot();
    public MecanumDriveSubsystem drivetrain;
    public SUB_Shoulder m_shoulder;
    public SUB_Elbow m_elbow;
    public SUB_Wrist m_wrist;

    public SUB_OpenCvCamera frontCamera;

    public RobotContainer(OpMode p_opMode) {
        SampleMecanumDrive drivebase = new SampleMecanumDrive(p_opMode.hardwareMap);
        drivetrain = new MecanumDriveSubsystem(drivebase, true);
        m_shoulder = new SUB_Shoulder(p_opMode, "shouldermotor");
        m_elbow = new SUB_Elbow(p_opMode, "elbowmotor");
        m_wrist = new SUB_Wrist(p_opMode, "wristservo");

//        frontCamera = new SUB_OpenCvCamera(p_opMode, "FrontCam");

    };

    public void run() {
        m_robot.run();
    }

    public void reset() {
        m_robot.reset();
    }

    public void schedule(Command... commands) {
            m_robot.schedule(commands);
    }

}

