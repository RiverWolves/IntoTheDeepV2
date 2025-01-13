package org.firstinspires.ftc.teamcode.OpModes.Auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;
import org.firstinspires.ftc.teamcode.Roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Extendo;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Lift;

public class SpecimenAuto extends LinearOpMode {
    Pose2d beginPose = new Pose2d(-62.5, 0, 0);
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
        final Buttons buttons = new Buttons();
        final Lift lift = new Lift();
        final Intake intake= new Intake();
        final DriveTrain driveTrain = new DriveTrain();
        final Extendo extendo = new Extendo() ;
        final Arm arm = new Arm();
        final Claw claw = new Claw();
        Subsystem[] subsystems = {buttons,extendo,driveTrain,intake,lift,claw, arm};
        for (Subsystem sub:subsystems) {
            sub.init(this);
        }
        waitForStart();

        Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .splineTo(new Vector2d(30, 30), Math.PI / 2)
                            .splineTo(new Vector2d(0, 60), Math.PI)
                            .build());

    }
}
