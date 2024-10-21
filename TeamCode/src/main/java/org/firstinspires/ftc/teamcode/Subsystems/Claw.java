package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;

public  class Claw extends Subsystem {
    HardwareMap hm;
    Gamepad gp;
    Servo cwS,cwD;
    Float poz;
    @Override
    public void init(OpMode opmode) {
        hm = opmode.hardwareMap;
        gp = opmode.gamepad2;
        cwD = hm.servo.get("ServoClawDreapta");
        cwS = hm.servo.get("ServoClawStanga");
        poz = 0f;
    }

    @Override
    public void loop(Buttons buttons) {
        if(buttons.circle)
            poz = 0.5f;
        else
            poz = 0f;

        cwD.setPosition(poz);
        cwS.setPosition(poz);
    }
}
