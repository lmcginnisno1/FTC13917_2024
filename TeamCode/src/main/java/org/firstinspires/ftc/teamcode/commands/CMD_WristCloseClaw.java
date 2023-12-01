package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.ftclib.command.InstantCommand;
import org.firstinspires.ftc.teamcode.ftclib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_WristCloseClaw extends SequentialCommandGroup {
    public CMD_WristCloseClaw(SUB_Wrist p_wrist) {
        addCommands(
                new InstantCommand(()-> p_wrist.closePincher())
                ,new Sleep(100)
        );
    }

    public CMD_WristCloseClaw(SUB_Wrist p_wrist, boolean twopixels) {
        addCommands(
                new InstantCommand(()-> p_wrist.IntakePincherDoubleGrab())
                ,new Sleep(100)
        );
    }
}
