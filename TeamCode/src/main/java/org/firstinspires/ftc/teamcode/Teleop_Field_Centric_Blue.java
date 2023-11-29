package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Teleop Field Centric Blue", group = "teleop blue")
public class Teleop_Field_Centric_Blue extends Teleop_Field_Centric_Red {
     @Override
     public void setSide(){
          m_robot.m_red = false;
     }
}
