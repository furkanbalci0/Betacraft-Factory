package org.betacraft.factory.utils.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormattedTime {

    //VARIABLES
    private String seconds = " seconds ";
    private String minutes = " minutes ";
    private String hours = " hours ";
    private String days = " days ";

    private long input;

    //CONSTURUCTOR
    public FormattedTime(long second) {
        this.input = second;
    }

    //FUNCTIONS

    /***
     * @param seconds This is seconds splitting name.
     * @return FormattedTime.
     */
    public FormattedTime setSeconds(String seconds) {
        this.seconds = seconds;
        return this;
    }

    /***
     * @param minutes This is minutes splitting name.
     * @return FormattedTime.
     */
    public FormattedTime setMinutes(String minutes) {
        this.minutes = minutes;
        return this;
    }

    /***
     * @param hours This is hours splitting name.
     * @return FormattedTime.
     */
    public FormattedTime setHours(String hours) {
        this.hours = hours;
        return this;
    }

    /***
     * @param days This is days splitting name.
     * @return FormattedTime.
     */
    public FormattedTime setDays(String days) {
        this.days = days;
        return this;
    }

    /***
     * @param input Just integer or long input.
     * @return FormattedTime.
     */
    public FormattedTime setInput(long input) {
        this.input = input;
        return this;
    }

    /**
     * @return Formatted time but string aspect.
     */
    public String get() {

        if (input >= 60 && input <= 3600) {

            int min = (int) Math.floor(input / 60.0);

            int sec = Math.floorMod((int) input, 60);

            return sec > 0 ? min + minutes + sec + seconds : min + minutes;

        } else if (input >= 3601 && input <= 86399) {

            int hour = (int) Math.floor(input / 3600.0);

            int min = (int) Math.floor((input % 3600.0) / 60);

            return min > 0 ? hour + hours + min + minutes : hour + hours;

        } else if (input >= 86400) {

            int day = (int) Math.floor(input / 86400.0);

            int hour = (int) Math.floor((input % 86400.0) / 3600);

            return hour > 0 ? day + days + hour + hours : day + days;
        }

        return Math.round(input) + seconds;

    }


    //STATIC FUNCTIONS

    /**
     * @param format For example: dd/MM/yyyy HH:mm:ss
     * @param date This is time. For example: new Date()
     * @return Formatted time.
     */
    public static String formattedTime(String format, Date date) {
        return new SimpleDateFormat(format).format(date);
    }

}
