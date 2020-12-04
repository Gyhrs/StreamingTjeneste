package com.langesokker.controllers;

import com.langesokker.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private static UserController instance;
    private final List<User> users;

    public UserController() {
        this.users = new ArrayList<>();
        //TODO: RET NAVNE
        users.add(new User("Super Dan", 34));
        users.add(new User("Claus Ildebrand", 31));
        users.add(new User("Smilende Signe", 32));
    }

    public static UserController getInstance() {
        if (instance == null) instance = new UserController();
        return instance;
    }
}
