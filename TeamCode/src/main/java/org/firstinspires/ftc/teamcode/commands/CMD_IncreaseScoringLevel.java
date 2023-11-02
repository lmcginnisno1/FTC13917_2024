package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;

public class CMD_IncreaseScoringLevel extends CommandBase {
     GlobalVariables m_variables;
     public CMD_IncreaseScoringLevel(GlobalVariables p_variables){
          m_variables = p_variables;
     }

     @Override
     public void initialize(){
          switch (m_variables.getScoringLevel()){
               case One:
                    m_variables.setScoringLevel(GlobalVariables.ScoringLevel.Two);
                    break;
               case Two:
                    m_variables.setScoringLevel(GlobalVariables.ScoringLevel.Three);
                    break;
               case Three:
                    m_variables.setScoringLevel(GlobalVariables.ScoringLevel.Four);
                    break;
               default: break;
          }
     }

     @Override
     public boolean isFinished(){
          return true;
     }
}
