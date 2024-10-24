package org.firstinspires.ftc.teamcode.OpModes.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.Lift;

@TeleOp(name="FullControll")
public class FullControllOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        final Buttons buttons = new Buttons();
        final Claw claw = new Claw();
        final Lift lift = new Lift();
        Subsystem[] subsystems = {buttons,lift,claw};
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
