package org.firstinspires.ftc.teamcode.helper;

import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Reverse {
}

// singleton for easy access to hardware peripherals
public class Hardware {
    // you can add more peripherals here
    // just declare a new field
    // the field name should match the name in the robot config
    // that's it!
    public static DcMotor frontLeft;
    @Reverse
    public static DcMotor frontRight;
    @Reverse
    public static DcMotor rearLeft;
    public static DcMotor rearRight;

    public static WebcamName camera;

    @Reverse
    public static DcMotor intake;
    public static DcMotor outtakeTop;
    public static DcMotor outtakeBottom;
    public static DcMotor outtakeMiddle;

    // initialization code
    public static void init(HardwareMap map) {
        Field[] fields = Hardware.class.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                try {
                    field.set(null, map.get(field.getType(), field.getName()));
                    if (field.getType() == DcMotor.class) {
                        Reverse r = field.getAnnotation(Reverse.class);
                        if (r != null) {
                            DcMotor m = (DcMotor) field.get(null);
                            assert m != null;
                            m.setDirection(DcMotorSimple.Direction.REVERSE);
                        }
                    }
                } catch (IllegalAccessException ignored) {
                    // maybe add something here
                }
            }
        }
    }

    public static MecanumConstants setMotorDirections(MecanumConstants consts) {
        Field[] fields = Hardware.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == DcMotor.class) {
                DcMotorSimple.Direction dir = field.getAnnotation(Reverse.class) == null ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE;
                switch (field.getName()) {
                    case "frontLeft":
                        consts = consts.leftFrontMotorDirection(dir);
                        break;
                    case "frontRight":
                        consts = consts.rightFrontMotorDirection(dir);
                        break;
                    case "rearLeft":
                        consts = consts.leftRearMotorDirection(dir);
                        break;
                    case "rearRight":
                        consts = consts.rightRearMotorDirection(dir);
                        break;
                }
            }
        }
        return consts;
    }
}