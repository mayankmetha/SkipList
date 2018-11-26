package utils;

public class trace {

    private static Boolean gFlag = true;
    private static int gMaxSteps = 1;
    private static int gCurStep = 0;

    public static void setFlag(Boolean flag) {
        gFlag = flag;
    }

    public static Boolean getFlag() {
        return gFlag;
    }

    public static int getMaxSteps() {
        return gMaxSteps;
    }

    public static void setMaxSteps(int maxSteps) {
        gMaxSteps = maxSteps;
    }

    public static int getCurStep() {
        return gCurStep;
    }

    public static void setCurStep(int curStep) {
        gCurStep = curStep;
    }

}
