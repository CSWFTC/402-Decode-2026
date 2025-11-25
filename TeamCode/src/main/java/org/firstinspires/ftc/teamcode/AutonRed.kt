package org.firstinspires.ftc.teamcode;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "RED Auton")
public class AutonRed extends Auton {
    @Override
    Pose transform(Pose p) {
        return p;
    }
}
