package org.firstinspires.ftc.teamcode;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "BLUE Auton")
public class AutonBlue extends Auton {
    @Override
    Pose transform(Pose p) {
        return p.mirror();
    }
}
