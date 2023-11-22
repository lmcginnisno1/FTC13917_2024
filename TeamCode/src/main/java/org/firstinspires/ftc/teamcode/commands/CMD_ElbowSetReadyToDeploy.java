package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;

public class CMD_ElbowSetReadyToDeploy extends CommandBase {
     SUB_Elbow m_elbow;
     GlobalVariables m_variables;
     boolean m_noWait;
     public CMD_ElbowSetReadyToDeploy(SUB_Elbow p_elbow, GlobalVariables p_variables){
          m_elbow = p_elbow;
          m_variables = p_variables;
     }

     public CMD_ElbowSetReadyToDeploy noWait(){
          m_noWait = true;
          return this;
     }

     @Override
     public void initialize(){
          m_elbow.setTargetAngle(Constants.ElbowConstants.kReadyToDeployPosition[m_variables.getScoringLevel()]);
     }

     @Override
     public boolean isFinished(){
          return Math.abs(m_elbow.getAngle() -
                  Constants.ElbowConstants.kReadyToDeployPosition[m_variables.getScoringLevel()]
          ) <= 5 || m_noWait;
     }
}
