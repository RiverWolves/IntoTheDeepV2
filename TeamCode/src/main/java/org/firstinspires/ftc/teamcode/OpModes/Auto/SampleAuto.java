package org.firstinspires.ftc.teamcode.OpModes.Auto;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Resurse.Subsystem;
import org.firstinspires.ftc.teamcode.Roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.Extendo;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Lift;

@Autonomous(name = "Specimen")
public class SampleAuto extends LinearOpMode {
    Pose2d beginPose = new Pose2d(8, -63, Math.toRadians(270));
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

        final Lift lift = new Lift();
        final Intake intake= new Intake();
        final Extendo extendo = new Extendo() ;
        final Arm arm = new Arm();
        final Claw claw = new Claw();
        Subsystem[] subsystems = {extendo,intake,lift,claw,arm};
        for (Subsystem sub:subsystems) {
            sub.init(this);
        }
          lift.updatePozition(0);
          claw.updateState(true);
          extendo.updatePoz(0.3);
          arm.updateBratPosition(arm.degreesToServo(110));

        waitForStart();

        Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .afterTime(0, new LiftAction (lift, 1400))
                            .lineToY(-34)
                              .afterTime(0.3, new ArmAction1(arm, 110))
                            .afterTime(0.3, new ArmAction2(arm, 90))
                            .afterTime(0.3, new ClawAction(claw, false))

                            //.lineToY(-45)
//                            .splineToConstantHeading(new Vector2d(37, -14), new Rotation2d(0, 2))
//                            .strafeTo(new Vector2d(48, -14))
//                            .stopAndAdd(new ExtendoAction(extendo, 700))
//                            .stopAndAdd(new ExtendoAction(extendo, 0))
//                            .strafeTo(new Vector2d(58, -14))
//                            .stopAndAdd(new ExtendoAction(extendo, 700))
//                            .stopAndAdd(new ExtendoAction(extendo, 0))
//                            .strafeTo(new Vector2d(61, -14))
//                            .strafeTo(new Vector2d(61, -50))
//                            .strafeTo(new Vector2d(61, -43))
//
//                            //.afterTime(0, new ArmAction(arm, pozitia initiala pe spate paralel cu pamantul))
//                            .strafeToLinearHeading(new Vector2d(47, -60), Math.toRadians(90))
//                            .stopAndAdd(new ClawAction(claw, true))
//                            .afterTime(0, new LiftAction(lift, 700))
//                            //.afterTime(0, new ArmAction(arm, muta bratul pe fata paralel cu pamantul))
//                            .strafeTo(new Vector2d(8, -34))
//                            //.stopAndAdd(new ArmAction(arm, muta bratul mai pe fata))
//                            .afterTime(0, new ClawAction(claw, false))
//                            .afterTime(0, new LiftAction(lift, 0))
//                            //.afterTime(0.3, new ArmAction(arm, bratul pe spate paralel cu pamantul))
//
//                            .strafeTo(new Vector2d(47, -60))
//                            .stopAndAdd(new ClawAction(claw, true))
//                            .afterTime(0, new LiftAction(lift, 700))
//                            //.afterTime(0, new ArmAction(arm, muta bratul pe fata paralel cu pamantul))
//                            .strafeTo(new Vector2d(8, -34))
//                            //.stopAndAdd(new ArmAction(arm, muta bratul mai pe fata))
//                            .afterTime(0, new ClawAction(claw, false))
//                            .afterTime(0, new LiftAction(lift, 0))
//                            //.afterTime(0.3, new ArmAction(arm, bratul pe spate paralel cu pamantul))
//
//
//                            .strafeTo(new Vector2d(47, -60))
//                            .stopAndAdd(new ClawAction(claw, true))
//                            .afterTime(0, new LiftAction(lift, 700))
//                            //.afterTime(0, new ArmAction(arm, muta bratul pe fata paralel cu pamantul))
//                            .strafeTo(new Vector2d(8, -34))
//                            //.stopAndAdd(new ArmAction(arm, muta bratul mai pe fata))
//                            .afterTime(0, new ClawAction(claw, false))
//                            .afterTime(0, new LiftAction(lift, 0))
//                            //.afterTime(0.3, new ArmAction(arm, bratul pe spate paralel cu pamantul))
//
//                            .afterTime(0, new LiftAction(lift, 0))
//                            .strafeTo(new Vector2d(56, -60))
//
                            .build());
        

    }
    public class LiftAction implements Action{
        Lift lift;
        int position;

        public LiftAction(Lift liftp, int p){
            this.lift = liftp;
            this.position = p;
        }


        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            lift.updatePozition(position);
            return false;
        }
    }

    public class ExtendoAction implements Action{
        Extendo extendo;
        double position;

        public ExtendoAction(Extendo extendop, int p) {
            this.extendo = extendop;
            this.position = p;
        }


        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            extendo.updatePoz(position);
            return false;
        }
    }
     public class ClawAction implements Action{
            Claw claw;
            boolean position;

            public ClawAction(Claw clawp, boolean p){
                this.claw =clawp;
                this.position = p;

            }

         @Override
         public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                claw.updateState(position);
             return false;
         }
     }

     public class ArmAction1 implements Action{
            Arm arm;
            float position;

            public ArmAction1(Arm armp, float p){
                this.arm=armp;
                this.position = p;
            }
         @Override
         public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                arm.updateBratPosition(arm.degreesToServo(position));
             return false;
         }
     }

    public class ArmAction2 implements Action{
        Arm arm;
        float position;

        public ArmAction2(Arm armp, float p){
            this.arm=armp;
            this.position = p;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            arm.updateClestePosition(arm.degreesToServo(position));

            return false;
        }
    }
}
