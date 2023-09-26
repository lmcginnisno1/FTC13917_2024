package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robot_Auto;
import org.firstinspires.ftc.teamcode.commands.RR_TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.Command;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.*;

import org.firstinspires.ftc.teamcode.commands.*;

@Autonomous(name = "Auto Red Right- 3 cones", group = "Auto Red",
        preselectTeleOp = "Driver Red (RC)")
public class AUTO_Red_Right extends Robot_Auto {

    private int m_Analysis;
    private SequentialCommandGroup prebuildTasks_1;

    @Override
    public void prebuildTasks() {
        // run these tasks now

    }

    @Override
    public SequentialCommandGroup buildTasks(int p_Analysis) {
        m_Analysis = p_Analysis;
        SequentialCommandGroup completetasks = new SequentialCommandGroup();
        completetasks.addCommands(prebuildTasks_1);

        m_robot.schedule(completetasks);
        return completetasks;
    }



}


