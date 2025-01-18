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
        ServoBratDreapta.setDirection(Servo.Direction.REVERSE);

        pozitieBrat=1f;//paralel cu solul
        clesteUnghiRotatie=0f;//paralel cu solul

        updateBratPosition(pozitieBrat);
        updateClestePosition(degreesToServo(clesteUnghiRotatie));


    }
    public void loop(Buttons buttons){
        float futurePoz =pozitieBrat+(gp.right_trigger-gp.left_trigger)*0.01f;

        if(futurePoz>=0.25&&futurePoz<0.6){
            pozitieBrat=futurePoz;
            clesteUnghiRotatie=0f;
        }
        else if (futurePoz> 0.6&&futurePoz<1) {
            pozitieBrat=futurePoz;
            clesteUnghiRotatie=0f;
        }



        tel.addData("ARM: BRAT",pozitieBrat);
        tel.addData("ARM: CLESTE",clesteUnghiRotatie );
        updateBratPosition(pozitieBrat);
        updateClestePosition(clesteUnghiRotatie);

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