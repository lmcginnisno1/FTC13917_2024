package org.firstinspires.ftc.teamcode;

public class GlobalVariables{
     public enum RobotState{
          Home,
          ReadyToIntake,
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

     public enum IntakeLevel{
          Two
          ,Three
          ,Four
          ,Five
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

     IntakeLevel m_intakeLevel = IntakeLevel.Two;

     public void setIntakeLevel(IntakeLevel p_intakeLevel){
          m_intakeLevel = p_intakeLevel;
     }

     public IntakeLevel getIntakeLevel(){
          return m_intakeLevel;
     }

     public boolean isIntakeLevel(IntakeLevel p_intakeLevel){
          return m_intakeLevel == p_intakeLevel;
     }
}
