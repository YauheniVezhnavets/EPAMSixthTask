package com.epam.task.sixth.task.entities;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class TwinTunnel {
    private static final Logger LOGGER = LogManager.getLogger(TwinTunnel.class);

    private final static AtomicReference<TwinTunnel> INSTANCE = new AtomicReference<>();
    private static final ReentrantLock twinTunnelLock = new ReentrantLock();
    private static final int NUMBER_OF_TUNNELS = 2;
    private static final Semaphore semaphore = new Semaphore(NUMBER_OF_TUNNELS);
    private static final int NEED_FOR_GENERATE_ONE = 2;

    private static final ReentrantLock tunnelLock = new ReentrantLock();
    private final List <Tunnel> tunnels = Arrays.asList(new Tunnel(),new Tunnel());


    public static TwinTunnel getInstance() {
        TwinTunnel localInstance;
        try {
            twinTunnelLock.lock();
            localInstance = INSTANCE.get();
            if (localInstance == null) {
                INSTANCE.set(new TwinTunnel());
            }
        } finally {
            twinTunnelLock.unlock();
        }
        return localInstance;
    }


    void process(Train train) {
        int numberOfTunnel = (int) (Math.random() * NEED_FOR_GENERATE_ONE);

        try {
            semaphore.acquire();
            twinTunnelLock.lock();


            if (!tunnels.isEmpty()) {
                tunnelLock.lock();
                Tunnel tunnel = tunnels.get(numberOfTunnel);
                tunnel.process(train);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            tunnelLock.unlock();
            semaphore.release();
            twinTunnelLock.unlock();
        }
    }
}

