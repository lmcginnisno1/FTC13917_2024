package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_ArmBackOffBackdrop extends CommandBase {
     SUB_Shoulder m_shoulder;
     SUB_Elbow m_elbow;
     SUB_Wrist m_wrist;
     GlobalVariables m_variables;
     public CMD_ArmBackOffBackdrop(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Wrist p_wrist,
                                   SUB_Blank p_blank, GlobalVariables p_variables){
          addRequirements(p_blank);
          m_shoulder = p_shoulder;
          m_elbow = p_elbow;
          m_wrist = p_wrist;
          m_variables = p_variables;
     }

     @Override
     public void initialize(){
          m_shoulder.setTargetAngle(Constants.ShoulderConstants.kReadyToDeployPosition[m_variables.getScoringLevel()] - Constants.ShoulderConstants.kBackOff);
          if(m_variables.getScoringLevel() > 6){
              m_elbow.setTargetAngle(-190);
              m_wrist.setPosition(Constants.WristConstants.kReadyToDeployPosition[m_variables.getScoringLevel()] + .1);
         }else {
              m_wrist.setPosition(Constants.WristConstants.kReadyToDeployPosition[m_variables.getScoringLevel()] + Constants.WristConstants.kBackOff);
              m_elbow.setTargetAngle(Constants.ElbowConstants.kReadyToDeployPosition[m_variables.getScoringLevel()] + Constants.ElbowConstants.kBackOff);
         }
     }

     @Override
     public boolean isFinished(){
          return Math.abs(m_shoulder.getAngle() - (Constants.ShoulderConstants
                  .kReadyToDeployPosition[m_variables.getScoringLevel()] - Constants.ShoulderConstants.kBackOff)) <= 10;
     }
}
