package ru.ifmo.sadovnikov;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alexkane on 3/4/15.
 */
public class Main {
    public static void main(String[] args) {
        JFrame homeScreen = new JFrame("Project History");
        homeScreen.setVisible(true);
        homeScreen.setSize(500, 200);
        homeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel homePanel = new JPanel();
        homeScreen.add(homePanel);
        JButton storyButton = new JButton("Расскажи историю");
        homePanel.add(storyButton);
        storyButton.addActionListener(new tellStory());
    }

    static class tellStory implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame storyScreen = new JFrame("Таинственая история");
            storyScreen.setVisible(true);
            storyScreen.setSize(500,500);
            String story = StoryTeller.tellMysteryStory();

            JTextArea theStory = new JTextArea(story);
            theStory.setLineWrap(true);
            theStory.setEditable(false);
            theStory.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

            JPanel storyPanel = new JPanel();
            storyPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            storyPanel.setLayout(new BoxLayout(storyPanel, BoxLayout.Y_AXIS));

            JScrollPane scrollPane = new JScrollPane(theStory);
            storyPanel.add(scrollPane);

            JButton undoButton = new JButton("Undo");
            storyPanel.add(undoButton);
            undoButton.addActionListener(new Undo(theStory));

            JButton moreButton = new JButton("Ещё!");
            storyPanel.add(moreButton);
            moreButton.addActionListener(new AnotherStory(theStory));

            storyScreen.add(storyPanel);
        }
    }

    static class Undo implements ActionListener {
        public JTextArea textField;

        public Undo(JTextArea textField) {
            this.textField = textField;
        }

        public void actionPerformed(ActionEvent e) {
            String story = StoryTeller.undo();
            textField.setText(story);
        }
    }

    static class AnotherStory implements ActionListener {
        public JTextArea textField;

        public AnotherStory(JTextArea textField) {
            this.textField = textField;
        }

        public void actionPerformed(ActionEvent e) {
            String story = StoryTeller.tellMysteryStory();
            textField.setText(story);
        }
    }
}
