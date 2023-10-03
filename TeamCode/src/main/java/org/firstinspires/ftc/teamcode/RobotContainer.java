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
    public SUB_Arm m_arm;

    public SUB_OpenCvCamera frontCamera;

    public RobotContainer(OpMode p_opMode) {
        SampleMecanumDrive drivebase = new SampleMecanumDrive(p_opMode.hardwareMap);
        drivetrain = new MecanumDriveSubsystem(drivebase, true);
        m_arm = new SUB_Arm(p_opMode, "shouldemotor", "elbowmotor",
                "wristservo");

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
                put(Constants.ArmConstants.ArmLevel.HomeLevel, new CMD_ArmSetLevelHome(m_arm));
                put(Constants.ArmConstants.ArmLevel.Level1, new CMD_ArmSetLevelOne(m_arm));
                put(Constants.ArmConstants.ArmLevel.Level2, new CMD_ArmSetLevelThree(m_arm));
                put(Constants.ArmConstants.ArmLevel.Level3, new CMD_ArmSetLevelThree(m_arm));
            }},
            Constants.ArmConstants::getArmLevel
    );

    public void schedule(Command... commands) {
            m_robot.schedule(commands);
    }
}

