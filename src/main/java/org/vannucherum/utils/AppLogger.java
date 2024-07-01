package org.vannucherum.utils;

import java.util.logging.Logger;

public class AppLogger extends BaseUtil {
    private AppLogger() {
        super();
    }
    private static final Logger logger = Logger.getLogger(AppLogger.class.getName());

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logWarning(String message) {
        logger.warning(message);
    }

    public static void logError(String message) {
        logger.severe(message);
    }
}
