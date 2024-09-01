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
import io.github.cdimascio.dotenv.Dotenv;


public class JsonGitHubUser {
    static Dotenv dotenv = Dotenv.load();

    private final Issues insurre = new Issues();
    private static String url = "https://api.github.com/users/joaolucassilva/events";

    public void User() throws IOException {
        JsonAPi();
        push();
        insurre.issueId();
        insurre.star();
        insurre.pullRequest();

    }


    public static JSONArray JsonAPi() throws IOException {
        HttpURLConnection httpConnection = (HttpURLConnection) new URL(url).openConnection();
        httpConnection.setRequestMethod("GET");

        httpConnection.setRequestProperty("Accept", "application/json");
        httpConnection.setRequestProperty("Authorization","Bearer"+  dotenv.get("TOKEN_GITHUB"));
        BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
        JSONArray jsonArray = new JSONArray(in.readLine());

        in.close();
        return jsonArray;
    }


    public void push() throws IOException {
        JSONArray jsonArray = JsonAPi();
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

