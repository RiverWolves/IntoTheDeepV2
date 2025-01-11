package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;

public class Intake extends Subsystem {
    HardwareMap hm;
    Gamepad gp;
    CRServo rotireS, rotireD;
    Servo rabatare1,rabatare2;
    public double Putere;

    @Override
    public void init(OpMode opmode) {
        hm = opmode.hardwareMap;
        gp = opmode.gamepad2;
        rotireS = hm.crservo.get("ServoIntakeRotireStanga");
        rotireD = hm.crservo.get("ServoIntakeRotireDreapta");
        rabatare1 = hm.servo.get("ServoIntakeRabatareDreapta");
        rabatare2 = hm.servo.get("ServoIntakeRabatareStanga");


        rabatare1.setDirection(Servo.Direction.REVERSE);
        rotireD.setDirection(DcMotorSimple.Direction.REVERSE);

retract();
    }

    @Override
    public void loop(Buttons buttons) {
        Putere = gp.right_trigger - gp.left_trigger;
        updatePower(Putere);

        if (!buttons.cross)
            retract();
        else
            extend();
    }
    public void retract(){
        rabatare1.setPosition(0.94);
        rabatare2.setPosition(0.94);
    }
    public void extend(){
        rabatare1.setPosition(0);
        rabatare2.setPosition(0);
    }
    public void updatePower(double value){
        rotireS.setPower(value);
        rotireD.setPower(value);
    }
}