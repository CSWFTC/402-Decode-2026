package org.firstinspires.ftc.teamcode

import com.bylazar.configurables.annotations.Configurable
import com.pedropathing.geometry.Pose
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous(name = "BLUE Auton")
@Configurable
class AutonBlue : Auton() {

    override fun getWaypoints(): Waypoints {
        return w
    }

    companion object {
        @JvmField
        var w = Waypoints(
            Pose(121.514, 119.135, 0.0),
            Pose(99.459, 83.243, 0.0),
            Pose(124.541, 83.459, 0.0),
            Pose(73.081, 73.514, 45.0),
            Pose(71.784, 53.622, 45.0)
        )
    }
}
