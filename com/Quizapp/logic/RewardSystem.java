package com.Quizapp.logic;
public class RewardSystem {
    private static final int[] rewards = {
        1000, 2000, 4000, 10000, 20000,
        50000, 100000, 200000, 500000, 1000000, 0
    };
    public static int getRewardForQuestion(int index) {
        // Prevent index from going out of bounds
        if (index >= 0 && index < rewards.length) {
            return rewards[index];
        }
        // Return the last reward for out-of-bounds access
        return rewards[rewards.length - 1];
    }
    public static int getSafeReward(int questionIndex) {
        if (questionIndex < 5) {
            return rewards[10];
        } else {
            return rewards[4];
        }
    }
}
