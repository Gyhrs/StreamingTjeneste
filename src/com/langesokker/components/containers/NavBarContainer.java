package com.langesokker.components.containers;

import com.langesokker.controllers.UserController;
import com.langesokker.models.User;
import com.langesokker.utils.Colors;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class NavBarContainer{

    private final UserController userController = UserController.getInstance();

    private final Container centerNav;

    public NavBarContainer(Container centerNav) {
        this.centerNav = centerNav;
    }

    public Container getContainer(){
        JPanel navContainer = new JPanel();
        navContainer.setLayout(new BorderLayout());
        navContainer.setBackground(Colors.PRIMARY_DARK.getColor());
        Container leftContainer = new Container();
        Container rightContainer = new Container();
        leftContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton homeButton = new JButton("Home");
        leftContainer.add(homeButton);
        JComboBox<User> userBox = new JComboBox<>(UserController.getInstance().getUserArray());
        userBox.setSelectedItem(userController.getCurrentUser());
        userBox.addItemListener(e -> {
            User user = (User) e.getItem();
            userController.switchUser(user);
        });
        rightContainer.add(userBox);

        navContainer.add(leftContainer, BorderLayout.WEST);

        if(centerNav != null){
            navContainer.add(centerNav, BorderLayout.CENTER);
        }

        leftContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rightContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        navContainer.add(rightContainer, BorderLayout.EAST);

        return navContainer;
    }

}
