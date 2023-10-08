package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.ftclib.command.Command;
import org.firstinspires.ftc.teamcode.ftclib.command.Robot;
import org.firstinspires.ftc.teamcode.ftclib.command.SelectCommand;
import org.firstinspires.ftc.teamcode.subsystems.*;
import org.firstinspires.ftc.teamcode.commands.*;

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

    public SUB_OpenCvCamera frontCamera;

    public RobotContainer(OpMode p_opMode) {
        SampleMecanumDrive drivebase = new SampleMecanumDrive(p_opMode.hardwareMap);
        drivetrain = new MecanumDriveSubsystem(drivebase, true);

        m_wrist = new SUB_Wrist(p_opMode, "wristservo");
        m_elbow = new SUB_Elbow(p_opMode, "elbowmotor");
        m_shoulder = new SUB_Shoulder(p_opMode, "shouldermotor");

//        frontCamera = new SUB_OpenCvCamera(p_opMode, "FrontCam");

    };

    public void run() {
        m_robot.run();
    }

    public void reset() {
        m_robot.reset();
    }
//    SelectCommand armLevel = new SelectCommand(
//            // the first parameter is a map of commands
//            new HashMap<Object, Command>() {{
//                put(Constants.ArmConstants.ArmLevel.Home, new CMD_ArmSetLevelHome(m_shoulder, m_elbow, m_wrist));
//                put(Constants.ArmConstants.ArmLevel.One, new CMD_ArmSetLevelOne(m_shoulder, m_elbow, m_wrist));
//                put(Constants.ArmConstants.ArmLevel.Two, new CMD_ArmSetLevelThree(m_shoulder, m_elbow, m_wrist));
//                put(Constants.ArmConstants.ArmLevel.Three, new CMD_ArmSetLevelThree(m_shoulder, m_elbow, m_wrist));
//            }},
//            Constants.ArmConstants::getArmLevel
//    );

    public void schedule(Command... commands) {
            m_robot.schedule(commands);
    }
}

