package haut.exam.First.one;

import java.util.Scanner;

/**
 * @author loop
 * @version 1.0
 *
 * 白皮书第一套题的第一道编程题
 *
 */
public class Sportor {
    int num;
    double Score;
    String name, country;

    public void setNum(int num) {
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public void inputScore(Sportor[] sportors){
        Scanner sc = new Scanner(System.in);
        for (Sportor sportor : sportors) {
            sportor.Score = sc.nextDouble();
        }
    }
    public void bestScore(Sportor[] sportors){
        double max = sportors[0].Score;
        for (Sportor s : sportors){
            if (s.Score > max) max = s.Score;
        }
        System.out.println("最大成绩是" + max);
    }
}
