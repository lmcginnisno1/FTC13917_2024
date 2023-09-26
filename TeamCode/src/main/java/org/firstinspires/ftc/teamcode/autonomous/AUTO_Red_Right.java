package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Robot_Auto;
import org.firstinspires.ftc.teamcode.commands.RR_TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.Command;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.*;

import org.firstinspires.ftc.teamcode.commands.*;

@Autonomous(name = "Auto Red Right- 3 cones", group = "Auto Red",
        preselectTeleOp = "Driver Red (RC)")
public class AUTO_Red_Right extends Robot_Auto {

    private int m_Analysis;
    private SequentialCommandGroup placePurplePixel;

    @Override
    public void prebuildTasks() {
        // run these tasks now
    }

    @Override
    public SequentialCommandGroup buildTasks(int p_Analysis) {
        m_Analysis = p_Analysis;
        SequentialCommandGroup completetasks = new SequentialCommandGroup();
        completetasks.addCommands(placePurplePixel);

        m_robot.schedule(completetasks);
        return completetasks;
    }

    private SequentialCommandGroup placePurplePixel(){
        SequentialCommandGroup cmds = new SequentialCommandGroup();
        Pose2d m_initialPose = new Pose2d(-36, -64, Math.toRadians(90));

        Trajectory LeftSpikeMark = m_robot.drivetrain.trajectoryBuilder(m_initialPose, true)
        .splineTo(new Vector2d(-24, -30), Math.toRadians(90))
        .build();

        Trajectory MiddleSpikeMark = m_robot.drivetrain.trajectoryBuilder(m_initialPose, true)
                .splineTo(new Vector2d(-36, -26), Math.toRadians(90))
                .build();

        Trajectory RightSpikeMark = m_robot.drivetrain.trajectoryBuilder(m_initialPose, true)
                .splineTo(new Vector2d(-48, -30), Math.toRadians(90))
                .build();

        switch (m_Analysis){
            case 1: cmds.addCommands(
                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, LeftSpikeMark)
                    ,new Sleep(100)
//                    ,new CMD_IntakeReverse(m_robot.m_intake)
//                    ,new Sleep(200)
//                    ,new CMD_IntakeOff(m_robot.m_intake);
            );

            case 2: cmds.addCommands(
                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, MiddleSpikeMark)
                    ,new Sleep(100)
//                    ,new CMD_IntakeReverse(m_robot.m_intake)
//                    ,new Sleep(200)
//                    ,new CMD_IntakeOff(m_robot.m_intake);
            );

            case 3: cmds.addCommands(
                    new RR_TrajectoryFollowerCommand(m_robot.drivetrain, RightSpikeMark)
                    ,new Sleep(100)
//                    ,new CMD_IntakeReverse(m_robot.m_intake)
//                    ,new Sleep(200)
//                    ,new CMD_IntakeOff(m_robot.m_intake);
            );
        }

        return cmds;
    }

}


