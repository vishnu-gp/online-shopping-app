package org.vannucherum.utils;

import java.util.UUID;

public class StringUtil {
    private StringUtil() {
        throw new AssertionError("Utility class should not be instantiated.");
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
