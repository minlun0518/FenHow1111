package com.lunlun.fenhow1219;

import android.text.format.DateFormat;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtils {
    public static final SimpleDateFormat DATE_FORMAT_CHINESE = new SimpleDateFormat("yyyy年MM月dd日");
    public static final SimpleDateFormat DATE_FORMAT_CHINESE_MD = new SimpleDateFormat("MM月dd日");
    public static final SimpleDateFormat DATE_FORMAT_CHINESE_MD_SLASH = new SimpleDateFormat("MM/dd");
    public static final SimpleDateFormat DATE_FORMAT_DASH = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_DAY_TO_SEC = new SimpleDateFormat("ddHHmmss");
    public static final SimpleDateFormat DATE_FORMAT_NON = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final SimpleDateFormat DATE_FORMAT_ORIGINAL = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat DATE_FORMAT_SLASH = new SimpleDateFormat("yyyy/MM/dd");
    public static final SimpleDateFormat DATE_TIME_FORMAT_SLASH = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    public static String formatDash(Calendar calendar) {
        return DATE_FORMAT_DASH.format(calendar.getTime());
    }

    public static String formatOriginal(Calendar calendar) {
        return DATE_FORMAT_ORIGINAL.format(calendar.getTime());
    }

    public static String formatNon(Calendar calendar) {
        return DATE_FORMAT_NON.format(calendar.getTime());
    }

    public static String formatDefault(Calendar calendar) {
        return DATE_FORMAT_SLASH.format(calendar.getTime());
    }

    public static String formatDefaultWithDayOfWeek(Calendar calendar) {
        return formatWithDayOfWeek(calendar, DATE_FORMAT_SLASH);
    }

    public static String formatChineseMDWithDayOfWeek(Calendar calendar) {
        return formatWithDayOfWeek(calendar, DATE_FORMAT_CHINESE_MD);
    }

    public static String formatWithDayOfWeek(Calendar calendar, SimpleDateFormat simpleDateFormat) {
        return String.format("%1$s (%2$s)", new Object[]{simpleDateFormat.format(calendar.getTime()), getDayOfWeekText(calendar.get(7))});
    }

    public static String getDayOfWeekText(int i) {
        switch (i) {
            case 0:
                return "六";
            case 1:
                return "日";
            case 2:
                return "一";
            case 3:
                return "二";
            case 4:
                return "三";
            case 5:
                return "四";
            case 6:
                return "五";
            case 7:
                return "六";
            default:
                return Integer.toString(i);
        }
    }

    public static String parserDateFormat(String str) {
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM/dd");
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return simpleDateFormat2.format(date);
    }

    public static String parserDateFormatSlash(String str) {
        Date date;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return DATE_FORMAT_SLASH.format(date);
    }

    public static String parserStartSeeTimeFormat(String str) {
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return simpleDateFormat2.format(date);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static String parserWeek(String r6) {

        throw new UnsupportedOperationException("Method not decompiled: org.cgh.app.util.DateUtils.parserWeek(java.lang.String):java.lang.String");
    }

    public static String parserDayOfWeekText(String str) {
        Calendar instance = Calendar.getInstance();
        try {
            instance.setTime(DATE_FORMAT_ORIGINAL.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getDayOfWeekText(instance.get(7));
    }

    public static String parserSlashDateFormat(String str) {
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return simpleDateFormat2.format(date);
    }

    public static class DayNCKUH {
        public static Date Str2Date(String _dateFormat, String _dateString) {
            try {
                return new SimpleDateFormat(_dateFormat).parse(_dateString);
            } catch (ParseException e) {
                return null;
            }
        }

        public static String Date2Str(String _dateFormate, Date _date) {
            return new SimpleDateFormat(_dateFormate).format(_date);
        }

        public static String GetDateParts(Date _date, String _dateParts) {
            return (String) DateFormat.format(_dateParts, _date);
        }

        public static String ChineseMonth(String _month) {
            try {
                switch (Integer.parseInt(_month)) {
                    case 1:
                        return "一月";
                    case 2:
                        return "二月";
                    case 3:
                        return "三月";
                    case 4:
                        return "四月";
                    case 5:
                        return "五月";
                    case 6:
                        return "六月";
                    case 7:
                        return "七月";
                    case 8:
                        return "八月";
                    case 9:
                        return "九月";
                    case 10:
                        return "十月";
                    case 11:
                        return "十一月";
                    case 12:
                        return "十二月";
                    default:
                        return _month;
                }
            } catch (Exception e) {
                return _month;
            }
        }

        public static String ChineseWeekday(String _weekday) {
            try {
                switch (Integer.parseInt(_weekday)) {
                    case 1:
                        return "日";
                    case 2:
                        return "一";
                    case 3:
                        return "二";
                    case 4:
                        return "三";
                    case 5:
                        return "四";
                    case 6:
                        return "五";
                    case 7:
                        return "六";
                    default:
                        return _weekday;
                }
            } catch (Exception e) {
                return _weekday;
            }
        }

        public static long DateDiff(Date s, Date e) {
            return e.getTime() - s.getTime();
        }

        public static Date DateAdd(Date d, int num) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(2, num);
            return cal.getTime();
        }

        public static String Long2Str(Long time, String formatStr) {
            Format format = new SimpleDateFormat(formatStr);
            Calendar c = Calendar.getInstance();
//            c.setTimeInMillis(time.longValue() / NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
            return format.format(c.getTime());
        }
    }


}

