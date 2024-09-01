package com.task.github;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonGitHubUser {


    public void User() throws IOException {
        JsonAPi();

    }


    public void JsonAPi() throws IOException {
//        URL gitHub = new URL("https://api.github.com/users/" + user + "/events");
        URL gitHub = new URL("https://api.github.com/users/joaolucassilva/events");
        URLConnection yc = gitHub.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        JSONArray jsonArray = new JSONArray(in.readLine());
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
        Map.Entry<String, Integer> lastEntry = null;
        for (Map.Entry<String, Integer> entry : repoCommits.entrySet()) {
            lastEntry = entry;
        }
        assert lastEntry != null;
        System.out.println("Pushed " + lastEntry.getValue() + " to " + lastEntry.getKey());


    }

}

