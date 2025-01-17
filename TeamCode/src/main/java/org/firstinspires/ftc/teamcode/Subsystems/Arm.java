package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;

public class Arm extends Subsystem {
    Servo ServoBratStanga, ServoRotatieCleste, ServoBratDreapta;
    HardwareMap hm;
    Gamepad gp;
    Telemetry tel;
    float pozitieBrat;
    float clesteUnghiRotatie;
    public void init(OpMode opmode){
        hm=opmode.hardwareMap;
        gp=opmode.gamepad2;
        tel = opmode.telemetry;

        ServoBratStanga =hm.servo.get("ServoBratStanga");
        ServoRotatieCleste =hm.servo.get("ServoMiscareCleste");
        ServoBratDreapta=hm.servo.get("ServoBratDreapta");

        pozitieBrat=degreesToServo(120);//paralel cu solul
        clesteUnghiRotatie=servoToDegrees(pozitieBrat);//paralel cu solul

        updateBratPosition(pozitieBrat);
        updateClestePosition(degreesToServo(clesteUnghiRotatie));

        ServoBratDreapta.setDirection(Servo.Direction.REVERSE);
    }
    public void loop(Buttons buttons){
        float futurePoz =pozitieBrat+(gp.right_trigger-gp.left_trigger)*0.01f;
        if(futurePoz>=0&&futurePoz<degreesToServo(45)){
            pozitieBrat=futurePoz;
            clesteUnghiRotatie=90+servoToDegrees(pozitieBrat);
        }
        else if (futurePoz> degreesToServo(45)&&futurePoz<degreesToServo(90)) {
            pozitieBrat=futurePoz;
            clesteUnghiRotatie=180-(180-servoToDegrees(pozitieBrat));
        }
        else if(futurePoz> degreesToServo(90)&&futurePoz<degreesToServo(120))
        {
            pozitieBrat=futurePoz;
            clesteUnghiRotatie=servoToDegrees(pozitieBrat);
        }


        tel.addData("ARM: BRAT",servoToDegrees(pozitieBrat) );
        tel.addData("ARM: CLESTE",clesteUnghiRotatie );
        updateBratPosition(pozitieBrat);
        updateClestePosition(degreesToServo(clesteUnghiRotatie));

    }
    public float degreesToServo(float degree) {
        float in_min = 0;
        float in_max = 135;
        float out_min = 0;
        float out_max = 1;
        return (degree - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
    public float servoToDegrees(float degree) {
        float in_min = 0;
        float in_max = 1;
        float out_min = 0;
        float out_max = 135;
        return (degree - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
    public void updateBratPosition(double value){
        ServoBratDreapta.setPosition(value);
        ServoBratStanga.setPosition(value);
    }

    public void updateClestePosition(double value){
        ServoRotatieCleste.setPosition(value);
    }

    public void retract(){
        updateBratPosition(degreesToServo(180));
        updateClestePosition(degreesToServo(90));
    }

    public void extend(){
        ServoBratStanga.setPosition(degreesToServo(0));
        ServoRotatieCleste.setPosition(degreesToServo(90));
    }

}