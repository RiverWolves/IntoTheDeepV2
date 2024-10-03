package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.sun.tools.javac.util.Pair;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;

import java.lang.reflect.Array;

public class Lift extends Subsystem {
    private HardwareMap hMap = null;
    private Telemetry tel = null;
    private DcMotor MotorLiftStanga = null, MotorLiftDreapta = null;
    private float coeff = 4; //The coefficient of how much the input ifluences the movement of the lift
    private int topLimit = 2000, lowLimit = 0; //Limit the height of the lift
    public int liftVirtualPozition = 0;
    @Override
    public void init(OpMode opmode) {
        // Splitting the opmode into hardwaremap and telemetry
        hMap = opmode.hardwareMap;
        tel = opmode.telemetry;
        MotorLiftStanga = initialized(hMap.get(DcMotor.class,"MotorLiftStanga"), DcMotorSimple.Direction.FORWARD);
        MotorLiftDreapta = initialized(hMap.get(DcMotor.class,"MotorLiftDreapta"), DcMotorSimple.Direction.REVERSE);
        liftVirtualPozition = 0;
    }

    public void updatePozition(float y) {
        if (hMap == null || tel == null) tel.addData("ERROR #001", this);
        if(!isInParameter(-y)[0]||!isInParameter(-y)[1]) tel.addData("ERROR #001 : OUT OF BOUNDS", this);
        else {
            liftVirtualPozition =Math.round(liftVirtualPozition - y * coeff );

            MotorLiftStanga.setTargetPosition(liftVirtualPozition);
            MotorLiftDreapta.setTargetPosition(liftVirtualPozition);

            MotorLiftDreapta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            MotorLiftStanga.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            MotorLiftStanga.setPower(1);
            MotorLiftDreapta.setPower(1);

        }
//        tel.addData("Lift.pozition",liftVirtualPozition);

    }

    public boolean[] isInParameter(float y) {
        float newPoz = liftVirtualPozition - y * coeff;
        return new boolean[]{!((newPoz > topLimit) && (y >= 0)), !((newPoz < lowLimit) && (y <= 0))};
    }
    private DcMotor initialized(DcMotor motor, DcMotorSimple.Direction direction){
        motor.setDirection(direction);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        return motor;
    }

}