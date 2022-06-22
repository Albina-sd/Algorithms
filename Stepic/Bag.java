package com.example.algorithms.SecondTopic;

import java.util.*;

public class Bag {

    /*
    Задача на программирование: непрерывный рюкзак
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Double, Integer> bag = new TreeMap<Double, Integer>(Collections.reverseOrder());

        int n, W, real_w = 0;
        double res = 0.0;

        n = scanner.nextInt();
        W = scanner.nextInt();

        for (int i = 0; i < n; i++){
            double c = scanner.nextDouble();
            int w = scanner.nextInt();

            bag.put(c/w, w);
        }

        // Получаем вид элементов
        Set set = bag.entrySet();

        // Получаем итератор
        Iterator i = set.iterator();

//        вывод списка
//        Iterator j = set.iterator();
//        while(j.hasNext()){
//            Map.Entry me = (Map.Entry)j.next();
//            System.out.println(me.getKey() + ": " + me.getValue());
//        }

        while((i.hasNext()) && (real_w <= W)) {

            Map.Entry me = (Map.Entry)i.next();

            if (real_w + (int)me.getValue() <= W){

                real_w += (int)me.getValue();
                res += (double)me.getKey() * (int)me.getValue();

            }
            else {

                res += (W - real_w) * (double)me.getKey();
                real_w += (int)me.getValue();

            }

//            System.out.println("res: "+res);
//            System.out.println("weight: " + real_w);

        }

        System.out.format("%.3f",res);

    }
}
