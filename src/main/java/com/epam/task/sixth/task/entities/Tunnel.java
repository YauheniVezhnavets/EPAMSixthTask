package com.epam.task.sixth.task.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;


public class Tunnel {
    private static final Logger LOGGER = LogManager.getLogger(Tunnel.class);


    private List<Train> trains = new ArrayList<>(MAX_NUMBER_OF_TRAINS);
    private static final int MAX_NUMBER_OF_TRAINS = 3;
    private static final ReentrantLock LOCK = new ReentrantLock();


    public Tunnel() {
    }

    void process(Train train) {

        try {

            LOCK.lock();
            if (trains.isEmpty()) {
                trains.add(train);
            }
            if (trains.size() == MAX_NUMBER_OF_TRAINS) {
                trains.clear();
            }
            if (train.getTrainDirection() == trains.get(0).getTrainDirection()) {
                trains.add(train);
            }

        } finally {
            LOCK.unlock();
        }
    }
}

