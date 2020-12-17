package com.langesokker.controllers;

import com.langesokker.media.Media;
import com.langesokker.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private static UserController instance;
    private final List<User> users;
    private User currentUser;
    private final GUIController guiController;

    /**
     * Kontrukt&oslash;r af UserController
     * Her tilf&oslash;jes der tre standard brugere
     */
    public UserController() {
        this.users = new ArrayList<>();
        this.addUser(new User("Dan Witzner Hansen", 34));
        this.addUser(new User("Claus Brabrand", 31));
        this.addUser(new User("Signe Kongsgaard", 32));
        this.currentUser = users.get(0);
        this.guiController = GUIController.getInstance();
    }

    /**
     * Laver en singleton af UserControlleren hvis den ikke findes i forvejen
     * @return = UserController singletong
     */
    public static UserController getInstance() {
        if (instance == null) instance = new UserController();
        return instance;
    }

    /**
     * Tilf&oslash;jer et medie til en brugers liste
     * @param user = Brugeren der skal tilf&oslash;je mediet
     * @param media = objekt af mediet
     */
    public void addMediaToUser(User user, Media media) {
        user.addMediaToList(media);
    }

    /**
     * Fjerner et medie til en brugers liste
     * @param user = Brugeren der skal fjerne mediet
     * @param media = objekt af mediet
     */
    public void removeMediaFromUser(User user, Media media){
        user.removeMediaFromList(media);
    }

    /**
     * Konvertere User ArrayListen til en Array så den nemt kan bruges i en JComboBox
     * @return User[] med indholdet fra users arraylisten
     */
    public User[] getUserArray(){
        return users.toArray(new User[]{});
    }

    /**
     * Skifter den nuværrene bruger til en anden bruger og genindlæser forsiden med det nye brugerdata
     * @param user = Den nye bruger
     */
    public void switchUser(User user){
        if(this.currentUser.equals(user)) return;
        this.currentUser = user;
        guiController.resetFrontPage();
        guiController.setView(guiController.getFrontPage());
    }

    /**
     * User
     * @return = Den nuværrene bruger
     */
    public User getCurrentUser(){
        return currentUser;
    }

    /**
     * Tilf&oslash;jer en bruger til ArrayListen
     * @param user = Den nye bruger
     */
    public void addUser(User user){
        users.add(user);
    }

    /**
     * Fjerner en bruger til ArrayListen
     * @param user = En bruger fra ArrayListen
     */
    public void removeUser(User user){
        users.remove(user);
    }
}
