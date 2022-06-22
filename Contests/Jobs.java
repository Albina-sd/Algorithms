package com.example.algorithms.contest;

import java.util.*;

class Candidates{
    public int tasks;
    public String vacancy;
    public int penalty;

    public Candidates(String v, int t, int p){
        this.tasks = t;
        this.vacancy = v;
        this.penalty = p;
    }

    public String toString() {
        return " " + this.vacancy + " " + this.tasks + " " + this.penalty;
    }
}

public class Jobs {

    static String max_tasks(LinkedHashMap<String, Candidates> emp, String job){
        String result = "";
        int max_v = -10;
        int min_p = 100000000;

        Set<String> keys = emp.keySet();

        for (String key : keys) {
            Candidates c = emp.get(key);

            if (c.vacancy.equals(job)){
                if (max_v < c.tasks){
                    result = key;
                    max_v = c.tasks;
                    min_p = c.penalty;
                }

                if ((max_v == c.tasks)&&(c.penalty < min_p)){
                    result = key;
                    max_v = c.tasks;
                    min_p = c.penalty;
                }
            }
            //System.out.println(key + " -- " + emp.get(key));
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        TreeMap <String, Integer> job = new TreeMap<>();

        for(int i = 0; i < n; i++){
            String v = scanner.next();
            int t = v.indexOf(",");
            int c = Integer.parseInt(v.substring(t+1));
            job.put(v.substring(0,t), c);
        }

        int k = scanner.nextInt();
        LinkedHashMap<String ,Candidates> employee = new LinkedHashMap<>();

        for(int i = 0; i < k; i++){
            String e = scanner.next();

            String[] m = e.split(",");
            employee.put(m[0], new Candidates(m[1],Integer.parseInt(m[2]),Integer.parseInt(m[3])));
        }

        //System.out.println(employee);

        Set keys = job.keySet();
        ArrayList<String> winners = new ArrayList<>();

        for (Iterator i = keys.iterator(); i.hasNext();) {
            String key = (String) i.next();
            Integer value = job.get(key);

            int count = 0;

            while (count < value){
                String e = max_tasks(employee,key);
                winners.add(e);
                employee.remove(e);
                count++;
            }

        }

        Collections.sort(winners);

        for(String s : winners) {
            System.out.println(s);
        }

    }

}
