package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;

public class Intake extends Subsystem {
    HardwareMap hm;
    Gamepad gp;
    Servo servoCleste, servoRotireIntake;
    double pasi, position;
    @Override
    public void init(OpMode opmode){
        hm=opmode.hardwareMap;
        gp=opmode.gamepad2;
        servoCleste=hm.servo.get("ServoIntakeCleste");
        servoRotireIntake=hm.servo.get("ServoIntakeRotire");
        pasi =0f;
        position=0.5f;
        servoCleste.setPosition(0);
        servoRotireIntake.setPosition(position);
    }

    @Override
    public void loop(Buttons buttons) {
        pasi = (gp.right_trigger- gp.left_trigger) *0.1;
        position += pasi;
        servoRotireIntake.setPosition(position);

        if (buttons.square)
            servoCleste.setPosition(0.5f);
        else
            servoCleste.setPosition(0);
    }
//    CRServo rotireS, rotireD;
//    Servo rabatare1;
//    Servo rabatare2;
//    public double Putere;
//
//    @Override
//    public void init(OpMode opmode) {
//        hm = opmode.hardwareMap;
//        gp = opmode.gamepad2;
//        rotireS = hm.crservo.get("ServoIntakeRotireStanga");
//        rotireD = hm.crservo.get("ServoIntakeRotireDreapta");
//        rabatare1 = hm.servo.get("ServoIntakeRabatareDreapta");
//        rabatare2 = hm.servo.get("ServoIntakeRabatareStanga");
//
//
//        rabatare1.setDirection(Servo.Direction.REVERSE);
//        rotireD.setDirection(DcMotorSimple.Direction.REVERSE);
//    }
//
//    @Override
//    public void loop(Buttons buttons) {
//        Putere = gp.right_trigger - gp.left_trigger;
//        rotireS.setPower(Putere);
//        rotireD.setPower(Putere);
//
//
//        if (buttons.cross)
//        {
//            rabatare1.setPosition(0.5);
//            rabatare2.setPosition(0.5);
//        }
//        else
//        {
//            rabatare1.setPosition(0);
//            rabatare2.setPosition(0);
//        }
//    }
//
//    public double getPower() {
//        return Putere;
//    }
}
//servo de la 0-0.5 si se rabateaza + buton va fi switch + functie de getposition