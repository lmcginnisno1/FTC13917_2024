package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Blank;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Elbow;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Shoulder;
import org.firstinspires.ftc.teamcode.subsystems.SUB_Wrist;

public class CMD_ArmPushIntoBackdrop extends CommandBase {
     SUB_Shoulder m_shoulder;
     SUB_Elbow m_elbow;
     GlobalVariables m_variables;
     public CMD_ArmPushIntoBackdrop(SUB_Shoulder p_shoulder, SUB_Elbow p_elbow, SUB_Blank p_blank,
                                    GlobalVariables p_variables){
          addRequirements(p_blank);
          m_shoulder = p_shoulder;
          m_elbow = p_elbow;
          m_variables = p_variables;
     }

     @Override
     public void initialize(){
          m_elbow.setTargetAngle(Constants.ElbowConstants.kReadyToDeployPosition[m_variables.getScoringLevel()] - Constants.ElbowConstants.kPushIntoBackdrop[m_variables.getScoringLevel()]);
          m_shoulder.setTargetAngle(Constants.ShoulderConstants.kReadyToDeployPosition[m_variables.getScoringLevel()] + Constants.ShoulderConstants.kPushIntoBackdrop[m_variables.getScoringLevel()]);
     }

     @Override
     public boolean isFinished(){
          return true;
//          return Math.abs(m_shoulder.getAngle() - (Constants.ShoulderConstants
//                  .kReadyToDeployPosition[m_variables.getScoringLevel()] + Constants.ShoulderConstants.kPushIntoBackdrop)) <= 5;
     }
}
