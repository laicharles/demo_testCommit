package com.example.demo.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketSell extends Thread {
    //定义一共有 50 张票，注意声明为 static,表示几个窗口共享
    private   int num = 50;


    Lock  lock  = new ReentrantLock();

    //调用父类构造方法，给线程命名
    public TicketSell(String string) {
        super(string);
    }

    @Override
    public void run() {

        //票分 50 次卖完
        for (int i = 0; i < 50; i++) {

            if (num > 0) {
                lock.lock();
                try {
                    --num;
                    sleep(10);//模拟卖票需要一定的时间
                   // System.out.println(this.currentThread().getName() + "卖出一张票，剩余" + (num) + "张");
                } catch (InterruptedException e) {
                    // 由于父类的 run()方法没有抛出任何异常，根据继承的原则，子类抛出的异常不能大于父类， 故我们这里也不能抛出异常
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
                System.out.println(this.currentThread().getName() + "卖出一张票，剩余" + (num) + "张");

            }

        }

    }

    public static void main(String[] args) {


        TicketSell  ticketSellA  = new TicketSell("A");
        TicketSell  ticketSellB = new TicketSell("B");
        TicketSell  ticketSellC = new TicketSell("C");
        TicketSell  ticketSellD = new TicketSell("D");
        TicketSell  ticketSellE = new TicketSell("E");

        ticketSellA.start();
        ticketSellB.start();
        ticketSellC.start();
        ticketSellD.start();
        ticketSellE.start();


    }
}
