package com.example.demo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class TestThreadAOrder {

    public static List<String> OutPutByOrder(List<String> StringList, final int threadSize) {

        //ExecutorService executorService = Executors.newFixedThreadPool(threadSize);
/*

        new ThreadPoolExecutor(threadSize, threadSize, 1000, TimeUnit.DAYS, new LinkedBlockingQueue<Runnable>());
*/
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(threadSize, threadSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(threadSize));
        List<Future<List<String>>> futures = new ArrayList<Future<List<String>>>(threadSize);


        int size = StringList.size();
        List<String> resultA = new ArrayList<>(size);
        for (int i = 0; i < threadSize; i++) {

            final List<String> task = StringList.subList(size / threadSize * i, size / threadSize * (i + 1));


            Callable<List<String>> listCallable = new Callable<List<String>>() {
                @Override
                public List<String> call() throws Exception {

                    List<String> list = new ArrayList<String>(task.size());

                    for (String str : task
                    ) {
                        list.add(str);
                    }

                    return list;
                }


            };
            futures.add(executorService.submit(listCallable));


        }

        for (Future<List<String>> future : futures
        ) {

            try {
                resultA.addAll(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();

        int resultSize = resultA.size();

        System.out.println("resultSize------------>" + resultSize);

        for (String rsul : resultA
        ) {
            System.out.println("result----------------->" + rsul);
        }

        return resultA;


    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(String.valueOf(i));
        }
        OutPutByOrder(list, 10);
    }

}
