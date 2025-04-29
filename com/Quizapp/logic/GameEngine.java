package com.Quizapp.logic;
import com.Quizapp.data.QuestionBank;
import com.Quizapp.model.Player;
import com.Quizapp.model.Question;
public class GameEngine {
    private Player player;
    private QuestionBank questionBank;
    private int currentQuestionIndex;
    public GameEngine(Player player) {
        this.player = player;
        this.questionBank = new QuestionBank();
        this.currentQuestionIndex = 0;
    }
    public boolean hasNextQuestion() {
        return questionBank.hasNext();
    }
    public Question getNextQuestion() {
        // Fetch the next question first
        Question nextQuestion = questionBank.getNextQuestion();
        // Then increment the current question index
        currentQuestionIndex++;
        return nextQuestion;
    }
    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }
    public int getCurrentReward() {
        return RewardSystem.getRewardForQuestion(currentQuestionIndex - 1);
    }
    public void updateReward() {
        player.setCurrentReward(getCurrentReward());
    }
    public int getSafeReward() {
        return RewardSystem.getSafeReward(currentQuestionIndex - 1);
    }
    public Player getPlayer() {
        return player;
    }
//    public void resetGame() {
//        currentQuestionIndex = 0;
//        player.setCurrentReward(0);
//        questionBank.reset();
//        player.resetLifelines();
//    }
    public int getNextRewardAmount() {
        int index = currentQuestionIndex - 1;
        return RewardSystem.getRewardForQuestion(index);
    }
    public int getCurrentRewardAmount() {
        int index = currentQuestionIndex - 2;
        return RewardSystem.getRewardForQuestion(index);
    }
    // New method to calculate the total reward
    public int calculateTotalReward() {
        int totalReward = 0;
        // Loop through all remaining questions starting from the current index
        for (int i = 0; i < 10; i++) {
            totalReward += RewardSystem.getRewardForQuestion(i);
        }
        return totalReward;
    }
}
