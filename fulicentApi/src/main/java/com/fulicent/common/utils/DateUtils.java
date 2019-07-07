package com.fulicent.common.utils;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    private static String _yyyy_MM_dd__HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    private static String _yyyyMMddHH = "yyyyMMddHH";

    private static DateTimeFormatter formatter_yyyy_MM_dd__HH_mm_ss = DateTimeFormatter.ofPattern(_yyyy_MM_dd__HH_mm_ss);

    public static String from_yyyy_MM_dd__HH_mm_ss_to_iso8601(String arg) {
        if (!StringUtils.isEmpty(arg)) {
            ZonedDateTime localDateTime = ZonedDateTime.of(LocalDateTime.from(formatter_yyyy_MM_dd__HH_mm_ss.parse(arg)), ZoneId.systemDefault());
            return DateTimeFormatter.ISO_ZONED_DATE_TIME.format(localDateTime);
        }
        return null;
    }
    
    public static String FormatDate(String format){
    	SimpleDateFormat date=new SimpleDateFormat(format);
    	return date.format(new Date());
    }
    
    
}
