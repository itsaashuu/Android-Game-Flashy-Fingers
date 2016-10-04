package com.example.abc.flashyfingers;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ABC on 01-Oct-16.
 */
public class WordGenerator {

    ArrayList<String> wordList = new ArrayList<String>();
    public ArrayList getList() {
        for (int i = 0; i < 4; i++) {
            char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            Random slength = new Random();
            int t = slength.nextInt(6)+1;
            for (int j = 0; j < t; j++) {
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }
            String output = sb.toString();
            wordList.add(output);
        }
        return wordList;
    }

}
