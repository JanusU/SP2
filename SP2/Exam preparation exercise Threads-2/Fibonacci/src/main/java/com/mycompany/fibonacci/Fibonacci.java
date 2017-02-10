/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fibonacci;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Janus
 */
public class Fibonacci extends Thread {

    public ArrayBlockingQueue fibray = new ArrayBlockingQueue(8);
    public ArrayBlockingQueue fibray2 = new ArrayBlockingQueue(8);

    private long fibo(long n) {
        if ((n == 0) || (n == 1)) {
            return n;
        } else {
            return fibo(n - 1) + fibo(n - 2);
        }
    }

    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        fib.fibray.add(4);
        fib.fibray.add(5);
        fib.fibray.add(8);
        fib.fibray.add(12);
        fib.fibray.add(21);
        fib.fibray.add(22);
        fib.fibray.add(34);
        fib.fibray.add(35);
        Runnable r1 = () -> {
            int calc;
            while (fib.fibray.peek() != null) {
                try {
                    calc = (int) fib.fibray.poll();
                    fib.fibray2.put(fib.fibo(calc));
                } catch (InterruptedException ex) {

                }
            }

        };
        Runnable r2 = () -> {
            long temp;
            long sum = 0;
            for (int i = 0; i <= 7; i++) {
                try {
                    temp = (long) fib.fibray2.take();
                    System.out.println(temp);
                    sum += temp;
                } catch (InterruptedException ex) {

                }
            }
            System.out.println("The sum is: " + sum);
        };
        
        new Thread(r1).start();
        new Thread(r1).start();
        new Thread(r1).start();
        new Thread(r1).start();
        new Thread(r2).start();
    }
}
