package org.firstinspires.ftc.teamcode.Subsystems;

import android.icu.lang.UProperty;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;

public  class Claw extends Subsystem {
    HardwareMap hm;
    Gamepad gp;
    Servo  cw;
     Telemetry telem;
    Float poz;
    @Override
    public void init(OpMode opmode) {
        hm = opmode.hardwareMap;
        gp = opmode.gamepad2;
        telem=opmode.telemetry;
        cw = hm.servo.get("ServoClaw");
       updateState(true);
    }

    @Override
    public void loop(Buttons buttons) {
        updateState(!buttons.dpadRight);

        telem.addData("Claw: pozitie ", poz);
        telem.addData("Claw: buton ", buttons.dpadRight);
    }

    public void updateState(boolean isClosed)
    {
        if(!isClosed)
            poz = 1f;
        else
            poz = 0.3f;



        cw.setPosition(poz);
    }
}
