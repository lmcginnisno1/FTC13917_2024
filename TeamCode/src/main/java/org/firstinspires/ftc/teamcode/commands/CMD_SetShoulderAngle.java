package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;

public class CMD_SetShoulderAngle extends CommandBase {
    SUB_Shoulder m_shoulder;
    double m_angle;
    int m_tolerance = 1;

    public CMD_SetShoulderAngle(SUB_Shoulder p_shoulder, double p_angle) {
        m_shoulder = p_shoulder;
        m_angle = p_angle;
//        addRequirements(m_shoulder);
    }

    public CMD_SetShoulderAngle setTolerance(int p_tolerance) {
        m_tolerance = p_tolerance;
        return this;
    }

    @Override
    public void initialize(){
        m_shoulder.setTargetAngle(m_angle);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(m_shoulder.getAngle() - m_angle) <= m_tolerance;
    }
}
