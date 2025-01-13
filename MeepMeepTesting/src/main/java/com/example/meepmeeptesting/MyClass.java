package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MyClass {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot1 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 12.4)
                .build();


        myBot1.runAction(myBot1.getDrive().actionBuilder(new Pose2d(8, -63, Math.toRadians(270)))
                .lineToY(-34)
                .waitSeconds(1)
                .lineToY(-45)
                .splineToConstantHeading(new Vector2d(37, -14), new Rotation2d(0, 2))
                .strafeTo(new Vector2d(48, -14))
                .waitSeconds(1)
                .strafeTo(new Vector2d(58, -14))
                .waitSeconds(1)
                .strafeTo(new Vector2d(61, -14))
                .strafeTo(new Vector2d(61, -50))
                .strafeTo(new Vector2d(61, -43))
                .strafeToLinearHeading(new Vector2d(47, -60), Math.toRadians(90))
                .strafeTo(new Vector2d(8, -34))
                .waitSeconds(1)
                .strafeTo(new Vector2d(47, -60))
                .waitSeconds(1)
                .strafeTo(new Vector2d(8, -34))
                .waitSeconds(1)
                .strafeTo(new Vector2d(47, -60))
                .waitSeconds(1)
                .strafeTo(new Vector2d(8, -34))
                .waitSeconds(1)
                .strafeTo(new Vector2d(56, -60))






                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot1)
                .start();

        RoadRunnerBotEntity myBot2 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 12.4)
                .build();

        myBot2.runAction(myBot2.getDrive().actionBuilder(new Pose2d(-8, -63, Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(-54, -54), Math.toRadians(45))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-48, -39), Math.toRadians(90))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-54, -54), Math.toRadians(45))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-58, -39), Math.toRadians(90))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-54, -54), Math.toRadians(45))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-58, -33), Math.toRadians(143))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-54, -54), Math.toRadians(45))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(-25, 0), Math.toRadians(0))


                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot2)
                .start();
    }
}
