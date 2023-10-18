package org.firstinspires.ftc.teamcode;

public class GlobalVariables{
     public enum RobotState{
          Home,
          ReadyToIntake,
          Intake,
          Stow,
          Score,
          Climb,
          ReadyToLaunch
     }

     public enum ScoringLevel{
          One,
          Two,
          Three,
     }

     RobotState m_robotState = RobotState.Home;

     public void setRobotState(RobotState p_robotState){
          m_robotState = p_robotState;
     }

     public RobotState getRobotState(){
          return m_robotState;
     }

     public boolean isRobotState(RobotState p_robotState){
          return p_robotState == m_robotState;
     }

     ScoringLevel m_currentScoringLevel = ScoringLevel.One;

     public void setScoringLevel(ScoringLevel p_scoringLevel){
          m_currentScoringLevel = p_scoringLevel;
     }

     public ScoringLevel getScoringLevel(){
          return m_currentScoringLevel;
     }

     public boolean isScoringLevel(ScoringLevel p_scoringLevel){
          return m_currentScoringLevel == p_scoringLevel;
     }
}
