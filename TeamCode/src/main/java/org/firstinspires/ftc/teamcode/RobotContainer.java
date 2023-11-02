package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.ftclib.command.Command;
import org.firstinspires.ftc.teamcode.ftclib.command.Robot;
import org.firstinspires.ftc.teamcode.ftclib.command.SelectCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.button.DigitalPort;
import org.firstinspires.ftc.teamcode.subsystems.*;
import org.firstinspires.ftc.teamcode.commands.*;
import org.firstinspires.ftc.teamcode.visionprocessors.VProcessor_DetectColorIn3PlacesCenterStage;

import java.util.HashMap;
import java.util.Map;
import java.util.HashMap.*;


public class RobotContainer {
    public boolean m_red = true;
    public Robot m_robot = new Robot();
    public MecanumDriveSubsystem drivetrain;
    public SUB_Wrist m_wrist;
    public SUB_Elbow m_elbow;
    public SUB_Shoulder m_shoulder;
    public SUB_Intake m_intake;
    public SUB_Blank m_blank;
    public SUB_DroneLauncher m_droneLauncher;
    public GlobalVariables m_variables;
    public DigitalPort m_pixelGuide;
    public SUB_VisionAprilTagsPlusAutoDetect m_backCamera;
    public VProcessor_DetectColorIn3PlacesCenterStage m_autonomousDetect;

    public RobotContainer(OpMode p_opMode) {
        SampleMecanumDrive drivebase = new SampleMecanumDrive(p_opMode.hardwareMap);
        drivetrain = new MecanumDriveSubsystem(drivebase, true);

        m_wrist = new SUB_Wrist(p_opMode, "wristservo", "clawa", "clawb");
        m_elbow = new SUB_Elbow(p_opMode, "elbowmotor");
        m_shoulder = new SUB_Shoulder(p_opMode, "rightmotor", "leftmotor");
        m_variables = new GlobalVariables();
        m_blank = new SUB_Blank();
        m_droneLauncher = new SUB_DroneLauncher(p_opMode, "dronelauncherservo");
        m_pixelGuide = new DigitalPort(p_opMode.hardwareMap.get(DigitalChannel.class, "pixelGuideSensor"));
        m_autonomousDetect = new VProcessor_DetectColorIn3PlacesCenterStage();
        m_backCamera = new SUB_VisionAprilTagsPlusAutoDetect(p_opMode, "backCamera",
                -6,
                0,
                180, m_autonomousDetect);
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

