package com.example.algorithms.firstTopic;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
нахождение чисел Фибоначчи с использовнием Hash Tables
сохраняем все числа, сокращаем количество вычислений
требуется BigInteger для чисел больше 1000
использует слишком много памяти
 */

public class Fibbonachi {
    Map<Integer, BigInteger> cache = new HashMap<>();
    int counter;

    private BigInteger fibb(int n){
        counter++;

        if (n < 2){
            return BigInteger.valueOf(n);
        }

        if (cache.containsKey(n)){
            return cache.get(n);
        } else {

            for (int i = 2; i <= n; i++) {
                BigInteger res = fibb(i - 1).add(fibb(i - 2));
                cache.put(i, res);
            }

            return cache.get(n);
        }
    }

    private void run(int n){
        System.out.println(n + ": " + fibb(n));
        System.out.println("количество вызовов: " + counter);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        long startTime = System.currentTimeMillis();

        new Fibbonachi().run(n);

        long endTime = System.currentTimeMillis();

        System.out.println("time: " + (endTime - startTime) + " ms\n");
    }
}
