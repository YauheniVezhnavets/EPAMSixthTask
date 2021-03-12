package com.epam.task.sixth.task.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Train implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(Train.class);

    private int id;
    private TrainDirection trainDirection;


    public Train (){
    }

    public Train(int id, TrainDirection trainDirection) {
        this.id = id;
        this.trainDirection = trainDirection;
    }


    public int getId() {
        return id;
    }

    public TrainDirection getTrainDirection() {
        return trainDirection;
    }


    @Override
    public void run() {
        TwinTunnel twinTunnel = TwinTunnel.getInstance();
        twinTunnel.process(this);
        try {
            //train go through the tunnel
            TimeUnit.MILLISECONDS.sleep(5_000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Train{" + "id=" + id + ", trainDirection=" + trainDirection + '}';
    }
}
