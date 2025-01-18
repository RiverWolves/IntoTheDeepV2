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
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Resurse.Subsystem;
import org.firstinspires.ftc.teamcode.Roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.Extendo;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Lift;
@Autonomous(name = "Sample Auto Bun")
public class SpecimenAuto extends LinearOpMode {
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
          arm.updateBratPosition(0);


        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(-38, -65, Math.toRadians(90)))
                        .afterTime(0, new LiftAction(lift, 3200))
                        .afterTime(1, new ArmAction1(arm, 110))
                        .afterTime(1.5, new ClawAction(claw, false))
                        .strafeToLinearHeading(new Vector2d(-70, -54), Math.toRadians(45))
                        .afterTime(1, new ArmAction1(arm, 0))
                        .afterTime(1, new LiftAction(lift, 0))
                        .waitSeconds(3)
                        //.strafeToLinearHeading(new Vector2d(-25, 0), Math.toRadians(0))

    /*            .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-48, -39), Math.toRadians(90))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-54, -54), Math.toRadians(45))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-58, -39), Math.toRadians(90))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-54, -54), Math.toRadians(45))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-58, -33), Math.toRadians(143))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-54, -54), Math.toRadians(45))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-25, 0), Math.toRadians(0))*/


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
