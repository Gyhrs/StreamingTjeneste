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

    /**
     * Alt det som skal ske før test
     */
    @Before
    public void before(){
        userController = UserController.getInstance();
    }

    /**
     * Cleanup / Alt det som skal ske efter test
     */
    @After
    public void after(){
        if(testUser != null) userController.removeUser(testUser);
        userController = null;
    }

    /**
     * En test som prøver at lave en ny bruger og ændre alderen før den tilføjes
     * Tester om navnet er rigtigt
     * Tester om alderen er som forventet efter ændring
     * Tester om antallet er brugere passer efter at have tilføjet brugeren
     */
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
