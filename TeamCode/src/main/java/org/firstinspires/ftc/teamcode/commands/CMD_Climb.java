package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_Climb extends ParallelCommandGroup {
    public CMD_Climb(SUB_Shoulder p_shoulder){
//        addRequirements(p_shoulder);
        addCommands(
                new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kClimb)
        );
    }
}