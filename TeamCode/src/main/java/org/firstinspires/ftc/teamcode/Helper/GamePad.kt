package org.firstinspires.ftc.teamcode.Helper

import android.os.SystemClock
import com.qualcomm.robotcore.hardware.Gamepad
import java.util.Date

/* Copyright (c) 2023 FIRST Tech Challenge - Team #404 “=ma” (https://...)
*
* Permission is granted, free of charge, to any person obtaining a copy of this software and
* associated documentation (collectively, the "Software") to use the Software without restriction,
* including without limitation, the rights to use, copy, modify, merge, publish, distribute,
* sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
* furnished to do so; subject to the copyright notice above and this permission notice being
* shall be included in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS"WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
* BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NON-INFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY DIRECT,
* INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES OR OTHER LIABILITY,
* WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
* WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

/*
 * This class demonstrates how to extend an external "hardware" class (Gamepad) in order to
 * modularize the processing of the robot's sensors and actuators.
 *
 * It reads all the inputs from Gamepad class and converts them to an enumerated InputType
 * which it assigns to the the highest priority input at that moment.
 *
 * The class also handle issues related to human inputs, such as enforcing lockout intervals
 * to prevent duplicate inputs from a control being held. This is supported in three modes:
 *
 *     Button:  Restricts input of same button for a the lockout interval
 *     DPad:    Extends Button restriction but hardware only allows on
 *     Trigger: Detects edge transition ON/OFF
 *     Joystick: A very small lockout interval reduces the number of times the same
 *               joystick position registers as a new input
 *
 *  This class collects telemetry data about its most recent values and exposes
 *  that data via (getTelemetry_...) methods.
 */
/*
BucketHangPos = .42
ArmHangPos = .50
ElbowHangPos = .52
GrappleHangPos = .455
Push Update
 */
class GamePad //--------------------------------------------------------------
//                External Class Interface
// -------------------------------------------------------------
// Class Constructor
    (// Internal Variables - Gamepad, Lockout Counters, and Previous State Variables
    private val inputGPad: Gamepad
) {
    class Params {
        var waitLoopSleepInterval: Int = 20
        var buttonLockoutInterval: Int = 1000
        var dpadLockoutInterval: Int = 1000
        var triggerLockoutInterval: Int = 50
        var joystickButtonLockoutInterval: Int = 300
        var joystickLockoutInterval: Int = 20 // should be Small
    }

    enum class GameplayInputType(private val description: String) {
        NONE("No Input"),
        BUTTON_A("A Button"),
        BUTTON_B("B Button"),
        BUTTON_X("X Button"),
        BUTTON_Y("Y Button"),
        BUTTON_L_BUMPER("L Bumper"),
        BUTTON_R_BUMPER("R Bumper"),
        BUTTON_BACK("Back Button"),
        LEFT_TRIGGER("L Trigger"),
        RIGHT_TRIGGER("R Trigger"),
        DPAD_UP("DPad: UP"),
        DPAD_DOWN("DPad: DOWN"),
        DPAD_LEFT("DPad: LEFT"),
        DPAD_RIGHT("DPad: RIGHT"),
        JOYSTICK("Joystick"),
        LEFT_STICK_BUTTON_ON("Left Joystick Button On"),
        LEFT_STICK_BUTTON_OFF("Left Joystick Button Off"),
        RIGHT_STICK_BUTTON_ON("Right Joystick Button On"),
        RIGHT_STICK_BUTTON_OFF("Right Joystick Button Off");

        override fun toString(): String {
            return description
        }
    }


    //--------------------------------------------------------------
    //                        CONSTANTS
    // -------------------------------------------------------------
    // TODO: Move these constants to FTC @Config parameters
    // Timeouts Needed to Debounce Gamepad Inputs (Milliseconds)
    // Telemetry Data
    private var tlm_WaitLoopCount = 0
    private var tlm_WaitLoopLastTimestamp = Date()
    private var tlm_InputCount = 0
    private var tlm_InputLastType: GameplayInputType? = GameplayInputType.NONE
    private var tlm_InputLastTimestamp = Date()


    private var LastButtonInputTime: Long = 0
    private var LastButtonInput = GameplayInputType.NONE

    private var LeftTriggerLast = 0f
    private var RightTriggerLast = 0f
    private var LastTriggerInputTime: Long = 0

    private var LastDPadInputTime: Long = 0
    private var LastDPadInput = GameplayInputType.NONE

    private var LastJoystickInputTime: Long = 0
    private var LastLeftJoystickX = 0f
    private var LastLeftJoystickY = 0f
    private var LastRightJoystickX = 0f
    private var LastRightJoystickY = 0f

    private var LeftJoystickButtonOn = false
    private var RightJoystickButtonOn = false


    val telemetry_WaitLoopCount: Int
        // Telemetry Data Getters
        get() = (tlm_WaitLoopCount)

    val telemetry_WaitLoopLastTimestamp: Date?
        get() = (tlm_WaitLoopLastTimestamp)

    val telemetry_InputCount: Int
        get() = (tlm_InputCount)

    val telemetry_InputLastType: GameplayInputType?
        get() = (tlm_InputLastType)

    val telemetry_InputLastTimestamp: Date?
        get() = (tlm_InputLastTimestamp)


    /*
     * WaitForGamepadInput:  Repeatedly checks for Gamepad Inputs until Timeout expires.
     */
    fun WaitForGamepadInput(msTimeout: Int): GameplayInputType? {
        // Loop until a Gamepad input or specified timeout (milliseconds)
        val ms_start = System.currentTimeMillis()
        val ms_end = ms_start + msTimeout

        var newInput: GameplayInputType? = GameplayInputType.NONE

        while (System.currentTimeMillis() < ms_end && newInput == GameplayInputType.NONE) {
            ++tlm_WaitLoopCount
            tlm_WaitLoopLastTimestamp = Date()

            newInput = GetGamepadInput()
            if (newInput == GameplayInputType.NONE) SystemClock.sleep(PARAMS.waitLoopSleepInterval.toLong())
            else {
                // Return New Input
                ++tlm_InputCount
                tlm_InputLastTimestamp = Date()
                tlm_InputLastType = newInput
            }
        }

        return newInput
    }


    //--------------------------------------------------------------
    //                Internal Helper Functions
    // -------------------------------------------------------------
    /*
     * GetInput:  Checks for Gamepad Inputs in Order of Importance (most to least)
     */
    private fun GetGamepadInput(): GameplayInputType? {
        // Check for Buttons
        var intype = GetButton()
        if (intype != GameplayInputType.NONE) return (intype)

        // Check for DPad
        intype = GetDPad()
        if (intype != GameplayInputType.NONE) return (intype)

        // Check for Joystick Button
        intype = GetJoystickButton()
        if (intype != GameplayInputType.NONE) return (intype)

        // Check for Triggers
        intype = GetTrigger()
        //Triggers will be more noisy, meaning even shaking hands will provide some float value for triggers.
        if (intype != GameplayInputType.NONE) return (intype)

        // Check for Joystick
        intype = GetJoystick() // Defaults to InputType None
        return (intype)
    }


    /*
     * GetButton: Check for Gamepad Button Inputs and Disregards Duplicates During Lockout Period.
     *            Buttons are checked in Order of Importance (most to least).
     *            Returns an input type of NONE when no button is pressed.
     */
    private fun GetButton(): GameplayInputType {
        var intype = GameplayInputType.NONE

        // Check for Button Inputs
        if (inputGPad.a) intype = GameplayInputType.BUTTON_A
        if (inputGPad.b) intype = GameplayInputType.BUTTON_B
        if (inputGPad.x) intype = GameplayInputType.BUTTON_X
        if (inputGPad.y) intype = GameplayInputType.BUTTON_Y
        if (inputGPad.left_bumper) intype = GameplayInputType.BUTTON_L_BUMPER
        if (inputGPad.right_bumper) intype = GameplayInputType.BUTTON_R_BUMPER
        if (inputGPad.back) intype = GameplayInputType.BUTTON_BACK

        // Check For Duplicate Button Input and Disregard Same Button During Lockout Period
        val lockedOut =
            ((LastButtonInputTime + PARAMS.buttonLockoutInterval) - System.currentTimeMillis()) > 0

        if (intype == LastButtonInput && lockedOut) {
            intype = GameplayInputType.NONE
        } else {
            LastButtonInput = intype
            LastButtonInputTime = System.currentTimeMillis()
        }
        return (intype)
    }


    /*
     * Get DPad: Checks for Gamepad DPad (Upper Left) Input Changes and Disregards Changes During
     *           the Lockout Period.
     *           Returns an input type of NONE when no Trigger change is detected.
     */
    private fun GetDPad(): GameplayInputType {
        var intype = GameplayInputType.NONE

        // Check for DPad Inputs
        if (inputGPad.dpad_up) intype = GameplayInputType.DPAD_UP
        if (inputGPad.dpad_down) intype = GameplayInputType.DPAD_DOWN
        if (inputGPad.dpad_left) intype = GameplayInputType.DPAD_LEFT
        if (inputGPad.dpad_right) intype = GameplayInputType.DPAD_RIGHT

        // Check For Duplicate DPad Input and Disregard Same Input During Lockout Period
        val lockedOut =
            ((LastDPadInputTime + PARAMS.dpadLockoutInterval) - System.currentTimeMillis()) > 0

        if (intype == LastDPadInput && lockedOut) {
            intype = GameplayInputType.NONE
        } else {
            LastDPadInput = intype
            LastDPadInputTime = System.currentTimeMillis()
        }
        return (intype)
    }


    /*
     * Get Joystick Button : Checks for Gamepad Joystick Button Input Changes and Disregard Changes During the
     *              Lockout Period.  It captures transitions and generates an ON/OFF Input
     *              Returns an input type of NONE when no change is detected.
     */
    private fun GetJoystickButton(): GameplayInputType {
        val lockedOut =
            ((LastTriggerInputTime + PARAMS.joystickButtonLockoutInterval) - System.currentTimeMillis()) > 0

        if (!lockedOut) {
            if (inputGPad.left_stick_button && !LeftJoystickButtonOn) {
                LeftJoystickButtonOn = true
                return (GameplayInputType.LEFT_STICK_BUTTON_ON)
            } else if (!inputGPad.left_stick_button && LeftJoystickButtonOn) {
                LeftJoystickButtonOn = false
                return (GameplayInputType.LEFT_STICK_BUTTON_OFF)
            }

            if (inputGPad.right_stick_button && !RightJoystickButtonOn) {
                RightJoystickButtonOn = true
                return (GameplayInputType.RIGHT_STICK_BUTTON_ON)
            } else if (!inputGPad.right_stick_button && RightJoystickButtonOn) {
                RightJoystickButtonOn = false
                return (GameplayInputType.RIGHT_STICK_BUTTON_OFF)
            }
        }

        return (GameplayInputType.NONE) // Catch all for No Joystick Input
    }

    /*
     * Get Trigger: Checks for Gamepad Trigger Input Changes and Disregard Changes During the
     *              Lockout Period.  It converts the floating Trigger value to an ON/OFF Input
     *                 Trigger is considered ON when it transitions over 75%
     *                 Trigger is considered OFF when it transitions under 25%
     *              Returns an input type of NONE when no Trigger change is detected.
     */
    private fun GetTrigger(): GameplayInputType {
        val lockedOut =
            ((LastTriggerInputTime + PARAMS.triggerLockoutInterval) - System.currentTimeMillis()) > 0


        // Trigger Moved or Remaining in Same (Non Resting) Position Past Lockout Interval
        if (!lockedOut) {
            val newLeft = (inputGPad.left_trigger != LeftTriggerLast)
            val atRestL = (inputGPad.left_trigger == 0f)
            if (newLeft || !atRestL) {
                LastTriggerInputTime = System.currentTimeMillis()
                LeftTriggerLast = inputGPad.left_trigger
                return (GameplayInputType.LEFT_TRIGGER)
            }

            val newRight = (inputGPad.right_trigger != RightTriggerLast)
            val atRestR = (inputGPad.right_trigger == 0f)
            if (newRight || !atRestR) {
                LastTriggerInputTime = System.currentTimeMillis()
                RightTriggerLast = inputGPad.right_trigger
                return (GameplayInputType.RIGHT_TRIGGER)
            }
        }

        return (GameplayInputType.NONE) // Catch all for No Joystick Input
    }


    /*
     * Get Joystick: Checks for Change in Joystick Input Changes and Disregards Changes During
     *               the Lockout Period.
     *               Returns an input type of NONE when no Joystick change is detected.
     *    IMPORTANT: Joysticks are heavily used, their Lockout period should be small
     */
    private fun GetJoystick(): GameplayInputType {
        // Check for Change in Joystick Position
        val newPosition = (inputGPad.left_stick_x != LastLeftJoystickX) ||
                (inputGPad.left_stick_y != LastLeftJoystickY) ||
                (inputGPad.right_stick_x != LastRightJoystickX) ||
                (inputGPad.right_stick_y != LastRightJoystickY)

        // Check for Joystick at Rest
        val atRest = (inputGPad.left_stick_x == 0f) && (inputGPad.left_stick_y == 0f) &&
                (inputGPad.right_stick_x == 0f) && (inputGPad.right_stick_y == 0f)

        val lockedOut =
            ((LastJoystickInputTime + PARAMS.joystickLockoutInterval) - System.currentTimeMillis()) > 0

        // Joystick Moved or Remaining in Same (Non Resting) Position Past Lockout Interval
        if (!lockedOut) {
            if (newPosition || !atRest) {
                LastJoystickInputTime = System.currentTimeMillis()
                LastLeftJoystickX = inputGPad.left_stick_x
                LastLeftJoystickY = inputGPad.left_stick_y
                LastRightJoystickX = inputGPad.right_stick_x
                LastRightJoystickY = inputGPad.right_stick_y
                return (GameplayInputType.JOYSTICK)
            }
        }

        return (GameplayInputType.NONE) // Catch all for No Joystick Input
    }

    companion object {
        /*
    Accessible HapticsController inside of GamePad class
    Based on GamepadHaptics class
     */
        var PARAMS: Params = Params()
    }
}