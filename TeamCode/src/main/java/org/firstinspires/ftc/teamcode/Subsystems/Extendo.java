package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;

public class Extendo extends Subsystem {
    Servo servoS, servoD, ServoRotire;
    HardwareMap hm;
    Telemetry tel;
    Gamepad gp;
    float poz;
    final float speed = 0.004f;
    @Override
    public void init(OpMode opmode) {
        hm = opmode.hardwareMap;
        gp = opmode.gamepad2;
        tel = opmode.telemetry;
        servoD = hm.servo.get("ServoExtendoDreapta");
        servoS = hm.servo.get("ServoExtendoStanga");
        ServoRotire=hm.servo.get("ServoRomaneasca");
        servoS.setDirection(Servo.Direction.REVERSE);
        poz = 0.32f;
        ServoRotire.setPosition(0);
        updatePoz(poz);
    }

    @Override
    public void loop(Buttons buttons) {
//        if( poz + speed < 0.35 && gp.dpad_up )
//            poz += speed;
//        if(poz -speed > 0 && gp.dpad_down)
//            poz -= speed;

        if (poz+(-gp.left_stick_y*speed)<0.35&&poz+(-gp.left_stick_y*speed)>0){
            poz -= gp.left_stick_y*speed;
            updatePoz(poz);
        }
        if(gp.square){
            ServoRotire.setPosition(0.3);
        }
        else ServoRotire.setPosition(0);
        tel.addData("EXTENDO",poz);

    }
    public void updatePoz(double value){

        servoD.setPosition(value);
        servoS.setPosition(value);
    }
}
