package com.samuel.product.server.api.util;

import java.time.LocalDateTime;

public class DateUtil {
    public static LocalDateTime nowDateTime() {
        return LocalDateTime.now().withNano(0);
    }
}
