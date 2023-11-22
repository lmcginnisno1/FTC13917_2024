package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;

public class CMD_ShoulderSetReadyToDeploy extends CommandBase {
     SUB_Shoulder m_shoulder;
     GlobalVariables m_variables;
     boolean m_noWait;
     public CMD_ShoulderSetReadyToDeploy(SUB_Shoulder p_shoulder, GlobalVariables p_variables){
          m_shoulder = p_shoulder;
          m_variables = p_variables;
     }

     public CMD_ShoulderSetReadyToDeploy noWait(){
          m_noWait = true;
          return this;
     }

     @Override
     public void initialize(){
          m_shoulder.setTargetAngle(Constants.ShoulderConstants.kReadyToDeployPosition[m_variables.getScoringLevel()]);
     }

     @Override
     public boolean isFinished(){
          return Math.abs(m_shoulder.getAngle() -
                  Constants.ShoulderConstants.kReadyToDeployPosition[m_variables.getScoringLevel()]
                  ) <= 5 || m_noWait;
     }
}
