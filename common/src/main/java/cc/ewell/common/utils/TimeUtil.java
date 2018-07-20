package cc.ewell.common.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by SuperFan on 2017/3/29.
 */
public class TimeUtil {


    /**
     * 判断某日与今天的前后
     *
     * @param other
     * @return
     */
    public static int getDayFromToday(Calendar other) {
        int result = 1;
        Calendar today = Calendar.getInstance();
        try {
            if (today.get(Calendar.YEAR) < other.get(Calendar.YEAR)) {
                result = 1;
                return result;
            } else if (today.get(Calendar.YEAR) > other.get(Calendar.YEAR)) {
                result = -1;
                return result;
            } else {
                if (today.get(Calendar.MONTH) < other.get(Calendar.MONTH)) {
                    result = 1;
                    return result;
                } else if (today.get(Calendar.MONTH) > other.get(Calendar.MONTH)) {
                    result = -1;
                    return result;
                } else {
                    if (today.get(Calendar.DAY_OF_MONTH) < other.get(Calendar.DAY_OF_MONTH)) {
                        result = 1;
                        return result;
                    } else if (today.get(Calendar.DAY_OF_MONTH) > other.get(Calendar.DAY_OF_MONTH)) {
                        result = -1;
                        return result;
                    } else {
                        result = 0;
                        return result;
                    }
                }
            }

        } catch (Exception e) {

        }

        return result;
    }

    /**
     * 根据日期计算属于第几天
     *
     * @param date 格式 yyyyMMdd
     */
    public static int getWeekOfYear(String date, String fomatString) {
        int result = 0;
        try {
            SimpleDateFormat df = new SimpleDateFormat(fomatString);
            Calendar cal = Calendar.getInstance();
            cal.setFirstDayOfWeek(Calendar.MONDAY); // 设置每周的第一天为星期一
//            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 每周从周一开始
//            cal.setMinimalDaysInFirstWeek(1); // 设置每周最少为1天
            cal.setTime(df.parse(date));
            switch (cal.get(Calendar.DAY_OF_WEEK)) {
                case 2:
                    result = 0;
                    break;
                case 3:
                    result = 1;
                    break;
                case 4:
                    result = 2;
                    break;
                case 5:
                    result = 3;
                    break;
                case 6:
                    result = 4;
                    break;
                case 7:
                    result = 5;
                    break;
                case 1:
                    result = 6;
                    break;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 把毫秒转化成日期
     *
     * @param dateFormat(日期格式，例如：MM/ dd/yyyy HH:mm:ss)
     * @param millSec(毫秒数)
     * @return
     */
    public static String transferLongToDate(String dateFormat, Long millSec) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }

    /**
     * 包装时间yyyy-MM-dd HH:mm
     *
     * @param date
     * @return
     */
    public static String getTime(Date date) {
        String result = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            result = format.format(date);
        } catch (Exception e) {

        }

        return result;
    }

    /**
     * 包装时间formatString
     *
     * @param date
     * @return
     */
    public static String getTimeForFormat(Date date, String formatString) {
        String result = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatString);
            result = format.format(date);
        } catch (Exception e) {

        }

        return result;
    }

    /**
     * 解析时间yyyy-MM-dd HH:mm
     *
     * @param formatTime
     * @return
     */
    public static Date getTimeDate(String formatTime) {

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = format.parse(formatTime);
            return date;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 判断date1 和 date2 是否是同一天
     */
    public static boolean isSameDay(String str1, String str2) {
        if (TextUtils.isEmpty(str1) || TextUtils.isEmpty(str2)) {
            return false;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date1 = sdf.parse(str1);
            Date date2 = sdf.parse(str2);

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);

            boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                    .get(Calendar.YEAR);
            boolean isSameMonth = isSameYear
                    && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
            boolean isSameDate = isSameMonth
                    && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                    .get(Calendar.DAY_OF_MONTH);
            return isSameDate;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断str 是否是今天
     */
    public static boolean isToday(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String today = sdf.format(new Date());
            return isSameDay(str, today);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断给定的str 是否是昨天
     */
    public static boolean isYesterday(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -1);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String yesterday = sdf.format(calendar.getTime());
            return isSameDay(str, yesterday);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 两个时间之间相差距离多少分钟
     *
     * @param str1 时间参数 1：
     * @param str2 时间参数 2：
     * @return 相差分钟数
     */
    public static long getDistanceMinutes(String str1, String str2) {
        if (TextUtils.isEmpty(str1) || TextUtils.isEmpty(str2)) {
            return 0;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long minutes = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            minutes = diff / (1000 * 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return minutes;
    }

    /**
     * 距离现在多少分钟
     *
     * @return 相差分钟数
     */
    public static long getCurrentDistanceMinutes(String otherDayString) {
        if (TextUtils.isEmpty(otherDayString)) {
            return 0;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = new Date();
        Date otherDay;
        long minutes = 0;
        try {
            otherDay = df.parse(otherDayString);
            long time1 = today.getTime();
            long time2 = otherDay.getTime();
            long diff;
            diff = time2 - time1;
            minutes = diff / (1000 * 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return minutes;
    }

    /**
     * 计算两个时间的时间差time1-time2
     *
     * @param time1
     * @param time2
     * @return 差多少分钟
     */
    public static int diffTime(long time1, long time2) {
        long diff = Math.abs(time1 - time2);// 这样得到的差值是微秒级别
        return (int) (diff / (1000 * 60));
    }
}
