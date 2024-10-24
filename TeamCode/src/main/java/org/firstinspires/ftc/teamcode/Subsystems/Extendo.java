package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;

public class Extendo extends Subsystem {
    private HardwareMap hm=null
    private Telemetry tele=null;
    private final int LimitaSus = 2000, LimitaJos = 0;
    private float coeficient=4;
    private DcMotor me;
    private Gamepad gp;
    private int ExtendoVirtualPosition;
    @Override
    public void init(OpMode opmode){
        hm=opmode.hardwareMap;
        gp=opmode.gamepad2;
        me=initialized(hm.get(DcMotor.class, "MotorExtendo"), DcMotorSimple.Direction.FORWARD);
        me.setPower(0);
        ExtendoVirtualPosition =0;

    }
    @Override
    public void loop(Buttons buttons){
        float y = gp.right_stick_y;
        if (hm == null || tele == null) tele.addData("ERROR #003", this);
        if(!isInParameter(-y)[0]||!isInParameter(-y)[1]) tele.addData("ERROR #004 : OUT OF BOUNDS-EXTENDO", this);
        else {
            ExtendoVirtualPosition =Math.round(ExtendoVirtualPosition - y * coeficient );
            me.setTargetPosition(ExtendoVirtualPosition);
            me.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            me.setPower(1);
        }
    }
    public boolean[] isInParameter(float y) {
        float newPoz = ExtendoVirtualPosition - y * coeficient;
        return new boolean[]{!((newPoz > LimitaSus) && (y >= 0)), !((newPoz < LimitaJos) && (y <= 0))};
    }
    private DcMotor initialized(DcMotor motor, DcMotorSimple.Direction direction){
        motor.setDirection(direction);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        return motor;
    }
}
