package com.Quizapp.logic;
import java.util.Random;

import com.Quizapp.model.Question;
public class LifelineManager {
    public static String[] applyFiftyFifty(Question question) {
        String[] options = question.getOptions();
        int correctIndex = question.getCorrectAnswerIndex();
        String[] reduced = new String[2];
        reduced[0] = options[correctIndex];
        Random rand = new Random();
        int secondIndex;
        do {
            secondIndex = rand.nextInt(4);
        } while (secondIndex == correctIndex);
        reduced[1] = options[secondIndex];
        return reduced;
    }
    public static int applyAudiencePoll(Question question) {
        return question.getCorrectAnswerIndex();
    }
    public static boolean shouldSkip() {
        return true;
    }
}
