package com.Quizapp.data;
import java.util.ArrayList;
import java.util.List;

import com.Quizapp.model.Question;
public class QuestionBank {
    private List<Question> questions;
    private int currentIndex;
    public QuestionBank() {
        questions = new ArrayList<>();
        currentIndex = 0;
        loadQuestions();
    }
    private void loadQuestions() {
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"Earth", "Mars", "Venus", "Jupiter"}, 1));
        questions.add(new Question("What is the capital of France?", new String[]{"London", "Paris", "Rome", "Berlin"}, 1));
        questions.add(new Question("Which gas is most abundant in the Earth's atmosphere?", new String[]{"Oxygen", "Hydrogen", "Nitrogen", "Carbon Dioxide"}, 2));
        questions.add(new Question("Who developed the theory of relativity?", new String[]{"Newton", "Tesla", "Einstein", "Darwin"}, 2));
        questions.add(new Question("What is the boiling point of water?", new String[]{"90°C", "100°C", "80°C", "70°C"}, 1));
        questions.add(new Question("Which language is used for Android development?", new String[]{"Java", "Swift", "Python", "C#"}, 0));
        questions.add(new Question("Which organ purifies blood in the human body?", new String[]{"Heart", "Lungs", "Kidney", "Liver"}, 2));
        questions.add(new Question("What is the capital of Japan?", new String[]{"Seoul", "Beijing", "Bangkok", "Tokyo"}, 3));
        questions.add(new Question("Who is known as the father of computers?", new String[]{"Charles Babbage", "Alan Turing", "John Von Neumann", "Steve Jobs"}, 0));
        questions.add(new Question("What is the largest mammal?", new String[]{"Elephant", "Blue Whale", "Giraffe", "Polar Bear"}, 1));
    }
    public Question getNextQuestion() {
        if (currentIndex < questions.size()) {
            return questions.get(currentIndex++);
        }
        return null;
    }
    public boolean hasNext() {
        return currentIndex < questions.size();
    }
    public void reset() {
        currentIndex = 0;
    }
    // Add this method to get the total number of questions in the QuestionBank
    public int getTotalQuestions() {
        return questions.size();
    }
}
