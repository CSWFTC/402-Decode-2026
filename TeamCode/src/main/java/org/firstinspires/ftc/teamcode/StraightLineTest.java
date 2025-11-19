package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.Simulation.OpModeBase;

public class StraightLineTest extends OpModeBase {
    public static StraightLineTest Init(){
        return new StraightLineTest();
    }
    @Override
    public void Run(Follower follower) {
        System.out.println("Starting straight line test");
        follower.followPath(follower.pathBuilder().addPath(new BezierLine(new Pose(0, 0), new Pose(0, 12))).build());
    }
}
