package com.sumslack.web.simple.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.zip.CRC32;

import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

public class Utils {

    public static final String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static final long cr32(String str) {
        CRC32 crc32 = new CRC32();
        crc32.update(str.getBytes());
        return crc32.getValue();
    }
    public static String formatNull(Object o) {
    	if(o == null) {
    		return "";
    	}else if(o.toString().equalsIgnoreCase("null")) {
    		return "";
    	}else {
    		return o.toString();
    	}
    }
    
    public static String formatNull(Object o,String defaultValue) {
    	if(o == null) {
    		return defaultValue;
    	}else if(o.toString().equalsIgnoreCase("null")) {
    		return defaultValue;
    	}else {
    		return o.toString();
    	}
    }

    public static String formatDate(Date date, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String md5(String text){
        Assert.notNull(text, "text is null");
        return DigestUtils.md5DigestAsHex(text.toString().getBytes());
    }
    
	public static Date parseDate(String strDate, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(format);
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

//    public static void main(String[] args) {
//        System.out.println(md5("123456"));
//    }


}
