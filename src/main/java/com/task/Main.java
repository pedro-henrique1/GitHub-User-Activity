package com.task;

import com.task.github.JsonGitHubUser;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String PREFIX = "github-activity";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        JsonGitHubUser jsonGitHubUser = new JsonGitHubUser();

        while (true) {
            System.out.print(PREFIX + " ");
            String input = scanner.nextLine();
            String[] inputArgs = (PREFIX + " " + input).split(" ");
            if (input.equalsIgnoreCase("exit")) {
                System.out.println(PREFIX + " Saindo... Até logo!");
                break;
            }
            jsonGitHubUser.User(input);
        }

    }
}