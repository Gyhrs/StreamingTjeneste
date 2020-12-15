package com.langesokker.views;

import com.langesokker.components.ErrorPopup;
import com.langesokker.components.containers.MediaItemContainer;
import com.langesokker.components.containers.NavBarContainer;
import com.langesokker.controllers.UserController;
import com.langesokker.media.Media;
import com.langesokker.utils.Colors;

import javax.swing.*;
import java.awt.*;

public class MyListView extends BaseView {

    private final UserController userController = UserController.getInstance();

    public MyListView(JFrame frame) {
        super(frame);
    }

    @Override
    public String getViewName() {
        return "My list";
    }

    /**
     * Fills a panel with MediaItemView of the medias in User's list
     * @return a scrollable panel with all media in User's List
     */
    public Container getContainer() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(Colors.SECONDARY_DARK.getColor());
        Container rowContainer = new Container();
        rowContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        int item = 0;
        // Fills the MyList page with data from User's list
            for (Media media : userController.getCurrentUser().getMyList()) {
                if (item % 5 == 0) {
                    panel.add(rowContainer);
                    rowContainer = new Container();
                    rowContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

                }
                rowContainer.add(new MediaItemContainer(media, this));
                item++;
            }
        mainPanel.add(new NavBarContainer(new Container()), BorderLayout.NORTH);
        panel.add(rowContainer);
        if(item == 0){
            new ErrorPopup(frame, "Empty list", "You have not added anything to your list yet", false);
            
        }
        JScrollPane scrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);

        mainPanel.add(scrollPane);

        return mainPanel;
    }
}
