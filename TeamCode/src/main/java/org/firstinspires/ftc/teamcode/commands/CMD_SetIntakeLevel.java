package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;

public class CMD_SetIntakeLevel extends CommandBase{
     GlobalVariables m_variables;
     GlobalVariables.IntakeLevel m_intakeLevel;

     public CMD_SetIntakeLevel(GlobalVariables p_variables, GlobalVariables.IntakeLevel p_intakeLevel){
          m_variables = p_variables;
          m_intakeLevel = p_intakeLevel;
     }

     @Override
     public void initialize(){
          m_variables.setIntakeLevel(m_intakeLevel);
     }

     @Override
     public boolean isFinished(){
          return true;
     }
}
