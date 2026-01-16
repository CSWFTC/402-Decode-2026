package org.firstinspires.ftc.teamcode.Helper;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

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

// use this annotation for things that you don't want to be initialized
// because they're not plugged in
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface DoNotInitialize {
}

// singleton for easy access to hardware peripherals
public class Hardware {
    // you can add more peripherals here
    // just declare a new field
    // the field name should match the name in the robot config
    // that's it!
    @DoNotInitialize
    public static DcMotor frontLeft;

    @Reverse
    @DoNotInitialize
    public static DcMotor frontRight;

    @Reverse
    @DoNotInitialize
    public static DcMotor rearLeft;
    @DoNotInitialize
    public static DcMotor rearRight;

    @DoNotInitialize
    public static WebcamName camera;

    @Reverse
    @DoNotInitialize
    public static DcMotor intake;
    @DoNotInitialize
    public static DcMotor outtakeTop;
    @DoNotInitialize
    public static DcMotor outtakeBottom;
    @DoNotInitialize
    public static DcMotor outtakeMiddle;

    @DoNotInitialize
    public static DcMotor outtakeLeft;

    @Reverse
    @DoNotInitialize
    public static DcMotor outtakeRight;

    public static Servo flapServo;

    // initialization code
    public static void init(HardwareMap map) {
        Field[] fields = Hardware.class.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers()) && field.getAnnotation(DoNotInitialize.class) == null) {
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

                }
            }
        }
    }
}
