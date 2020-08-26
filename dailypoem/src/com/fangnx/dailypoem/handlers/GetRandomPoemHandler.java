package com.fangnx.dailypoem.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.List;
import java.util.Optional;
import java.io.IOException;

import com.alibaba.fastjson.*;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

public class GetRandomPoemHandler implements RequestHandler {
    private static final String apiUrl = "https://poetrydb.org/random/1";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("GetRandomPoemIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        OkHttpClient client = new OkHttpClient();
        String speechText = "";
        try {
            Request request = new Request.Builder().url(apiUrl).build();
            com.squareup.okhttp.Response response = client.newCall(request).execute();
            String json = response.body().string();

            speechText = this.buildPoemOutput(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input.getResponseBuilder().withSpeech(speechText).withSimpleCard("HelloWorld", speechText).build();
    }

    private String buildPoemOutput(String poemJson) {
        JSONArray jsonArr = JSON.parseArray(poemJson);
        StringBuilder output = new StringBuilder();

        for (Object poem : jsonArr) {
            JSONObject poemObject = (JSONObject) poem;
            String title = poemObject.getString("title");
            String author = poemObject.getString("author");
            JSONArray linesArr = poemObject.getJSONArray("lines");
            List<String> lines = JSONObject.parseArray(linesArr.toJSONString(), String.class);
            String content = String.join(" \n\n", lines).replaceAll("[_-]", "");

            output.append(title + ", ");
            output.append(" by " + author + ". \n\n");
            output.append(content);
        }
        return output.toString();
    }
}
