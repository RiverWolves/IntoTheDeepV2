package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

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
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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
@TeleOp(name = "TestingServos")
public class TestingServos extends LinearOpMode {
    public static double power = -0.3;
    public static double bratPosition;
    public static double wristPosition;
    public static double clawPosition;
    public static int slidersPositionHigh = 1000;
    public static long  HowLongToSleep = 2350;

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

        bratDreapta.setDirection(Servo.Direction.REVERSE);

        SlidersMotor1.setDirection(DcMotorSimple.Direction.FORWARD);
        SlidersMotor2.setDirection(DcMotorSimple.Direction.REVERSE);

        SlidersMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        SlidersMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        SlidersMotor1.setTargetPosition(slidersPositionHigh);
        SlidersMotor2.setTargetPosition(slidersPositionHigh);

        SlidersMotor1.setPower(1);
        SlidersMotor2.setPower(1);

        SlidersMotor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        SlidersMotor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);


        if(isStopRequested()) return;
        waitForStart();


        while (opModeIsActive()) {
            bratStanga.setPosition(bratPosition);
            bratDreapta.setPosition(bratPosition);
            claww.setPosition(clawPosition);
            wrist.setPosition(wristPosition);
            SlidersMotor1.setTargetPosition(slidersPositionHigh);
            SlidersMotor2.setTargetPosition(slidersPositionHigh);

        }







    }
}

