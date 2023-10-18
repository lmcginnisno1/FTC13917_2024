package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptSoundsASJava;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.ftclib.command.ParallelCommandGroup;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_PrepareToClimb extends ParallelCommandGroup {
    public CMD_PrepareToClimb(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist, SUB_Blank p_blank){
        addRequirements(p_blank);
        addCommands(
            new CMD_SetShoulderAngle(p_shoulder, Constants.ShoulderConstants.kUp),
            new CMD_SetElbowAngle(p_elbow, Constants.ElbowConstants.kParallel),
            new CMD_SetWristPosition(p_wrist, Constants.WristConstants.kHome)
        );
    }
}
