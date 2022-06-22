package com.example.algorithms.SecondTopic;

import java.util.ArrayList;
import java.util.Scanner;

public class DifferentTerms {

    /*
    Задача на программирование: различные слагаемые числа
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int i = 1, c = n, sum = 0;

        ArrayList <Integer> M = new ArrayList<>();

        while (sum < n){

            if ((c - i) > i){
                M.add(i);
                sum += i;
            } else {
                M.add(c);
                sum += c;
            }

            c -= i;
            i++;
        }

        System.out.println(M.size());

        for (int element : M){
            System.out.print(element + " ");
        }
    }
}
