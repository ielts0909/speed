package test.netty.discard;

import java.util.Calendar;
import java.util.Date;

/**
 * User: gais.ch
 * Date: 15-5-4
 * Time: 上午11:10
 */
public class Main {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println((int) (getDayEnd(date) - date.getTime() / 1000));
    }

    /**
     * 获取指定时间的当天结束时间
     *
     * @param date
     * @return
     */
    public static long getDayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTimeInMillis();
    }
}
