package com.example.algorithms.SecondTopic;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
Декодирование Хаффмана. Восстановление строки по её коду и беспрефиксному коду символов.
 */

public class Huffman2 {

    private static String decoding(Map<String, String> code, String mass[]){
        String res = "";
        String element = "";

        for (int i = 0; i < mass.length; i++){
            boolean flag = true;

            if (flag) {
                element += mass[i];
            }
            //System.out.println(element);

            for(Map.Entry entry: code.entrySet()){
                String val = (String) entry.getValue();


                if (val.equals(element)){
                    res += (String) entry.getKey();
                    element = "";
                    flag = false;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int k = scanner.nextInt();
        int l = scanner.nextInt();

        Map<String, String> code_letters = new LinkedHashMap<>();

        String regex = "^[a-z][\\W+\\s]";
        Pattern pattern = Pattern.compile(regex);

        for (int i = 0; i < k; i++){
            String str = scanner.next();
            //String str = "a: 011";
            String key = str.substring(0,1);
            String val = scanner.next();
            code_letters.put(key,val);
        }
        //System.out.println(code_letters);
        String s = scanner.next();
        String mass [] = s.split("");

        String result = decoding(code_letters, mass);
        System.out.println(result);
    }
}
