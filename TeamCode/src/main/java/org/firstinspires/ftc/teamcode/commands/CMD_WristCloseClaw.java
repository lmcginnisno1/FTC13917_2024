package org.firstinspires.ftc.teamcode.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_WristCloseClaw extends CommandBase {
    SUB_Wrist m_wrist;
    private double m_waitTime = 300;
    private ElapsedTime m_runtime = new ElapsedTime();
    public CMD_WristCloseClaw(SUB_Wrist p_wrist) {
        m_wrist = p_wrist;
    }

    @Override
    public void initialize() {
        m_wrist.closeClawA();
        m_wrist.closeClawB();
        m_runtime.reset();
    }



    @Override
    public boolean isFinished() { return m_runtime.milliseconds()>m_waitTime; }
}
