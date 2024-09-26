package org.firstinspires.ftc.teamcode.Resurse;

import com.qualcomm.robotcore.util.ElapsedTime;

public class Button {
    ElapsedTime et, lastDebounceTime, timeFromPressed;
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
    }
}
