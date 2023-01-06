package ru.job4j.ood.srp.formatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeAdapter extends XmlAdapter<String, Calendar> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    @Override
    public Calendar unmarshal(String xml) throws Exception {
        Calendar calendar =  Calendar.getInstance();
        calendar.setTime(dateFormat.parse(xml));
        return calendar;
    }
    @Override
    public String marshal(Calendar object) {
        return dateFormat.format(object.getTime());
    }
}
