package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;

public class Extendo extends Subsystem {
    Servo servoS, servoD;
    HardwareMap hm;
    Gamepad gp;
    float poz;
    final float coef = 0.3f;
    @Override
    public void init(OpMode opmode) {
        servoD = hm.servo.get("ServoExtendoDreapta");
        servoS = hm.servo.get("ServoExtendoStanga");
        servoS.setDirection(Servo.Direction.REVERSE);


    }

    @Override
    public void loop(Buttons buttons) {
        poz += gp.right_stick_y * coef;
        servoD.setPosition(poz);
        servoS.setPosition(poz);
    }
}
