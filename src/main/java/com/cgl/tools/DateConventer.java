package com.cgl.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: CGL
 * @Date: 2019/7/4 16:46
 * @Description: 时间格式转换器
 */
public class DateConventer {

    /**
     * 计算时间差 返回两个时间之间的时间间隔
     * @param start
     * @param end
     * @return
     */
    public static String TimeDifference(long start, long end) {
        StringBuilder stringBuilder = null;
        if(start>end){
            return "false";
        }
        stringBuilder = new StringBuilder();
        long between = end - start;
        long day = between / (24 * 60 * 60 * 1000);
        long hour = (between / (60 * 60 * 1000) - day * 24);
        long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if(day>0)
            stringBuilder.append(day+"天");
        if(hour>0)
            stringBuilder.append(hour+"小时");
        if(min>0)
            stringBuilder.append(min+"分钟");
        if(s>0)
            stringBuilder.append(s+"秒");
       /* long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                - min * 60 * 1000 - s * 1000);*/
        return stringBuilder.toString();
    }

    public static Date dateConvent(String str) throws ParseException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        date =  sdf.parse(str);
        return date;
    }
}
