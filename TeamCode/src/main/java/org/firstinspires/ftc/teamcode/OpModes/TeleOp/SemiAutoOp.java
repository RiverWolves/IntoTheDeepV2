package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Arm;
import org.firstinspires.ftc.teamcode.Subsystems.Claw;
import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.Extendo;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Lift;

@TeleOp(name="SemiAuto")
public class
SemiAutoOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        final Buttons buttons = new Buttons();
        final Lift lift = new Lift();
        final Intake intake = new Intake();
        final DriveTrain driveTrain = new DriveTrain();
        final Extendo extendo = new Extendo();
        final Arm arm = new Arm();
        final Claw claw = new Claw();
        Subsystem[] subsystems = {buttons, extendo, driveTrain, intake, lift, claw, arm};

        for (Subsystem sub : subsystems) {
            sub.init(this);
        }
        if (isStopRequested())
            return;
        waitForStart();

        while (opModeIsActive()) {
            claw.updateState(buttons.rightBumper);
            if (buttons.square) {
                intake.extend();
                extendo.updatePoz(0);
            } else {
                intake.retract();
                extendo.updatePoz(0.3);
            }
        }
    }
}
