package ru.ifmo.sadovnikov.storyCreation;

import ru.ifmo.sadovnikov.interfaces.Controller;
import ru.ifmo.sadovnikov.interfaces.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by alexkane on 4/2/15.
 */
public class View extends JFrame {
    private final Controller controller;
    private final StoryTeller model;
    private String genre = "";
    private String characterName = "";
    private String story = "";

    public View(Controller controller, StoryTeller model) {
        this.controller = controller;
        this.model = model;
    }

    public void createHomeScreen() {
        this.setTitle("Project History");
        this.setVisible(true);
        this.setSize(500, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
        this.add(homePanel);

        homePanel.add(new JLabel("Выберите жанр истории"));
        String[] genresList = {"Таинственная", "Хоррор"};
        final JComboBox genres = new JComboBox(genresList);
        homePanel.add(genres);
        homePanel.add(Box.createVerticalStrut(15));
        homePanel.add(new JLabel("Введите имя Вашего героя"));
        JTextField name = new JTextField();
        homePanel.add(name);
        homePanel.add(Box.createVerticalStrut(15));
        JButton storyButton = new JButton("Расскажи историю");
        storyButton.addActionListener(new TellStory(controller, model, genres.getSelectedItem().toString(), name));
        homePanel.add(storyButton);
        homePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }


    class TellStory implements ActionListener, Observer {
        private final Controller controller;
        private final StoryTeller model;
        private final String genre;
        private final JTextField name;
        private JButton moreButton;
        private JButton undoButton;
        private JButton saveButton;
        private JTextArea theStory;
        private JFrame storyScreen;
        private JPanel storyPanel;
        private JScrollPane scrollPane;

        private String story = " ";

        public TellStory(Controller controller, StoryTeller model, String genre, JTextField name) {
            this.controller = controller;
            this.model = model;
            this.genre = genre;
            this.name = name;
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

            saveButton = new JButton("Сохранить");
            storyPanel.add(saveButton);
            saveButton.addActionListener(this);

            storyScreen.add(storyPanel);
        }

        public void actionPerformed(ActionEvent e) {
            characterName = name.getText();
            if (e.getSource() == moreButton) {
                controller.getStory(genre, characterName);
            } else if (e.getSource() == undoButton) {
                controller.undo();
            }
            else if(e.getSource() == saveButton){
                try {
                    controller.save();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                controller.clearTrash();
                createStoryFrame(story);
                controller.getStory(genre, characterName);
            }
        }

        @Override
        public void update(String story) {
            this.story = story;
            theStory.setText(story);
        }
    }

}
