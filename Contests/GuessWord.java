package com.example.algorithms.contest;

import java.util.Scanner;

/*
Саша разрабатывает игру «Отгадай слово». В этой игре, игрок должен отгадать загаданное слово из N букв за несколько попыток.
В данный момент перед Сашей стоит задача написать логику проверки величины совпадения попытки игрока с загаданным словом.
 */

class Search{
    private String[] Q;
    private String[] S;
    private int[] answers;
    private int[] mathes;

    public Search(String s, String q){
        this.Q = q.split("");
        this.S = s.split("");
    }

    int[] getAnswers(){
        return answers;
    }

    private void check_correct(){

        for (int i = 0; i < S.length; i++){
            if (this.Q[i].equals(this.S[i])){
                this.mathes[i] = 1;
                this.answers[i] = 1;
            }else {
                this.mathes[i] = 0;
                this.answers[i] = 0;
            }
        }

    }

    private void check_present(){

        for (int i = 0; i < S.length; i++){

            if (!this.Q[i].equals(this.S[i])){

                for (int j = 0; j < S.length; j++){

                    if ((this.Q[i].equals(this.S[j]))&&(this.mathes[j] == 0)){
                        this.answers[i] = 2;
                        this.mathes[j] = 1;
                    }
                }
            }
        }

    }

    private void decodeOutpute(){

        for(int i = 0; i < this.Q.length; i++){
            switch (this.answers[i]){
                case 0:
                    System.out.println("absent");
                    break;
                case 1:
                    System.out.println("correct");
                    break;
                case 2:
                    System.out.println("present");
                    break;
                default:
                    System.out.println("error");
                    break;
            }

        }
    }

    public void calculate(){

        this.answers = new int[this.S.length];
        this.mathes = new int[this.S.length];

        check_correct();
        check_present();

        decodeOutpute();
    }
}

public class GuessWord {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s_str = scanner.next();
        String q_str = scanner.next();

        Search s = new Search(s_str, q_str);
        s.calculate();
    }
}
