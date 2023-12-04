package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Teleop Blue", group = "teleop blue")
public class Teleop_Robot_Centric_Blue extends Teleop_Robot_Centric_Red {
     @Override
     public void setSide(){
          m_robot.m_red = false;
     }
}
