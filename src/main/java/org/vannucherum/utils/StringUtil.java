package org.vannucherum.utils;

import java.util.UUID;

public class StringUtil extends BaseUtil {
    private StringUtil() {
        super();
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
