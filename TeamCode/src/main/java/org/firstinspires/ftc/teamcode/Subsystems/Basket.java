package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;

public class Basket extends Subsystem {

    Gamepad gp;
    HardwareMap hm;
    Servo basketS,basketD;
    Telemetry telem;

    @Override
    public void init(OpMode opmode) {
        hm = opmode.hardwareMap;
        gp = opmode.gamepad2;
        telem=opmode.telemetry;

        basketD = hm.servo.get("ServoBasketDreapta");
        basketS = hm.servo.get("ServoBasketStanga");
        basketS.setDirection(Servo.Direction.REVERSE);

        intake();
    }

    @Override
    public void loop(Buttons buttons) {
        if (buttons.triangle)
            outtake();
        else
            intake();

        telem.addData("Basket: Input ", buttons.triangle);

    }

    public void updatePozition(double value){
        basketS.setPosition(value);
        basketD.setPosition(value);
    }
    public void outtake()
    {
        updatePozition(1);
    }
    public void intake(){
        updatePozition(0);
    }
}
