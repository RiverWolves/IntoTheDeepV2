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
    Servo rabatare1;
    Servo rabatare2;
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
    }

    @Override
    public void loop(Buttons buttons) {
        Putere = gp.right_trigger - gp.left_trigger;
        rotireS.setPower(Putere);
        rotireD.setPower(Putere);


        if (buttons.cross)
        {
            rabatare1.setPosition(0.5);
            rabatare2.setPosition(0.5);
        }
        else
        {
            rabatare1.setPosition(0);
            rabatare2.setPosition(0);
        }
    }

    public double getPower() {
        return Putere;
    }
}
//servo de la 0-0.5 si se rabateaza + buton va fi switch + functie de getposition