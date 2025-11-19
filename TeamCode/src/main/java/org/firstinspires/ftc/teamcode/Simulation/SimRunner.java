package org.firstinspires.ftc.teamcode.Simulation;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;

public class SimRunner {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> cls = Class.forName(args[0]);
        OpModeBase b = (OpModeBase) cls.newInstance();
        b.Run(new Follower(new FollowerConstants(), new SimLocalizer(), new SimDrivetrain()));
    }
}
