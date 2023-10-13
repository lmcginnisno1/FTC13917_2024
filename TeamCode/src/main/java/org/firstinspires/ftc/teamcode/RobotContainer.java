package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.checkerframework.checker.units.qual.C;
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
    public SUB_Intake m_intake;
    public SUB_OpenCvCamera frontCamera;

    public RobotContainer(OpMode p_opMode) {
        SampleMecanumDrive drivebase = new SampleMecanumDrive(p_opMode.hardwareMap);
        drivetrain = new MecanumDriveSubsystem(drivebase, true);

        m_wrist = new SUB_Wrist(p_opMode, "wristservo", "clawa", "clawb");
        m_elbow = new SUB_Elbow(p_opMode, "elbowmotor");
        m_shoulder = new SUB_Shoulder(p_opMode, "rightmotor", "leftmotor");
//        m_intake = new SUB_Intake(p_opMode, "intakemotor");
//        frontCamera = new SUB_OpenCvCamera(p_opMode, "FrontCam");
    };

    public void run() {
        m_robot.run();
    }

    public void reset() {
        m_robot.reset();
    }

    SelectCommand armLevel = new SelectCommand(
            // the first parameter is a map of commands
            new HashMap<Object, Command>() {{
                put(Constants.ArmConstants.ArmLevel.Home, new CMD_ArmSetLevelHome(m_shoulder, m_elbow, m_wrist));
                put(Constants.ArmConstants.ArmLevel.One, new CMD_ArmSetLevelOne(m_shoulder, m_elbow, m_wrist));
            }},
            Constants.ArmConstants::getArmLevel
    );

    SelectCommand robotStateForward = new SelectCommand(
            // the first parameter is a map of commands
            new HashMap<Object, Command>() {{
                put(Constants.robotConstants.robotState.Home, new CMD_ArmSetReadyIntake(m_shoulder, m_elbow, m_wrist));
                put(Constants.robotConstants.robotState.Ready2Intake, new CMD_ArmDropIntake(m_shoulder, m_elbow, m_wrist));
                put(Constants.robotConstants.robotState.Intake, new CMD_ArmSetStow(m_shoulder, m_elbow, m_wrist));
                put(Constants.robotConstants.robotState.Stow, new CMD_ArmSetLevelOne(m_shoulder, m_elbow, m_wrist));
                put(Constants.robotConstants.robotState.Score, new CMD_ArmSetLevelHome(m_shoulder, m_elbow, m_wrist));
            }},
            Constants.robotConstants::getRobotState
    );

    SelectCommand robotStateBackwards = new SelectCommand(
            new HashMap<Object, Command>() {{
                put(Constants.robotConstants.robotState.Stow, new CMD_ArmSetReadyIntake(m_shoulder, m_elbow, m_wrist));
                put(Constants.robotConstants.robotState.Intake, new CMD_ArmSetReadyIntake(m_shoulder, m_elbow, m_wrist));
                put(Constants.robotConstants.robotState.Score, new CMD_ArmSetStow(m_shoulder, m_elbow, m_wrist));
            }},
            Constants.robotConstants::getRobotState
    );

    public void schedule(Command... commands) {
            m_robot.schedule(commands);
    }
}

