package com.cultivation.javaBasic.showYourIntelligence;

public class StackFrameHelper {
    public static String getCurrentMethodName() {
        // TODO: please modify the following code to pass the test
        // <--start
        Throwable throwable = new Throwable();
        StackTraceElement[] stackFrames = throwable.getStackTrace();
        if (stackFrames.length > 1) {
            return stackFrames[1].getClassName() + "." + stackFrames[1].getMethodName();
        }

        return "";
        // --end-->
    }
}
