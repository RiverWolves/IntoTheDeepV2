package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "tele")
public class Tele extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        double poz = 0;
        hardwareMap.servo.get("ServoExtendoDreapta").setPosition(0);
        waitForStart();
        if(isStopRequested())return;
        while(opModeIsActive()){
            if( gamepad2.dpad_up )
                poz += 0.01;
            if( gamepad2.dpad_down)
                poz -= 0.01;

            hardwareMap.servo.get("ServoExtendoDreapta").setPosition(poz);
            telemetry.addData("EXTENDO",poz);
            telemetry.update();
        }
    }
}
