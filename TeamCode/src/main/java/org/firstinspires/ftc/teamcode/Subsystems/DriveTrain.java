package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;
import org.firstinspires.ftc.teamcode.Roadrunner.MecanumDrive;

public class DriveTrain extends Subsystem {
    HardwareMap hm;
    Gamepad gp;
    MecanumDrive drive;

    @Override
    public void init(OpMode opmode) {
        hm = opmode.hardwareMap;
        gp = opmode.gamepad1;
        drive = new MecanumDrive(hm, new Pose2d(0, 0, 0));
    }

    @Override
    public void loop(Buttons buttons) {
        float y = -gp.left_stick_y, x = -gp.left_stick_x, rx = -gp.right_stick_x;
        if(!gp.dpad_right) {

            drive.setDrivePowers(new PoseVelocity2d(new Vector2d(y, x), rx));
        }
        else {
            drive.setDrivePowers(new PoseVelocity2d(new Vector2d(y*0.5, x*0.5), rx*0.5));
        }

    }
}
