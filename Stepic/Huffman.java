package com.example.algorithms.SecondTopic;

import java.util.*;

/*
Кодирование Хаффмана. Построение оптимального беспрефиксного кода.
 */

class Vertex {
    public String letter;
    public Integer frequency;

    public Vertex(String letter, Integer frequency){
        this.letter = letter;
        this.frequency = frequency;
    }

    public String toString() {
        return this.letter + " " + this.frequency + " ";
    }
}

public class Huffman {

    private static Map<String, Integer> elements;

    static String ExtractMin(){

        Integer min = 10000;
        String res = "";

        for(Map.Entry entry: elements.entrySet()){
            Integer freq = (Integer) entry.getValue();

            if (freq < min){
                min = freq;
                res = (String) entry.getKey();
            }
        }

        return res;
    }

    static Map<String, String> get_letters(){
        Map<String, String> res = new HashMap<>();

        for(Map.Entry entry: elements.entrySet()){
            res.put((String)entry.getKey(),null);
        }

        return res;
    }

    static void PrintList(Map<String, Integer> elem){
        Set set = elem.entrySet();
        Iterator iter = set.iterator();

        while(iter.hasNext()){
            Map.Entry me = (Map.Entry)iter.next();
            System.out.println(me.getKey() + ": " + me.getValue());
        }

        System.out.println();
    }

    static void Print(Map<String, String> elem){
        Set set = elem.entrySet();
        Iterator iter = set.iterator();

        while(iter.hasNext()){
            Map.Entry me = (Map.Entry)iter.next();
            System.out.println(me.getKey() + ": " + me.getValue());
        }
    }

     static Map<String,Integer> alphabet(String mass[]){
        // посчитать повторения элементов
        for (String el : mass){
            Integer frequency = elements.get(el);
            elements.put(el, frequency == null ? 1 : frequency + 1);
        }

        return elements;
    }

    static Map<Vertex, String> BuildingTree(int n, Map<Vertex, String> t){

        for (int k = 0; k < n; k++){
            String i = "", j = "", k_vert = "";
            int fi = 0, fj = 0, fk = 0;
            Vertex v;

            i = ExtractMin();
            if (elements.get(i) != null) {
                fi = elements.get(i).intValue();
                fk = fi;
            }else break;

            if (i.length() < n) {
                v = new Vertex(i, fi);
                t.put(v, "1");
                //System.out.format("%s %d %d \n",i,fi,1);
            }
            elements.remove(i);

            if (!elements.isEmpty()) {
                j = ExtractMin();
                fj = elements.get(j);
                fk += fj;
                //System.out.format("%s %d %d \n", j, fj, 0);

                v = new Vertex(j, fj);
                t.put(v, "0");
                elements.remove(j);

                k_vert = i + j;
                //t.put(new Vertex(k_vert, fk),null);
                elements.put(k_vert, fk);
            }

//            System.out.println("Map: " + t);
//            System.out.println("elements: "+elements+"\n");
        }

        return t;
    }

    static Map<String, String> CodeFromTree(Map<String, String> res,  Map<Vertex,String> t){

        for(Map.Entry ltr: res.entrySet()) {
            String code = "";
            String l = (String) ltr.getKey();
            //System.out.print("\n"+l+": ");

            for(Map.Entry entry: t.entrySet()) {
                Vertex v = (Vertex) entry.getKey();

                int size = res.size()-1;

                //if ((v.letter.contains(l)) && (code.length()<size)){
                if (v.letter.contains(l)){
                    code = entry.getValue() + code;
                    //System.out.print(v.letter+"("+entry.getValue()+") ");
                }

            }
            res.put(l,code);
        }

        return res;
    }

    static String encoding (String mass[], Map<String, String> code){
        String result = "";

        for (int i = 0; i < mass.length; i++){
            result += code.get(mass[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //String s = scanner.next();
        String s = "abacabad";
        String mass[] = s.split("");

        Map<Vertex,String> t = new LinkedHashMap<>();
        elements = new HashMap<>();
        elements = alphabet(mass);

        Map<String, String> code_letters = get_letters();
        int n = code_letters.size();

        //PrintList(elements);

        if (code_letters.size() > 1) {
            t = BuildingTree(n, t);
            //System.out.println("\nTree: " + t + "\n");
            code_letters = CodeFromTree(code_letters, t);
        }else {
            code_letters.put(mass[0],"0");
        }

        String result = encoding(mass,code_letters);
        System.out.println(n + " " + result.length());
        Print(code_letters);
        System.out.println(result);
    }
    //abababbaacdc
    //abacabad
    //beepaboopabeerc
    //loremipsumdolorsitametconsecteturadipisicingelitdelectuseligendiillumisteomnisvoluptatumaspernaturatqueautautemculpadoloremeveniet
}
