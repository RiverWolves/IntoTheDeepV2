package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;

public class Basket extends Subsystem {

    Gamepad gp;
    HardwareMap hm;
    Servo basket;

    @Override
    public void init(OpMode opmode) {
        hm = opmode.hardwareMap;
        gp = opmode.gamepad2;
        basket = hm.servo.get("ServoBasket");

    }

    @Override
    public void loop(Buttons buttons) {
        if (buttons.triangle)
            basket.setPosition(0.5);
        else
            basket.setPosition(0);
    }
}
