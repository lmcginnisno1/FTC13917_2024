package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;

public class CMD_DecreaseIntakeLevel extends CommandBase {
     GlobalVariables m_variables;
     public CMD_DecreaseIntakeLevel(GlobalVariables p_variables){
          m_variables = p_variables;
     }

     @Override
     public void initialize(){
          switch (m_variables.getIntakeLevel()){
               case Five:
                    m_variables.setIntakeLevel(GlobalVariables.IntakeLevel.Four);
                    break;
               case Four:
                    m_variables.setIntakeLevel(GlobalVariables.IntakeLevel.Three);
                    break;
               case Three:
                    m_variables.setIntakeLevel(GlobalVariables.IntakeLevel.Two);
               default: break;
          }
     }

     @Override
     public boolean isFinished(){
          return true;
     }
}
