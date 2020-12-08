package com.langesokker.models;

import com.langesokker.media.Media;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private int age;
    private List<Media> myList;

    public User(String name, int age, List<Media> myList) {
        this.name = name;
        this.age = age;
        this.myList = myList;
    }

    public User(String name, int age) {
        this(name, age, new ArrayList<>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Media> getMyList() {
        return myList;
    }

    public void setMyList(List<Media> myList) {
        this.myList = myList;
    }

    public void addMediaToList(Media media) {
        if (!myList.contains(media)) myList.add(media);
    }

    public void removeMediaFromList(Media media) {
        myList.remove(media);
    }

    public boolean isInList(Media media) {
        for (Media m : myList) {
            if (media == m) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }

}

