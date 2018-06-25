package cn.nest.util;


import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
@SuppressWarnings(value = "unused")
public class DateUtil {
    private DateUtil() {

    }

    private final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static boolean is8Date(String date) {
        if (StringUtil.isEmpty(date)) {
            return false;
        }
        String eL = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
        Pattern pattern = Pattern.compile(eL);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public static String getSysDate(String format) {
        if (StringUtil.isEmpty(format)) {
            format = DEFAULT_FORMAT;
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    public static int getSysYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    public static Date getDateByString(String date, String format) {
        if (StringUtil.isEmpty(format)) {
            format = DEFAULT_FORMAT;
        }
        if (StringUtil.isNotEmpty(date)) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                return sdf.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException("转换为日期类型错误：DATE：" + date + "  FORMAT:" + format);
            }
        } else {
            return null;
        }
    }

    public static String getFormatDate(Date date, String format) {
        if (StringUtil.isEmpty(format)) {
            format = DEFAULT_FORMAT;
        }
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(format);
            return df.format(date);
        } else {
            return null;
        }
    }

    public static Date getDayStartOfDate(Date date) {
        String formatDate = getFormatDate(date, "yyyy-MM-dd");
        return StringUtil.isEmpty(formatDate) ? null : getDateByString(formatDate + " 00:00:00", "");
    }

    public static Date getDayEndOfDate(Date date) {
        String formatDate = getFormatDate(date, "yyyy-MM-dd");
        return StringUtil.isEmpty(formatDate) ? null : getDateByString(formatDate + " 23:59:59", "");
    }

    /**
     * 一月的最后一天
     *
     * @param date 日期
     * @return 当月最后一天
     */
    public static Date getDayEndOfMonth(Date date) {
        if (date == null) {
            date = new Date();
        }
        Date dayStartOfMonth = getDayStartOfMonth(date);
        Calendar cal = Calendar.getInstance();

        cal.setTime(dayStartOfMonth);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return getDayEndOfDate(cal.getTime());
    }

    /**
     * 一月的第一天
     *
     * @param date 日期
     * @return 当月第一天
     */
    public static Date getDayStartOfMonth(Date date) {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getDayStartOfDate(cal.getTime());
    }

    public static Date getDateByLongDate(Long millis) {
        if (millis == null) {
            return new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        return cal.getTime();

    }

    /**
     * 日期加减运算
     *
     * @param date 日期
     * @param day  加天数（减传负数)
     * @return 返回运算后日期
     */
    public static Date daysOperation(Date date, int day) {
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            //加天
            cal.add(Calendar.DATE, day);
            return cal.getTime();
        } else {
            return null;
        }
    }

    public static Date monthOperation(Date date, int month) {
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            //加天
            cal.add(Calendar.MONTH, month);
            return cal.getTime();
        } else {
            return null;
        }
    }

    public static String secToTime(int time) {
        String timeStr;
        int hour;
        int minute;
        int second;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    /**
     * 获取当前日期对象
     *
     * @return 当前日期对象
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 获取当前日期字符串
     *
     * @param format 日期格式
     * @return 当前日期字符串
     */
    public static String now(String format) {
        return format(now(), format);
    }

    /**
     * 格式化日期对象
     *
     * @param date   日期对象
     * @param format 日期格式
     * @return 当前日期字符串
     */
    public static String format(Date date, String format) {
        return new DateTime(date).toString(format);
    }

    /**
     * 格式化日期对象，格式为yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期对象
     * @return 日期字符串
     */
    public static String format(Date date) {
        return new DateTime(date).toString(DEFAULT_FORMAT);
    }

    /**
     * 格式化日期对象，格式为yyyy-MM-dd HH:mm:ss
     *
     * @param mills 毫秒
     * @return 日期字符串
     */
    public static String format(Long mills) {
        return new DateTime(mills).toString(DEFAULT_FORMAT);
    }

    /**
     * 格式化日期对象
     *
     * @param mills   毫秒
     * @param pattern 格式
     * @return 日期字符串
     */
    public static String format(Long mills, String pattern) {
        return new DateTime(mills).toString(pattern);
    }
}
