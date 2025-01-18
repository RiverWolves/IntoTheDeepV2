package org.firstinspires.ftc.teamcode.OpModes.Auto;

import android.telecom.StatusHints;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Roadrunner.ThreeDeadWheelLocalizer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Resurse.Subsystem;
import org.firstinspires.ftc.teamcode.Roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Extendo;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Lift;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@Autonomous(name = "SpecimenAutoHoR")
public class SpecimenAutoHoR extends LinearOpMode {
    public static double power = -0.3;
    public static double bratPosition = 0.5;
    public static double wristPosition = 0.5;
    public static double clawPosition = 0.5;
    public static int slidersPositionHigh = 1450;
    public static long  HowLongToSleep = 2550;
    boolean doneAuto = false;

    DcMotorEx leftFront, leftBack, rightBack, rightFront, SlidersMotor1, SlidersMotor2;
    Servo bratStanga, bratDreapta, wrist, claww;


    @Override
    public void runOpMode() throws InterruptedException {



        leftFront = hardwareMap.get(DcMotorEx.class, "SF");
        leftBack = hardwareMap.get(DcMotorEx.class, "SS");
        rightBack = hardwareMap.get(DcMotorEx.class, "DS");
        rightFront = hardwareMap.get(DcMotorEx.class, "DF");
        SlidersMotor1 = hardwareMap.get(DcMotorEx.class, "MotorLiftStanga");
        SlidersMotor2 = hardwareMap.get(DcMotorEx.class, "MotorLiftDreapta");

        claww = hardwareMap.get(Servo.class, "ServoClaw");
        wrist = hardwareMap.get(Servo.class, "ServoMiscareCleste");
        bratDreapta = hardwareMap.get(Servo.class, "ServoBratDreapta");
        bratStanga = hardwareMap.get(Servo.class, "ServoBratStanga");

        SlidersMotor1.setDirection(DcMotorSimple.Direction.FORWARD);
        SlidersMotor2.setDirection(DcMotorSimple.Direction.REVERSE);

        SlidersMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        SlidersMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        SlidersMotor1.setTargetPosition(slidersPositionHigh);
        SlidersMotor2.setTargetPosition(slidersPositionHigh);

        SlidersMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        SlidersMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);



        waitForStart();

        while (opModeIsActive()) {
            if(!doneAuto) {

                leftFront.setPower(power);
                leftBack.setPower(power);
                rightBack.setPower(power);
                rightFront.setPower(power);
                SlidersMotor1.setPower(1);
                SlidersMotor2.setPower(1);
                bratStanga.setPosition(bratPosition);
                bratDreapta.setPosition(bratPosition);
                claww.setPosition(clawPosition);
                wrist.setPosition(wristPosition);

                sleep(HowLongToSleep);
                leftFront.setPower(0);
                leftBack.setPower(0);
                rightBack.setPower(0);
                rightFront.setPower(0);
                SlidersMotor1.setTargetPosition(0);
                SlidersMotor2.setTargetPosition(0);
                bratDreapta.setPosition(0.6);
                bratStanga.setPosition(0.6);
                sleep(750);
                bratStanga.setPosition(0.9);
                bratDreapta.setPosition(0.9);
                claww.setPosition(0.8);
                leftFront.setPower(-power);
                leftBack.setPower(-power);
                rightBack.setPower(-power);
                rightFront.setPower(-power);
                sleep(400);
                leftFront.setPower(power);
                leftBack.setPower(-power);
                rightBack.setPower(-power);
                rightFront.setPower(power);
                sleep(3500);
                leftFront.setPower(power);
                leftBack.setPower(-power);
                rightBack.setPower(-power);
                rightFront.setPower(power);
                sleep(4000);
                leftFront.setPower(0);
                leftBack.setPower(0);
                rightBack.setPower(0);
                rightFront.setPower(0);

                doneAuto = true;
            }
        }




                telemetry.update();

        }
    }

