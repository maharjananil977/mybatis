package org.personsal.mybatis.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/** created by: maharjananil created on: 11/26/2024 */
public class DateUtils {
    private DateUtils(){}

    public static long getTimestamp(){
        return LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }

}
