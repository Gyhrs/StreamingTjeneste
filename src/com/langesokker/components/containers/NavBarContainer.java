package com.langesokker.components.containers;

import com.langesokker.controllers.GUIController;
import com.langesokker.controllers.UserController;
import com.langesokker.models.User;
import com.langesokker.utils.Colors;
import com.langesokker.views.MyListView;

import javax.swing.*;
import java.awt.*;

public class NavBarContainer extends JPanel{

    private final UserController userController = UserController.getInstance();
    private final GUIController guiController = GUIController.getInstance();


    /**
     * Konstruk&oslash;r til NavBarContainer
     * @param centerNav = omr&aring;det i midten af navbaren. For eksempel s&oslash;ge felt
     */
    public NavBarContainer(Container centerNav) {
        this.setLayout(new BorderLayout());
        this.setBackground(Colors.PRIMARY_DARK.getColor());

        Container leftContainer = new Container();
        leftContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        Container rightContainer = new Container();
        rightContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));


        leftContainer.add(createHomeButton());
        leftContainer.add(createMyListButton());
        rightContainer.add(createUserBox());

        this.add(leftContainer, BorderLayout.WEST);
        if(centerNav != null) { this.add(centerNav, BorderLayout.CENTER); }
        this.add(rightContainer, BorderLayout.EAST);
    }

    /**
     * Hj&aelig;lpe metode til at f&aring; genereret en knap
     * @return JButton
     */
    private JButton createMyListButton() {
        JButton myListButton = new JButton("My list");
        myListButton.addActionListener(e -> {
            MyListView myListView = new MyListView(guiController.getFrame());
            guiController.setView(myListView);
        });
        return myListButton;
    }

    /**
     * Hj&aelig;lpe metode til at f&aring; genereret en background
     * @return Container
     */
    private JComboBox<User> createUserBox() {
        JComboBox<User> userBox = new JComboBox<>(UserController.getInstance().getUserArray());
        userBox.setSelectedItem(userController.getCurrentUser());
        userBox.addItemListener(e -> {
            User user = (User) e.getItem();
            userController.switchUser(user);
        });
        return userBox;
    }

    /**
     * Hj&aelig;lpe metode til at f&aring; genereret en hjem knap
     * @return JButton
     */
    private JButton createHomeButton() {
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(e -> {
            guiController.getFrontPage().resetSearch();
            guiController.setView(guiController.getFrontPage());
        });
        return homeButton;
    }
}
