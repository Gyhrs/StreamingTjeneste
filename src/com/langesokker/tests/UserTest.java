package com.langesokker.tests;

import com.langesokker.controllers.MediaController;
import com.langesokker.controllers.UserController;
import com.langesokker.media.Media;
import com.langesokker.media.SupportedMediaTypes;
import com.langesokker.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserTest {

    private UserController userController;
    private User testUser;

    @Before
    public void before(){
        userController = UserController.getInstance();
    }

    @After
    public void after(){
        if(testUser != null) userController.removeUser(testUser);
        userController = null;
    }

    @Test
    public void addUser(){
        testUser = new User("Ruben", 38);
        assertEquals(testUser.getName(), "Ruben");
        testUser.setAge(testUser.getAge() + 1);
        assertEquals(testUser.getAge(), 39);
        int userSizeBefore = userController.getUserArray().length;
        userController.addUser(testUser);
        int userSizeAfter = userController.getUserArray().length;
        assertEquals((userSizeBefore + 1), userSizeAfter);
    }

}
