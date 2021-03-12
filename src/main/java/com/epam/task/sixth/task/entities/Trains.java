package com.epam.task.sixth.task.entities;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Trains {
    private static final Logger LOGGER = LogManager.getLogger(Trains.class);

    private List <Train> trains;


    public Trains () {
    }

    public List<Train> getTrains() {
        return trains;
    }
}
