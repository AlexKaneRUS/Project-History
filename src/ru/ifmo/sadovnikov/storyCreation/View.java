package ru.ifmo.sadovnikov.storyCreation;

import ru.ifmo.sadovnikov.interfaces.Controller;
import ru.ifmo.sadovnikov.interfaces.Observer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alexkane on 4/2/15.
 */
public class View {
    private final Controller controller;
    private final StoryTeller model;
    private String story = "";

    public View(Controller controller, StoryTeller model) {
        this.controller = controller;
        this.model = model;
    }

    public void createHomeScreen() {
        JFrame homeScreen = new JFrame("Project History");
        homeScreen.setVisible(true);
        homeScreen.setSize(500, 200);
        homeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel homePanel = new JPanel();
        homeScreen.add(homePanel);
        JButton storyButton = new JButton("Расскажи историю");
        homePanel.add(storyButton);
        storyButton.addActionListener(new TellStory(controller, model));
    }


    class TellStory implements ActionListener, Observer {
        private final Controller controller;
        private final StoryTeller model;
        private JButton moreButton;
        private JButton undoButton;
        private JTextArea theStory;
        private JFrame storyScreen;
        private JPanel storyPanel;
        private JScrollPane scrollPane;

        private String story = " ";

        public TellStory(Controller controller, StoryTeller model) {
            this.controller = controller;
            this.model = model;
            this.model.registerObserver(this);
        }

        public void createStoryFrame(String story) {
            this.storyScreen = new JFrame("Таинственая история");
            storyScreen.setVisible(true);
            storyScreen.setSize(500, 500);

            this.theStory = new JTextArea(story);
            theStory.setLineWrap(true);
            theStory.setEditable(false);
            theStory.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

            this.storyPanel = new JPanel();
            storyPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            storyPanel.setLayout(new BoxLayout(storyPanel, BoxLayout.Y_AXIS));

            this.scrollPane = new JScrollPane(theStory);
            storyPanel.add(scrollPane);

            undoButton = new JButton("Undo");
            storyPanel.add(undoButton);
            undoButton.addActionListener(this);

            moreButton = new JButton("Ещё!");
            storyPanel.add(moreButton);
            moreButton.addActionListener(this);

            storyScreen.add(storyPanel);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == moreButton) {
                controller.getStory();
            } else if (e.getSource() == undoButton) {
                controller.undo();
            } else {
                controller.clearTrash();
                createStoryFrame(story);
                controller.getStory();
            }
        }

        @Override
        public void update(String story) {
            this.story = story;
            theStory.setText(story);
        }
    }

}
