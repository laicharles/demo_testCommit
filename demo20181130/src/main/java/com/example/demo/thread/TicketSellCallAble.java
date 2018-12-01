package com.example.demo.thread;

import java.util.concurrent.*;

public class TicketSellCallAble {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName();
            }
        });

        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
