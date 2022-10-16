package ua.denis.dev.bot.utils;

import lombok.Getter;
import lombok.Setter;
import ua.denis.dev.bot.exception.WrongDateException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Date {
    @Setter @Getter
    private String day;
    @Setter @Getter
    private String month;
    @Setter @Getter
    private String year;

    public Date() {
    }

    public Date(int day, int month, int year) throws WrongDateException {
        if (month > 12) throw new WrongDateException("In months can only be 12 months");
        this.day = String.valueOf(day);
        this.month = String.valueOf(month);
        this.year = String.valueOf(year);
    }

    public String getDateFormatted(){
        String string = new StringBuilder().append(day).append(".").append(month).append(".").append(year).toString();
        return string;
    }

    public boolean isActual(){
        var ans = false;
        String now = LocalDateTime.now(ZoneId.of("ECT")).format(DateTimeFormatter.ofPattern(BotConsts.DATE_PATTERN));
        if (getDateFormatted().compareTo(now) >= 0) ans = true;
        return ans;
    }
}
