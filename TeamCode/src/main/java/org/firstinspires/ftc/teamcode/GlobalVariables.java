package org.firstinspires.ftc.teamcode;

public class GlobalVariables{
     public enum RobotState{
          Home,
          ReadyToIntake,
          Intake,
          Stow,
          Score
     }

     RobotState m_robotState = RobotState.Home;

     public void setRobotState(RobotState p_robotState){
          m_robotState = p_robotState;
     }

     public RobotState getRobotState(){
          return m_robotState;
     }
}
