package com.langesokker.models;

import com.langesokker.media.Media;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private int age;
    private final List<Media> myList;

    /**
     * Kontrukt&oslash;r af User
     * @param name = Navnet p&aring; brugeren
     * @param age = Alderen p&aring; brugeren (Kan v&aelig;re brugbart hvis der skal sortes efter b&oslash;rnevenligt i fremtiden)
     * @param myList = Start listen for brugeren (Kan v&aelig;re brugbart hvis brugerdataen i fremtiden skulle gemmes)
     */
    public User(String name, int age, List<Media> myList) {
        this.name = name;
        this.age = age;
        this.myList = myList;
    }

    /**
     * Overloadet kontrukt&oslash;r af User
     * @param name = Navnet p&aring; brugeren
     * @param age = Alderen p&aring; brugeren (Kan v&aelig;re brugbart hvis der skal sortes efter b&oslash;rnevenligt i fremtiden)
     */
    public User(String name, int age) {
        this(name, age, new ArrayList<>());
    }

    /**
     * Getter metode til at få navnet på brugeren
     * @return navnet på brugeren
     */
    public String getName() {
        return name;
    }

    /**
     * Getter metode til at få alderen på brugeren
     * @return alder på brugeren
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter metode til at sætte alderen for brugeren
     * @param age den nye alder
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Getter metode til at få brugerens liste
     * @return brugerens liste
     */
    public List<Media> getMyList() {
        return myList;
    }

    /**
     * Tilføjer et medie til listen
     * @param media = Mediet der skal tilføjes
     */
    public void addMediaToList(Media media) {
        if (!myList.contains(media)) myList.add(media);
    }

    /**
     * Fjerner et medie til listen
     * @param media = Mediet der skal fjernes
     */
    public void removeMediaFromList(Media media) {
        myList.remove(media);
    }

    /**
     * Boolean metode til at finde ud af om et medie er i listen
     * @param media = Mediet der skal kigges efter
     * @return boolean om mediet er i listen
     */
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

