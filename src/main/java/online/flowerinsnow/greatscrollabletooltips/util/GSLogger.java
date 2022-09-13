package online.flowerinsnow.greatscrollabletooltips.util;

import online.flowerinsnow.greatscrollabletooltips.GreatScrollableToolTips;
import org.apache.logging.log4j.Logger;

public class GSLogger {
    public static Logger getLogger() {
        return GreatScrollableToolTips.getLogger();
    }

    public static void info(String message) {
        getLogger().info(message);
    }

    public static void warn(String message) {
        getLogger().warn(message);
    }

    public static void error(String message) {
        getLogger().error(message);
    }

    public static void error(String message, Throwable throwable) {
        getLogger().error(message, throwable);
    }

    private GSLogger() {
    }
}
