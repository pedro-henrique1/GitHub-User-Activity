package com.task.github;

import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import io.github.cdimascio.dotenv.Dotenv;


public class JsonGitHubUser {
    static Dotenv dotenv = Dotenv.load();

    private final Issues insurre = new Issues();

    public void User(String name) throws IOException {
        JSONArray events = JsonAPi(name);
        push(events);
        insurre.issueId(events);
        insurre.star(name);
        insurre.pullRequest(events);
    }


    public static JSONArray JsonAPi(String input) throws IOException {
        String url = "https://api.github.com/users/" + input + "/events";
        HttpURLConnection httpConnection = (HttpURLConnection) new URL(url).openConnection();
        httpConnection.setRequestMethod("GET");
        httpConnection.setRequestProperty("Accept", "application/json");
        httpConnection.setRequestProperty("Authorization", "Bearer" + dotenv.get("TOKEN_GITHUB"));

        BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
        JSONArray jsonArray = new JSONArray(in.readLine());

        in.close();

        if (jsonArray.isEmpty()){
            System.out.println("No events found");
        }
        return jsonArray;
    }


    public void push(JSONArray jsonArray) {
        Map<String, Integer> repoCommits = new HashMap<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            Object type = json.get("type");
            if (type.equals("PushEvent")) {
                JSONObject payload = json.getJSONObject("payload");
                int commitCount = payload.getJSONArray("commits").length();
                commitCount++;
                JSONObject repo = json.getJSONObject("repo");
                String repoName = repo.getString("name");
                repoCommits.put(repoName, repoCommits.getOrDefault(repoName, 0) + commitCount);
            }
        }
        Optional<Map.Entry<String, Integer>> lastEntry = repoCommits.entrySet().stream().reduce((first, second) -> second);
        lastEntry.ifPresent(entry -> System.out.println("Pushed " + entry.getValue() + " commits to " + entry.getKey()));
    }

}

