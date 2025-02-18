package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Resurse.Buttons;
import org.firstinspires.ftc.teamcode.Resurse.Subsystem;

public class Lift extends Subsystem {
    private HardwareMap hMap = null;
    private Telemetry tel = null;
    private Gamepad gpad;
    private DcMotor MotorLiftStanga = null, MotorLiftDreapta = null;
    private float speed = 50; //The coefficient of how much the input ifluences the movement of the lift
    private final int topLimit = 3000, lowLimit = 0; //Limit the height of the lift
    public int liftVirtualPozition = 0;
    @Override
    public void init(OpMode opmode) {
        // Splitting the opmode into hardwaremap and telemetry
        hMap = opmode.hardwareMap;
        tel = opmode.telemetry;
        gpad = opmode.gamepad2;
        if(MotorLiftStanga == null|| MotorLiftDreapta ==null)
            resetEncoders();
        liftVirtualPozition = 0;
    }
    @Override
    public void loop(Buttons buttons) {
        float y = gpad.right_stick_y;
        if (hMap == null || tel == null) tel.addData("ERROR #001", this);

        if(!isInParameter(-y)[0]||!isInParameter(-y)[1]) tel.addData("ERROR #002 : OUT OF BOUNDS", this);
        else {
        liftVirtualPozition =Math.round(liftVirtualPozition - y * speed);
            updatePozition(liftVirtualPozition);
            tel.addData("LIFT:POZ: ", MotorLiftDreapta.getCurrentPosition());
        }
    }
    public void resetEncoders(){
        MotorLiftStanga = initialized(hMap.get(DcMotor.class,"MotorLiftStanga"), DcMotorSimple.Direction.FORWARD);
        MotorLiftDreapta = initialized(hMap.get(DcMotor.class,"MotorLiftDreapta"), DcMotorSimple.Direction.REVERSE);
    }


    public boolean[] isInParameter(float y) {
        float newPoz = liftVirtualPozition - y * speed;
        return new boolean[]{!((newPoz > topLimit) && (y >= 0)), !((newPoz < lowLimit) && (y <= 0))};
    }

    public void updatePozition(int value){
        MotorLiftStanga.setTargetPosition(value);
        MotorLiftDreapta.setTargetPosition(value);

        MotorLiftDreapta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        MotorLiftStanga.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        MotorLiftStanga.setPower(1);
        MotorLiftDreapta.setPower(1);
    }
    private DcMotor initialized(DcMotor motor, DcMotorSimple.Direction direction){
        motor.setDirection(direction);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        return motor;
    }

}
