package com.example.demo.thread;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketSellRunnable implements Callable<List<String>> {

    //定义一共有 50 张票，继承机制开启线程，资源是共享的，所以不用加 static
    private int num = 50;
    //创建一个锁对象
    Lock l = new ReentrantLock();

    public static void main(String[] args) {
        TicketSellRunnable runnable = new TicketSellRunnable();

        ExecutorService service = Executors.newFixedThreadPool(5);

        Future<List<String>> result = service.submit(runnable);

        service.shutdown();


    }

    @Override
    public List<String> call() throws Exception {

        List<String>   bodyEntities =null;

        //票分 50 次卖完
            for (int i = 0; i < 50; i++) {
                //获取锁
              l.lock();
                try {
                    if (num > 0) {
                        //模拟卖一次票所需时间
                        Thread.sleep(10);
                        --num;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //释放锁
                    l.unlock();
                }
                System.out.println(Thread.currentThread().getName() + "卖出一张票，剩余" + (num) + "张");

        }
            return bodyEntities;
    }
}