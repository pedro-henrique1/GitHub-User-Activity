package com.task.github;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;

import io.github.cdimascio.dotenv.Dotenv;

public class Issues {
    Dotenv dotenv = Dotenv.load();
    private static String url = "https://api.github.com/users/kamranahmedse/starred";
    public void issueId() throws IOException {
        JSONArray jsonArray = JsonGitHubUser.JsonAPi();
        JSONObject json = jsonArray.getJSONObject(0);
        Object type = json.get("type");
        if (type.equals("IssuesEvent")) {
            JSONObject repo = json.getJSONObject("repo");
            String url = (String) repo.get("url");
            int startIndex = url.lastIndexOf("/repos/") + "/repos/".length();
            String repoPath = url.substring(startIndex);
            System.out.println("Opened a new issue in " + repoPath);
        }
    }

    public void star() throws IOException {
//        URL gitHub = new URL("https://api.github.com/users/" + user + "/events");
        HttpURLConnection httpConnection = (HttpURLConnection) new URL(url).openConnection();
        httpConnection.setRequestMethod("GET");

        httpConnection.setRequestProperty("Accept", "application/json");
        httpConnection.setRequestProperty("Authorization", "Bearer" + dotenv.get("TOKEN_GITHUB"));
        BufferedReader in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
        JSONArray jsonArray = new JSONArray(in.readLine());
        JSONObject json = jsonArray.getJSONObject(0);
        Object url = json.get("full_name");
        System.out.println("Starred " + url);
    }


    public void pullRequest() throws IOException {
        JSONArray jsonArray = JsonGitHubUser.JsonAPi();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            Object type = json.get("type");
            if (type.equals("PullRequestEvent")) {
                JSONObject repo = json.getJSONObject("repo");
                String url = (String) repo.get("url");
                int startIndex = url.lastIndexOf("/repos/") + "/repos/".length();
                String repoPath = url.substring(startIndex);
                JSONObject payload = json.getJSONObject("payload");
                String action = payload.getString("action");
                System.out.println("Last Pull request em " + repoPath + " status of " + action);
                break;
            }
        }
    }
}


