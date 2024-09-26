package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.sun.tools.javac.util.Pair;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.lang.reflect.Array;

public class Lift {
    private HardwareMap hMap = null;
    private Telemetry tel = null;
    private DcMotor MotorLiftStanga = null, MotorLiftDreapta = null;
    private float coeff = 0.1f; //The coefficient of how much the input ifluences the movement of the lift
    private int topLimit = 2000, lowLimit = 0; //Limit the height of the lift

    public void init(OpMode opmode) {
        // Splitting the opmode into hardwaremap and telemetry
        //TODO: Test if this works on the longer run;
        hMap = opmode.hardwareMap;
        tel = opmode.telemetry;
        MotorLiftStanga = (DcMotor) hMap.get("MotorLiftStanga");
        MotorLiftDreapta = (DcMotor) hMap.get("MotorLiftDreapta");
    }

    public void updatePozition(float y) {
        if (hMap == null || tel == null) tel.addData("ERROR #001", this);
        if(!isInParameter(y)[0]||!isInParameter(y)[1]) tel.addData("ERROR #001 : OUT OF BOUNDS", this);
        else {
            MotorLiftStanga.setTargetPosition(Math.round(getPozition() + y * coeff));
            MotorLiftDreapta.setTargetPosition(Math.round(getPozition() + y * coeff));
        }
        tel.addData("Lift.pozition",getPozition());

    }

    public boolean[] isInParameter(float y) {
        float newPoz = getPozition() + y * coeff;
        return new boolean[]{!((newPoz >= topLimit) && (y >= 0)), !((newPoz <= lowLimit) && (y <= 0))};
    }

    //TODO: Test pozition getting
    public int getPozition() {
        return (MotorLiftDreapta.getCurrentPosition() + MotorLiftStanga.getCurrentPosition()) / 2;
    }


}
