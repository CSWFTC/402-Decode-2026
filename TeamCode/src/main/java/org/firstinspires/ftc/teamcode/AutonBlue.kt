package org.firstinspires.ftc.teamcode

import com.pedropathing.geometry.Pose
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous(name = "BLUE Auton")
class AutonBlue : Auton() {
    override fun getWaypoints(): Waypoints {
        return w
    }

    companion object {
        @JvmField
        var w =
            Waypoints(
                Pose(88.0, 8.0, 90.0),
                Pose(102.0, 36.0, 0.0),
                Pose(0.0, 24.0, 0.0),
                Pose(18.0, 0.0, 0.0),
                Pose(82.0, 96.0, 150.0)
            )
    }
}

