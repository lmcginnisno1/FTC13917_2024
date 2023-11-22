package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_WristSetReadyToDeploy extends CommandBase {
     SUB_Wrist m_wrist;
     GlobalVariables m_variables;
     public CMD_WristSetReadyToDeploy(SUB_Wrist p_wrist, GlobalVariables p_variables){
          m_wrist = p_wrist;
          m_variables = p_variables;
     }

     @Override
     public void initialize(){
          m_wrist.setPosition(Constants.WristConstants.kReadyToDeployPosition[m_variables.getScoringLevel()]);
     }

     @Override
     public boolean isFinished(){
          return true;
     }
}
