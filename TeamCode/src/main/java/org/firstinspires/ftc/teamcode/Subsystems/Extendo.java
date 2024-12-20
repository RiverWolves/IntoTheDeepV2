package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;

public class Extendo extends Subsystem {
    Servo servoS, servoD;
    HardwareMap hm;
    Telemetry tel;
    Gamepad gp;
    float poz;
    final float coef = 0.005f;
    @Override
    public void init(OpMode opmode) {
        hm = opmode.hardwareMap;
        gp = opmode.gamepad2;
        tel = opmode.telemetry;
        servoD = hm.servo.get("ServoExtendoDreapta");
        servoS = hm.servo.get("ServoExtendoStanga");
        servoS.setDirection(Servo.Direction.REVERSE);
        poz = 0;
        servoD.setPosition(poz);
        servoS.setPosition(poz);
    }

    @Override
    public void loop(Buttons buttons) {
        tel.addData("poz",poz);
        if(0.3> poz - gp.right_stick_y * coef && poz - gp.right_stick_y * coef > 0 )
            poz -= gp.right_stick_y * coef;

        servoD.setPosition(poz);
        servoS.setPosition(poz);
    }
}
