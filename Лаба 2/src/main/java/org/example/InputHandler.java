package org.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
@Scope("singleton")
public class InputHandler {
    private Scanner scanner = new Scanner(System.in);

    public String readString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (Exception e) {
                System.out.println("Error: Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    public void readLine() {
        scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}