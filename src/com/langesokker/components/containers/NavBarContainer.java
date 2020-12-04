package com.langesokker.components.containers;

import javax.swing.*;
import java.awt.*;

public class NavBarContainer{

    private Container centerNav;

    public NavBarContainer(Container centerNav) {
        this.centerNav = centerNav;
    }

    public Container getContainer(){
        JPanel navContainer = new JPanel();
        navContainer.setLayout(new BorderLayout());
        navContainer.setBackground(new Color(31, 31, 31));
        Container leftContainer = new Container();
        leftContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton homeButton = new JButton("Home");
        leftContainer.add(homeButton);

        navContainer.add(leftContainer, BorderLayout.WEST);

        if(centerNav != null){
            navContainer.add(centerNav, BorderLayout.CENTER);
        }

        Container rightContainer = new Container();
        leftContainer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        navContainer.add(rightContainer, BorderLayout.EAST);
        return navContainer;
    }

}
