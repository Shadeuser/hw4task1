package task1;

import org.w3c.dom.ls.LSOutput;

public class Main {
    public static volatile int threadNum = 1;

    public static Object monitor = new Object();
    public static final int NUM_TIMES = 4;

    public static void main(String[] args) {

        new Thread(() -> { // Поток 1

                try {
                    for (int i = 0; i <= NUM_TIMES ; i++) {
                        synchronized (monitor) {
                            while (threadNum != 1) {
                                System.out.println("1. жду");
                                monitor.wait();
                            }
                            System.out.println("A");
                            threadNum = 2;
                            monitor.notifyAll();
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }).start();

        new Thread(() -> { //Поток 2
            try {
                for (int i = 0; i <= NUM_TIMES ; i++) {
                    synchronized (monitor) {
                        while (threadNum != 2) {
                            System.out.println("2. жду");
                            monitor.wait();
                        }
                        System.out.println("B");
                        threadNum = 3;
                        monitor.notifyAll();
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();



        new Thread(() -> { //Поток 3
            try {
                for (int i = 0; i <= NUM_TIMES ; i++) {
                    synchronized (monitor) {
                        while (threadNum != 3) {
                            System.out.println("3. жду");
                            monitor.wait();
                        }
                        System.out.println("C");
                        threadNum = 1;
                        monitor.notifyAll();
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }


}
