package org.firstinspires.ftc.teamcode

import com.bylazar.configurables.annotations.Configurable
import com.pedropathing.follower.Follower
import com.pedropathing.geometry.BezierLine
import com.pedropathing.geometry.Pose
import com.pedropathing.paths.PathChain
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.Helper.AprilTagConfig
import org.firstinspires.ftc.teamcode.Helper.BallOrder
import org.firstinspires.ftc.teamcode.Helper.Hardware
import org.firstinspires.ftc.teamcode.Helper.Shooter
import org.firstinspires.ftc.teamcode.pedroPathing.Constants

internal enum class State {
    GOING_TO_PICKUP,
    PICKING_UP,
    GOING_TO_LAUNCH,
    DONE
}

@Configurable
abstract class Auton : LinearOpMode() {
    override fun runOpMode() {
        Hardware.init(hardwareMap)
        val f = Constants.createFollower(hardwareMap)
        f.setStartingPose(start)
        val atConf = AprilTagConfig()
        val shooter = Shooter()
        while (atConf.order == null) {
            atConf.Update()
            sleep(20)
        }
        var pickup: Pose = pickupGPP
        when (atConf.order) {
            BallOrder.PPG -> pickup = pickup.plus(nextSetOffset * 2.0)
            BallOrder.PGP -> pickup = pickup.plus(nextSetOffset)
            else -> {}
        }
        telemetry.addData("Ball Order", atConf.order)
        telemetry.addLine()
        telemetry.update()
        waitForStart()
        val toPickup = between(f, start, pickup)
        val finishPickup = between(f, pickup, pickup.plus(lastBallOffset))
        val toLaunch = between(f, pickup.plus(lastBallOffset), launch)
        var s = State.GOING_TO_PICKUP
        f.followPath(toPickup)
        while (opModeIsActive()) {
            when (s) {
                State.GOING_TO_PICKUP -> if (!f.isBusy) {
                    shooter.SetIntake(true)
                    f.followPath(finishPickup)
                    s = State.PICKING_UP
                }

                State.PICKING_UP -> if (!f.isBusy) {
                    shooter.SetIntake(false)
                    f.followPath(toLaunch, true)
                    s = State.GOING_TO_LAUNCH
                }

                State.GOING_TO_LAUNCH -> if (!f.isBusy) {
                    shooter.SetOuttake(true)
                    s = State.DONE
                }

                State.DONE -> {}
            }
        }
    }

    // red should just return p, blue should mirror p
    abstract fun transform(p: Pose): Pose

    fun between(f: Follower, begin: Pose, end: Pose): PathChain? {
        var begin = begin
        var end = end
        begin = transform(begin)
        end = transform(end)
        return f.pathBuilder().addPath(BezierLine(begin, end))
            .setLinearHeadingInterpolation(begin.heading, end.heading).build()
    }

    companion object {
        // these poses are for the red side; blue will mirror them
        @JvmField // allows the dashboard to configure them
        var start: Pose = Pose(56.0, 8.0, 90.0)

        @JvmField
        var pickupGPP: Pose = Pose(40.0, 36.0, 180.0)

        @JvmField
        var nextSetOffset: Pose = Pose(0.0, 24.0)

        @JvmField
        var lastBallOffset: Pose = Pose(-16.0, 0.0)

        @JvmField
        var launch: Pose = Pose(56.0, 104.0, 35.0)
    }
}