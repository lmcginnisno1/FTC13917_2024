package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.GlobalVariables;
import org.firstinspires.ftc.teamcode.ftclib.command.CommandBase;

public class CMD_SetRobotState extends CommandBase{
     GlobalVariables m_variables;
     GlobalVariables.RobotState m_robotState;

     public CMD_SetRobotState(GlobalVariables p_variables, GlobalVariables.RobotState p_robotState){
          m_variables = p_variables;
          m_robotState = p_robotState;
     }

     @Override
     public void initialize(){
          m_variables.setRobotState(m_robotState);
     }

     @Override
     public boolean isFinished(){
          return true;
     }
}
