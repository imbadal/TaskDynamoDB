package com.example.taskdynamodb;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeStampToTime {

    public String getDateTime(long timeStamp) {

        TimeUnit timeUnit = TimeUnit.DAYS;
        Date dateCurrent = new Date();
        Date dateOld = new Date(timeStamp);

        long diffInMillies = dateCurrent.getTime() - dateOld.getTime();
        long days = timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);

        String string;
        if (days == 1)
            string = "Submitted " + days + " day ago";
        else
            string = "Submitted " + days + " days ago";

        return string;


    }

}
