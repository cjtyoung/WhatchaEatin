package com.colinyoung.whatchaeatin.data;

public class User {

    private String email;
    private String name;
    private int targetCalories;
    private int targetProtein;
    private int targetFat;
    private int targetCarbs;
    private int targetWater;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(int targetCalories) {
        this.targetCalories = targetCalories;
    }

    public int getTargetProtein() {
        return targetProtein;
    }

    public void setTargetProtein(int targetProtein) {
        this.targetProtein = targetProtein;
    }

    public int getTargetFat() {
        return targetFat;
    }

    public void setTargetFat(int targetFat) {
        this.targetFat = targetFat;
    }

    public int getTargetCarbs() {
        return targetCarbs;
    }

    public void setTargetCarbs(int targetCarbs) {
        this.targetCarbs = targetCarbs;
    }

    public int getTargetWater() {
        return targetWater;
    }

    public void setTargetWater(int targetWater) {
        this.targetWater = targetWater;
    }
}
