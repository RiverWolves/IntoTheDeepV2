package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MyClass {
    public static void main(String[] args) {
        float lenght = 13 ,wight = 12.4f;
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), wight)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(70/6,-70+lenght, Math.toRadians(90)))
                        .lineToY(-70/3 -lenght)
                        .setReversed(true)
                        .strafeTo(new Vector2d(70/3,-70+lenght))
                

                        .strafeTo(new Vector2d(70/3,-70+lenght))
                        .strafeTo(new Vector2d(70-wight,-70+lenght))
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
