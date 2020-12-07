package com.langesokker.views;

import com.langesokker.components.containers.MediaItemContainer;
import com.langesokker.components.containers.NavBarContainer;
import com.langesokker.controllers.UserController;
import com.langesokker.media.Media;
import com.langesokker.utils.Colors;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class MyListView extends BaseView {

    private final UserController userController = UserController.getInstance();

    public MyListView(JFrame frame) {
        super(frame);
    }

    public JFrame getFrame() {
        return super.getFrame();
    }

    public Container getContainer() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        int item = 0;
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(Colors.SECONDARY_DARK.getColor());
        Container rowContainer = new Container();
        rowContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
            for (Media media : userController.getCurrentUser().getMyList()) {
                if (item % 5 == 0) {
                    panel.add(rowContainer);
                    rowContainer = new Container();
                    rowContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

                }
                rowContainer.add(new MediaItemContainer(media).getContainer());
                item++;
            }
        mainPanel.add(new NavBarContainer(new Container()).getContainer(), BorderLayout.NORTH);
        panel.add(rowContainer);

        JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        mainPanel.add(scrollPane);

        return mainPanel;
    }
}
