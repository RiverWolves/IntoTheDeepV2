package org.firstinspires.ftc.teamcode.Resurse;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Buttons extends Subsystem {
    public enum buton {
        UNPRESSED,
        PRESSING,
        PRESSED,
        UNPRESSING
    }
    private buton square_state,circle_state,triangle_state,cross_state ;
    public boolean square,circle,triangle,cross;
    Gamepad gmp1,gmp2;


    @Override
    public void init(OpMode opmode) {
        gmp1 = opmode.gamepad1;
        gmp2 = opmode.gamepad2;
    }
    @Override
    public void loop() {
       circle = buttonToSwich(gmp2.circle,circle_state);
        square = buttonToSwich(gmp2.square,square_state);
        triangle = buttonToSwich(gmp2.triangle,triangle_state);
        cross = buttonToSwich(gmp2.cross,cross_state);
    }



    public boolean buttonToSwich(boolean button, buton state){
        if(button) {
            switch (state) {
                case UNPRESSED: {
                    state = buton.PRESSING;
                }
                case PRESSED: {
                    state = buton.UNPRESSING;
                }
            }
        }
        else {
            switch (state) {
                case UNPRESSING: {
                    state = buton.UNPRESSED;
                }
                case PRESSING: {
                    state = buton.UNPRESSING;
                }
            }
        }
        return (state == buton.PRESSED);
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
