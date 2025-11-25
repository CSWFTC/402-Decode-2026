package org.firstinspires.ftc.teamcode

import com.pedropathing.geometry.Pose
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous(name = "BLUE Auton")
class AutonBlue : Auton() {
    override fun transform(p: Pose): Pose {
        return p.mirror()
    }
}
