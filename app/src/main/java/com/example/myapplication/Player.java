package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Player implements Parcelable {
    private String sprite;
    private String name;
    private int points;
    private int lives;
    private int difficulty;

    public Player(String sprite, String name, int points, int difficulty) {
        this.sprite = sprite;
        this.name = name;
        this.points = points;
        this.difficulty = difficulty;
        setLivesFromDifficulty();
    }

    public Player() {

    }

    protected Player(Parcel in) {
        sprite = in.readString();
        name = in.readString();
        points = in.readInt();
        lives = in.readInt();
        difficulty = in.readInt();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

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

    public boolean nameValid(String name) {
        return !(name == null || name.equals("") || name.trim().length() == 0);
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

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        setLivesFromDifficulty();
    }

    public int getDifficulty() {
        return this.difficulty;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(sprite);
        parcel.writeString(name);
        parcel.writeInt(points);
        parcel.writeInt(lives);
        parcel.writeInt(difficulty);
    }
}
