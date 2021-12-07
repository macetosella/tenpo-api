package com.tenpo.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateUtil {

    public static Instant now() {
        return LocalDateTime.now().toInstant(ZoneOffset.UTC);
    }
}
