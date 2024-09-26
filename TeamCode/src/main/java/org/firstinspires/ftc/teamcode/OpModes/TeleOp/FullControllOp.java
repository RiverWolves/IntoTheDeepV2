package org.firstinspires.ftc.teamcode.OpModes.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Lift;

@TeleOp(name="FullControll")
public class FullControllOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Lift lift = new Lift();
        lift.init(this);
//        while(opModeInInit()){ }
        if(isStopRequested()) return;
        waitForStart();
        while(opModeIsActive()){
            lift.updatePozition(gamepad1.left_stick_y);
            telemetry.update();
        }
    }


}
