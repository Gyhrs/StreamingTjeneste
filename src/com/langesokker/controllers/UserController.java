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

    public UserController() {
        this.users = new ArrayList<>();
        //TODO: RET NAVNE
        users.add(new User("Super Dan", 34));
        users.add(new User("Claus Ildebrand", 31));
        users.add(new User("Smilende Signe", 32));
        this.currentUser = users.get(0);
        this.guiController = GUIController.getInstance();
    }

    public static UserController getInstance() {
        if (instance == null) instance = new UserController();
        return instance;
    }

    public void addMediaToUser(User user, Media media) {
        user.addMediaToList(media);
    }

    public void removeMediaFromUser(User user, Media media){
        user.removeMediaFromList(media);
    }

    public User[] getUserArray(){
        return users.toArray(new User[]{});
    }

    public void switchUser(User user){
        if(this.currentUser.equals(user)) return;
        this.currentUser = user;
        guiController.resetFrontPage();
        guiController.setView(guiController.getFrontPage().getContainer());
    }

    public User getCurrentUser(){
        return currentUser;
    }
}
