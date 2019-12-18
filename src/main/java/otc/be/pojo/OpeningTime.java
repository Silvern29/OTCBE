package otc.be.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class OpeningTime {
    private DayOfWeek dayOfWeek;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime opening;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime closing;

    public OpeningTime(DayOfWeek dayOfWeek, LocalTime opening, LocalTime closing) {
        this.dayOfWeek = dayOfWeek;
        this.opening = opening;
        this.closing = closing;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getOpening() {
        return opening;
    }

    public void setOpening(LocalTime opening) {
        this.opening = opening;
    }

    public LocalTime getClosing() {
        return closing;
    }

    public void setClosing(LocalTime closing) {
        this.closing = closing;
    }
}
