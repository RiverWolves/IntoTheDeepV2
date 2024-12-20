package org.firstinspires.ftc.teamcode.OpModes.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Basket;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Extendo;
import org.firstinspires.ftc.teamcode.Subsystems.OldExtendo;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Lift;

@TeleOp(name="FullControll")
public class FullControllOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        final Buttons buttons = new Buttons();
        final Lift lift = new Lift();
        final Intake intake= new Intake();
        final DriveTrain driveTrain = new DriveTrain();
        final Extendo extendo = new Extendo() ;
        final Basket basket = new Basket();
        final Claw claw = new Claw();
        Subsystem[] subsystems = {buttons,extendo,driveTrain,intake,lift,claw,basket};
        for (Subsystem sub:subsystems) {
            sub.init(this);
        }
 //        while(opModeInInit()){ }
        if(isStopRequested()) return;
        waitForStart();
        while(opModeIsActive()){

            for (Subsystem sub:subsystems) {
                sub.loop(buttons);
            }

            telemetry.update();
        }
    }


}
