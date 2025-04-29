package com.Quizapp.model;
public class Player {
    private String name;
    private String city;
    private String state;
    private int currentReward;
    private boolean[] lifelinesUsed;
    public Player(String name, String city, String state) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.currentReward = 0;
        this.lifelinesUsed = new boolean[3];
    }
    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }
    public String getState() {
        return state;
    }
    public int getCurrentReward() {
        return currentReward;
    }
    public void setCurrentReward(int reward) {
        this.currentReward = reward;
    }
    public boolean isLifelineUsed(int index) {
        return lifelinesUsed[index];
    }
    public void useLifeline(int index) {
        lifelinesUsed[index] = true;
    }
    public void resetLifelines() {
        for (int i = 0; i < lifelinesUsed.length; i++) {
            lifelinesUsed[i] = false;
        }
    }
}
