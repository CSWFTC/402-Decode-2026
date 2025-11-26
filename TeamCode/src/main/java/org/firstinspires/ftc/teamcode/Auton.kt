package org.firstinspires.ftc.teamcode

import com.pedropathing.follower.Follower
import com.pedropathing.geometry.BezierLine
import com.pedropathing.geometry.Pose
import com.pedropathing.paths.PathChain
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.helper.AprilTagConfig
import org.firstinspires.ftc.teamcode.helper.BallOrder
import org.firstinspires.ftc.teamcode.helper.Hardware
import org.firstinspires.ftc.teamcode.helper.Shooter
import org.firstinspires.ftc.teamcode.pedroPathing.Constants

internal enum class State {
    GOING_TO_PICKUP,
    PICKING_UP,
    RETURN_TO_PICKUP,
    GOING_TO_LAUNCH,
    DONE
}

abstract class Auton : LinearOpMode() {
    abstract fun getWaypoints(): Waypoints
    override fun runOpMode() {
        val w = getWaypoints()
        Hardware.init(hardwareMap)
        val f = Constants.createFollower(hardwareMap)
        f.setStartingPose(w.start)
        val atConf = AprilTagConfig()
        val shooter = Shooter()
        while (atConf.order == null) {
            atConf.Update()
            sleep(20)
        }
        val pickup = w.pickupGPP.plus(
            when (atConf.order!!) {
                BallOrder.PPG -> w.nextSetOffset * 2.0
                BallOrder.PGP -> w.nextSetOffset
                BallOrder.GPP -> Pose(0.0, 0.0, 0.0)
            }
        )
        val pickupEnd = pickup.plus(w.lastBallOffset)
        telemetry.addData("Ball Order", atConf.order)
        telemetry.addLine()
        telemetry.update()
        waitForStart()
        val toPickup = between(f, w.start, pickup)
        val finishPickup = between(f, pickup, pickupEnd)
        val returnToPickup = between(f, pickupEnd, pickup)
        val toLaunch = between(f, pickupEnd, w.launch)
        var s = State.GOING_TO_PICKUP
        f.followPath(toPickup)
        while (opModeIsActive()) {
            when (s) {
                State.GOING_TO_PICKUP -> if (!f.isBusy) {
                    shooter.intake = true
                    f.followPath(finishPickup)
                    s = State.PICKING_UP
                }

                State.PICKING_UP -> if (!f.isBusy) {
                    shooter.intake = false
                    f.followPath(returnToPickup)
                    s = State.RETURN_TO_PICKUP
                }

                State.RETURN_TO_PICKUP -> if (!f.isBusy) {
                    f.followPath(toLaunch, true)
                    s = State.GOING_TO_LAUNCH
                }

                State.GOING_TO_LAUNCH -> if (!f.isBusy) {
                    shooter.outtake = true
                    s = State.DONE
                }

                State.DONE -> {}
            }
        }
    }

    fun between(f: Follower, begin: Pose, end: Pose): PathChain? {
        return f.pathBuilder().addPath(BezierLine(begin, end))
            .setLinearHeadingInterpolation(begin.heading, end.heading).build()
    }

    data class Waypoints(
        @JvmField
        val start: Pose,
        @JvmField
        val pickupGPP: Pose,
        @JvmField
        val nextSetOffset: Pose,
        @JvmField
        val lastBallOffset: Pose,
        @JvmField
        val launch: Pose
    )
}