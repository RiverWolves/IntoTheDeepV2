package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="SemiAuto")
public class
SemiAutoOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        if(isStopRequested())
            return;
        waitForStart();
        while(opModeInInit()){
        }
        while(opModeIsActive()){

        }
    }
}
