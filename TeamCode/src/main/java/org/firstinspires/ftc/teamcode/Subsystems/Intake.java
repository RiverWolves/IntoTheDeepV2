package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Resurse.Subsystem;

public class Intake extends Subsystem {
   HardwareMap hm;
    Gamepad gp;
    CRServo rabatare1, rabatare2;
    public double Putere;
    @Override
    public void init(OpMode opmode) {
        hm = opmode.hardwareMap;
         gp = opmode.gamepad2;
         rabatare1 = hm.crservo.get("rabatare1");
        rabatare2 = hm.crservo.get("rabatare2");

        rabatare2.setDirection(DcMotorSimple.Direction.REVERSE);


    }

    @Override
    public void loop() {
        Putere = gp.right_trigger-gp.left_trigger;

        rabatare1.setPower(Putere);
        rabatare2.setPower(Putere);


    }

    public double getPower(){
        return Putere;
    }
}
//servo de la 0-0.5 si se rabateaza + buton va fi switch + functie de getposition