package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_SetWristPosition extends CommandBase {
    SUB_Wrist m_wrist;
    double m_pos;
    public CMD_SetWristPosition(SUB_Wrist p_wrist, double pos) {
        m_wrist = p_wrist;
        m_pos = pos;
    }

    @Override
    public void initialize() {
        m_wrist.setPosition(m_pos);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
