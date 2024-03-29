package com.atguigu;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有"+lockA+" \t 尝试获得"+ lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (Exception e){

            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有lockB \t 尝试获得lockA");
            }
        }
    }
}

/**
 * 死锁是两个或者两个以上的进程在执行过程中，
 * 因争夺资源而造成的一种相互等待的现象
 * 若无外力干涉他们都将无法推进下去
 *jps -l 查看线程
 * jstack 线程id  查看死锁
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA,lockB),"ThreadAAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"ThreadBBB").start();
    }
}
