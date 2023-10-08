package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_WristReleaseOutsideClaw extends CommandBase {
    SUB_Wrist m_wrist;
    public CMD_WristReleaseOutsideClaw(SUB_Wrist p_wrist) {
        m_wrist = p_wrist;
    }

    @Override
    public void initialize() {
        m_wrist.openClawB();
    }

    @Override
    public boolean isFinished() { return true; }
}
