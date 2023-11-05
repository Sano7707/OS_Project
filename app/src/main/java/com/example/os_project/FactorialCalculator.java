package com.example.os_project;

import java.math.BigInteger;

public class FactorialCalculator {

    static {
        System.loadLibrary("native-lib");
    }

    public native double performComplexComputationWithThreadsC(int n, int numThreads);
    public Double performComplexComputationWithThreads(int n, int numThreads) {
        if (n < 0) {
            throw new IllegalArgumentException("Complex computation is not defined for negative numbers");
        }

        // Divide the computation among multiple threads
        Double[] partialResults = new Double[numThreads];
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            final int finalI = i;
            threads[i] = new Thread(() -> {
                partialResults[finalI] = calculatePartialComputation(n);
            });
            threads[i].start();
        }

        // Wait for all threads to finish
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Combine partial results to get the final result
        Double result = 0.0;
        for (Double partialResult : partialResults) {
            result += partialResult;
        }

        return result;
    }

    private static Double calculatePartialComputation(int n) {
        Double partialResult = 0.0;

        for (int i = 0; i <= n; i ++) {
            partialResult += Math.exp(Math.sin(i) * Math.cos(i));
        }

        return partialResult;
    }

}

