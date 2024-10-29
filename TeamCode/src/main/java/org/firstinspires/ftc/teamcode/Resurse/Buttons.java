package org.firstinspires.ftc.teamcode.Resurse;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Buttons extends Subsystem {
    public enum buton {
        UNPRESSED,
        PRESSING,
        PRESSED,
        UNPRESSING
    }
    private buton[] buttons={buton.UNPRESSED,buton.UNPRESSED,buton.UNPRESSED,buton.UNPRESSED};
    public boolean square,circle,triangle,cross;
    Gamepad gmp1,gmp2;
    Telemetry tele;


    @Override
    public void init(OpMode opmode) {
        gmp1 = opmode.gamepad1;
        gmp2 = opmode.gamepad2;
        tele=opmode.telemetry;
    }
    @Override
    public void loop(Buttons buttons) {
        circle = buttonToSwich(gmp2.b,0);
        square = buttonToSwich(gmp2.x,1);
        triangle = buttonToSwich(gmp2.y,2);
        cross = buttonToSwich(gmp2.a,3);

    }



    public boolean buttonToSwich(boolean button, int index){
        buton temp_button = buttons[index];
        if (button) {
            if (temp_button == buton.UNPRESSED) {
                buttons[index] = buton.PRESSING;
            }
            if (temp_button == buton.PRESSED) {
                buttons[index] = buton.UNPRESSING;
            }
        }
        if (!button) {
            if (temp_button == buton.UNPRESSING)
                buttons[index] = buton.UNPRESSED;
            if (temp_button == buton.PRESSING)
                buttons[index] = buton.PRESSED;
        }

        return (buttons[index] == buton.PRESSED);
    }
    /*ElapsedTime et, lastDebounceTime, timeFromPressed;
    boolean buttonState = false;
    boolean debounced = false;
    public boolean buttonToSwich(boolean buton) {

        if (et == null) {
            et = new ElapsedTime();
            et.reset();
        }
        if (lastDebounceTime == null) {
            lastDebounceTime = new ElapsedTime();
            lastDebounceTime.reset();
        }

        if (buton) {
            lastDebounceTime.reset();
        }
        if ((et.milliseconds() - lastDebounceTime.milliseconds()) > 20) {
            if (buton != buttonState) {
                buttonState = buton;
                if (buttonState) {
                    debounced = !debounced;
                }
                et.reset();
            }
        }

        return debounced;
    }*/
}
