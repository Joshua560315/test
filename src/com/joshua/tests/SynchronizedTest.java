package com.joshua.tests;

/**
 * Created by bmk on 18-3-20.
 */
public class SynchronizedTest {
    public  void test1()
    {
        synchronized(SynchronizedTest.class)
        {
            int i = 6;
            while( --i > 0)
            {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try
                {
                    Thread.sleep(500);
                }
                catch (InterruptedException ie)
                {
                }
            }
        }
    }

    public static synchronized void test2()
    {
            int i = 0;
            while (i++ < 5) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
    }

    public static void main(String[] args)
    {
        SynchronizedTest t1 = new SynchronizedTest();
        SynchronizedTest t2 = new SynchronizedTest();
        Thread test1 = new Thread(  new Runnable() {  public void run() {  t1.test1();  }  }, "test1"  );
        Thread test2 = new Thread(  new Runnable() {  public void run() {  test2();   }  }, "test2"  );
        test1.start();
        test2.start();
    }
}
