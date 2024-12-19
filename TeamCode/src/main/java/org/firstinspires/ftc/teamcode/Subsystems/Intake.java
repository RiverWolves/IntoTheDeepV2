package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;

public class Intake extends Subsystem {
    HardwareMap hm;
    Gamepad gp;
    Telemetry tel;
//    Servo servoCleste, servoRotireIntake;
//    double pasi, position;
//    @Override
//    public void init(OpMode opmode){
//        hm=opmode.hardwareMap;
//        gp=opmode.gamepad2;
//        servoCleste=hm.servo.get("ServoIntakeCleste");
//        servoRotireIntake=hm.servo.get("ServoIntakeRotire");
//        pasi =0f;
//        position=0.5f;
//        servoCleste.setPosition(0);
//        servoRotireIntake.setPosition(position);
//    }
//
//    @Override
//    public void loop(Buttons buttons) {
//        pasi = (gp.right_trigger- gp.left_trigger) *0.1;
//        position += pasi;
//        servoRotireIntake.setPosition(position);
//
//        if (buttons.square)
//            servoCleste.setPosition(0.5f);
//        else
//            servoCleste.setPosition(0);
//    }
    CRServo rotireS, rotireD;
    Servo rabatare1;
    Servo rabatare2;
    public double Putere;
    final float pozIntake = 0.25f, pozTrans = 0.95f;

    @Override
    public void init(OpMode opmode) {
        hm = opmode.hardwareMap;
        gp = opmode.gamepad2;
        rotireS = hm.crservo.get("ServoIntakeRotireStanga");
        rotireD = hm.crservo.get("ServoIntakeRotireDreapta");
        rabatare1 = hm.servo.get("ServoIntakeRabatareDreapta");
        rabatare2 = hm.servo.get("ServoIntakeRabatareStanga");
        tel = opmode.telemetry;

        rabatare2.setDirection(Servo.Direction.REVERSE);
        rotireD.setDirection(DcMotorSimple.Direction.REVERSE);


        rabatare1.setPosition(pozIntake);
        rabatare2.setPosition(pozIntake);


    }

    @Override
    public void loop(Buttons buttons) {
        float poz;

        Putere = gp.right_trigger - gp.left_trigger;
        rotireS.setPower(Putere);
        rotireD.setPower(Putere);


        if (buttons.cross)
        { poz = pozTrans;

        }
        else
        {
            poz = pozIntake;
        }
        tel.addData("poz", poz);
        rabatare1.setPosition(poz);
        rabatare2.setPosition(poz);

    }

    public double getPower() {
        return Putere;
    }
}
//servo de la 0-0.5 si se rabateaza + buton va fi switch + functie de getposition