package org.firstinspires.ftc.teamcode

import com.bylazar.configurables.annotations.Configurable
import com.pedropathing.geometry.Pose
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous(name = "BLUE Auton")
@Configurable
class AutonBlue2 : Auton() {

    override fun getWaypoints(): Waypoints {
        return w
    }

    companion object {
        @JvmField
        var w = Waypoints(
            Pose(24.432, 118.919, -90.0),
            Pose(46.703, 83.243, 180.0),
            Pose(17.730, 83.676, 180.0),
            Pose(73.081, 73.514, 45.0),
            Pose(72.216, 58.162, 45.0)
        )
    }
}
