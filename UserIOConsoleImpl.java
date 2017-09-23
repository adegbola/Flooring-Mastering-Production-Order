/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.ui;

import java.util.Scanner;

/**
 *
 * @author Kelsey
 */
public class UserIOConsoleImpl implements UserIO{
  
    
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        String input = sc.nextLine();
        double readdouble = Double.parseDouble(input);

        return readdouble;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        String input = sc.nextLine();
        double readdouble = Double.parseDouble(input);
        while (readdouble < min && readdouble > max) {
            System.out.println(prompt);
            input = sc.nextLine();
            readdouble = Integer.parseInt(input);
        }

        return readdouble;
    }

    @Override
    public float readFloat(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        String input = sc.nextLine();
        float readfloat = Float.parseFloat(input);
        return readfloat;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        String input = sc.nextLine();
        float readfloat = Float.parseFloat(input);
        while (readfloat < min && readfloat > max) {
            System.out.println(prompt);
            input = sc.nextLine();
            readfloat = Integer.parseInt(input);
        }
        return readfloat;
    }

    @Override
    public int readInt(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        String input = sc.nextLine();
        int readint = Integer.parseInt(input);
        return readint;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        String input = sc.nextLine();
        int readint = Integer.parseInt(input);
        while (readint < min && readint > max) {
            System.out.println(prompt);

            input = sc.nextLine();
            readint = Integer.parseInt(input);
        }
        return readint;
    }

    @Override
    public long readLong(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        String input = sc.nextLine();
        long readlong = Long.parseLong(input);
        return readlong;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        String input = sc.nextLine();
        long readlong = Long.parseLong(input);
        while (readlong < min && readlong > max) {
            System.out.println(prompt);
            input = sc.nextLine();
            readlong = Integer.parseInt(input);
        }
        return readlong;
    }

    @Override
    public String readString(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.println(prompt);
        String input = sc.nextLine();

        return input;
    }
}
