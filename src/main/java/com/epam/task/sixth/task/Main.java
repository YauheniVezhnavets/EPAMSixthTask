package com.epam.task.sixth.task;

import com.epam.task.sixth.task.entities.Train;
import com.epam.task.sixth.task.entities.Trains;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

import java.util.stream.Collectors;

public class Main {
    private static final String INPUT_FILE = "src/main/resources/trains.json";
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(INPUT_FILE);
        Trains trainsWrapper = objectMapper.readValue(file,Trains.class);

        List <Train> trains = trainsWrapper.getTrains();



        final ExecutorService executorService = Executors.newFixedThreadPool(trains.size());


        List <Future<?>> futures = trains.stream().map(executorService::submit).collect(Collectors.toList());


        executorService.shutdown();


        trains.stream().forEach(System.out::println);
    }
}