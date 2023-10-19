package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;

public class CMD_SetScoringLevel extends CommandBase{
     GlobalVariables m_variables;
     GlobalVariables.ScoringLevel m_scoringLevel;

     public CMD_SetScoringLevel(GlobalVariables p_variables, GlobalVariables.ScoringLevel p_scoringLevel){
          m_variables = p_variables;
          m_scoringLevel = p_scoringLevel;
     }

     @Override
     public void initialize(){
          m_variables.setScoringLevel(m_scoringLevel);
     }

     @Override
     public boolean isFinished(){
          return true;
     }
}
