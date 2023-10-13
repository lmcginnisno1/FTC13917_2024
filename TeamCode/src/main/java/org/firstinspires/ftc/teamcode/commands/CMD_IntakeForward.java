package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Intake;

public class CMD_IntakeForward extends CommandBase {
     SUB_Intake m_intake;
     public CMD_IntakeForward(SUB_Intake p_intake) {
          m_intake = p_intake;
     }

     @Override
     public void initialize() {
          m_intake.intakeForward();
     }

     @Override
     public boolean isFinished() { return true; }
}
