package com.example.algorithms.contest;

import java.util.ArrayList;
import java.util.Scanner;

public class calculator {


    public static String calc (String str){
        String res = "";
        StringBuilder sb = new StringBuilder();

        if (str.contains("*")){
            sb.append(mult_calc(str));

            if (str.contains("+")){
                res += String.valueOf(summ(sb.toString()));
            }else {
                res += sb.toString();
            }

        }else {
            res += String.valueOf(summ(str));
            return res;
        }

        return res;
    }

    public static int multiplication (String str){
        int res = 1;
        String mass [] = str.split("\\*");

        for (int i = 0; i < mass.length; i++){
            //System.out.println(mass[i]);
            res *= Integer.parseInt(mass[i]);
        }

        return res;
    }

    public static String mult_calc (String str){
        int res = 0;
        StringBuilder sb = new StringBuilder(str);
        StringBuilder sb2 = new StringBuilder();
        int k = str.indexOf("+");
        int n = str.indexOf("*");

        //System.out.format("k = {%d}, n = {%d}\n",k,n);
        // добавляем слагаемые до умножения в итоговую строку
        if (k != -1 && k < n){
            String nach = str.substring(0,n);
            n = nach.lastIndexOf("+");
            sb2.append(str.substring(0, n+1));
            sb.replace(0, n+1, "");
            //System.out.println("before mult: " + sb2.toString());
        }

        do{
            k = sb.indexOf("+");

            if (k == -1){

                sb2.append(multiplication(sb.toString()));
                sb.replace(0, sb.length(),"");

            }else {

                sb2.append(multiplication(sb.substring(0, k)) + "+");
                sb.replace(0, k+1, "");

                if (sb.indexOf("*") == -1){
                    sb2.append(sb);
                    sb.replace(0, sb.length(),"");
                }
            }

        }while (sb.length() != 0); // ???

        //System.out.println("res mult: " + sb2.toString());
        return sb2.toString();
    }

    public static String brackets (String str){
        int n;
        StringBuilder sb = new StringBuilder(str);

        do {
            n = sb.indexOf("(");
            int k = sb.indexOf(")");

            if (n > -1){
                //System.out.println(sb.substring(n+1, k));
                String res1 = calc(sb.substring(n+1, k));
                //System.out.println("res backets: "+ res1);
                sb.replace(n,k+1, res1);
                //System.out.println("After brackets:"+ sb);
            }

        }while (n != -1);

        return sb.toString();
    }

    public static int summ (String str){
        int res = 0;
        String mass [] = str.split("\\+");

        for (int i = 0; i < mass.length; i++){
            //System.out.println("summ[i]: " + mass[i]);
            res += Integer.parseInt(mass[i]);
        }

        return res;
    }

    public static int Result (String str){
        int res;
        int n = 0;

        // считаем выражение в скобках и вставляем в строку
        String str_b = brackets(str);

        // перемножаем все что не в скобках
        String str_m = calc(str_b);
        //System.out.println("calc after brackets: "+str_m);

        res = summ(str_m);

        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1+2*3+(10+2*3)
        
        String str = scanner.next();
        int res = Result(str);
        System.out.println(res);

    }
}
