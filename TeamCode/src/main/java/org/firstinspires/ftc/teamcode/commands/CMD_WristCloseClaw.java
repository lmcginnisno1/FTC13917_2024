package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_WristCloseClaw extends SequentialCommandGroup {
    public CMD_WristCloseClaw(SUB_Wrist p_wrist) {
        addCommands(
                new InstantCommand(()-> p_wrist.closeClawA())
                ,new Sleep(200)
                ,new InstantCommand(()-> p_wrist.closeClawB())
                ,new Sleep(100)
        );
    }
}
