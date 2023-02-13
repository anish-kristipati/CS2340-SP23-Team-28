package com.example.myapplication;

public class Player {
    private String sprite;
    private String name;
    private int points;
    private int lives;
    private  int difficulty;

    public Player(String sprite, String name, int points, int difficulty) {
        this.sprite = sprite;
        this.name = name;
        this.points = points;
        this.difficulty = difficulty;
        setLivesFromDifficulty();
    }
    public Player(){

    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLives() {
        return lives;
    }

    private void setLivesFromDifficulty() {
        switch (difficulty) {
            case 1:
                lives = 15;
                break;
            case 2:
                lives = 10;
                break;
            case 3:
                lives = 5;
                break;
            default:
                lives = 0;
                break;
        }
    }

    public void decreaseLives() {
        lives--;
    }

    public void increaseLives() {
        lives++;
    }
}
