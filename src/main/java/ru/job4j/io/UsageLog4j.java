package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        double weight = 59.9;
        float height = 1.71f;
        int floor = 4;
        short house = 42;
        byte flat = 12;
        char section = 'a';
        long distance = 32000;
        boolean isTrue = true;
        LOG.debug("Anton: weight - {} kg, height - {} cm. He lives on {} floor.", weight, height, floor);
        LOG.debug("Adress: Salnas str. {}/{} - {}. Distance from Moscow = {} km", house, section, flat, distance);
        LOG.debug("Info = {}", isTrue);
    }
}
