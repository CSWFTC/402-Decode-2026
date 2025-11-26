package org.firstinspires.ftc.teamcode

import com.pedropathing.geometry.Pose
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous(name = "RED Auton")
class AutonRed : Auton() {
    override fun getWaypoints(): Waypoints {
        return w
    }

    companion object {
        @JvmField
        var w =
            Waypoints(
                Pose(56.0, 8.0, 90.0),
                Pose(40.0, 36.0, 180.0),
                Pose(0.0, 24.0, 0.0),
                Pose(-18.0, 0.0, 0.0),
                Pose(60.0, 96.0, 30.0)
            )
    }
}
