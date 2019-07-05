package com.cgl.test;

import com.cgl.tools.DateConventer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: CGL
 * @Date: 2019/7/4 14:55
 * @Description:  时间计算
 */
public class DateMath {
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
      //  long s = (between / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if(day>0)
            stringBuilder.append(day+"天");
        if(hour>0)
            stringBuilder.append(hour+"小时");
        if(min>0)
            stringBuilder.append(min+"分钟");
       /* long ms = (between - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                - min * 60 * 1000 - s * 1000);*/
        return stringBuilder.toString();
    }
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String str = "2019-07-04 15:41:02";
        String str2 = "2019-07-04 17:10:52";

        System.out.println(dd.parse(str).compareTo(dd.parse(str2)));
        System.out.println(TimeDifference(dd.parse(str).getTime(),dd.parse(str2).getTime()));
        System.out.println(DateConventer.dateConvent(str));
        try {
            Date date2 = dd.parse(str);
            System.out.println(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        Date date1 = null;
        date1 = calendar.getTime();
        System.out.println(dd.format(calendar.getTime()));
        System.out.println("初始值达特："+date1);
        calendar.add(Calendar.HOUR_OF_DAY,11);
        date1 = calendar.getTime();
        System.out.println("修改后的"+date1);
        System.out.println("两个小时后的时间"+dd.format(calendar.getTime()));
    }
}
