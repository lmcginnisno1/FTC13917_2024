package org.firstinspires.ftc.teamcode;

public class Constants {
     public static final class ArmConstants{
          enum ArmLevel {
             HomeLevel
             ,Level1
             ,Level2
             ,Level3
          }

          static ArmLevel m_armLevel = ArmLevel.HomeLevel;

          public static ArmLevel getArmLevel(){
               return m_armLevel;
          }

          public void setArmLevel(ArmLevel p_ArmLevel){
               m_armLevel = p_ArmLevel;
          }
     }

     public static final class WristConstants{
          public static final double m_openPosition = 0.6;
          public static final double m_closedPosition = 0.0;
     }
}

