package ru.ifmo.sadovnikov.storyCreation;

import ru.ifmo.sadovnikov.interfaces.Controller;
import ru.ifmo.sadovnikov.interfaces.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
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
    private JRadioButton genderM;
    private JRadioButton genderW;

    public View(Controller controller, StoryTeller model) {
        this.controller = controller;
        this.model = model;
    }

    public void createHomeScreen() {
        this.setTitle("Project History");
        this.setVisible(true);
        this.setSize(670, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
        this.add(homePanel);

        homePanel.add(new JLabel("Выберите жанр истории"));
        homePanel.add(Box.createVerticalStrut(10));
        String[] genresList = {"Таинственная", "Хоррор"};
        final JComboBox genres = new JComboBox(genresList);
        homePanel.add(genres);

        homePanel.add(Box.createVerticalStrut(15));
        homePanel.add(new JLabel("Выберите пол вашего героя"));
        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JRadioButton genderM = new JRadioButton("Мужской");
        genderM.setActionCommand("Мужской");
        genderM.setSelected(true);
        JRadioButton genderW = new JRadioButton("Женский");
        genderW.setActionCommand("Женский");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(genderM);
        genderGroup.add(genderW);
        genderPanel.add(genderM);
        genderPanel.add(genderW);
        genderPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        homePanel.add(genderPanel);
        homePanel.add(Box.createVerticalStrut(15));
        homePanel.add(new JLabel("Введите имя Вашего героя"));
        homePanel.add(Box.createVerticalStrut(10));
        JTextField name = new JTextField();
        homePanel.add(name);
        homePanel.add(Box.createVerticalStrut(15));

        JPanel button = new JPanel();
        button.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton storyButton = new JButton("Расскажи историю");
        storyButton.addActionListener(new TellStory(controller, model, genres.getSelectedItem().toString(), genderGroup, name));
        button.add(storyButton);
        homePanel.add(button);
        homePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }


    class TellStory implements ActionListener, Observer {
        private final Controller controller;
        private final StoryTeller model;
        private final String genre;
        private final ButtonGroup genderGroup;
        private final JTextField name;
        private JButton moreButton;
        private JButton undoButton;
        private JButton saveButton;
        private JButton selectButton;
        private JTextArea theStory;
        private JFrame storyScreen;
        private JFrame saveScreen;
        private JPanel storyPanel;
        private JScrollPane scrollPane;
        private JFileChooser saver;
        private JTextField fileNameField;
        private String gender;
        private String story = " ";

        public TellStory(final Controller controller, final StoryTeller model, final String genre, final ButtonGroup genderGroup, final JTextField name) {
            this.controller = controller;
            this.model = model;
            this.genre = genre;
            this.genderGroup = genderGroup;
            this.name = name;
            this.model.registerObserver(this);
        }

        public void createStoryFrame(String story) {
            gender = genderGroup.getSelection().getActionCommand();
            storyScreen = new JFrame("Таинственая история");
            storyScreen.setVisible(true);
            storyScreen.setSize(500, 500);

            storyScreen.getContentPane().setLayout(new BoxLayout(storyScreen.getContentPane(), BoxLayout.Y_AXIS));

            theStory = new JTextArea(story);
            theStory.setLineWrap(true);
            theStory.setEditable(false);
            theStory.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            storyPanel = new JPanel();
            storyPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            storyPanel.setLayout(new BoxLayout(storyPanel, BoxLayout.Y_AXIS));

            scrollPane = new JScrollPane(theStory);
            storyPanel.add(scrollPane);
            storyScreen.add(storyPanel);
            storyScreen.getContentPane().add(Box.createVerticalStrut(5));

            JPanel panelWithButtons = new JPanel();
            panelWithButtons.setLayout(new FlowLayout(FlowLayout.CENTER));

            undoButton = new JButton("Undo");
            panelWithButtons.add(undoButton);
            undoButton.addActionListener(this);

            moreButton = new JButton("Ещё!");
            panelWithButtons.add(moreButton);
            moreButton.addActionListener(this);

            saveButton = new JButton("Сохранить");
            panelWithButtons.add(saveButton);
            saveButton.addActionListener(this);

            panelWithButtons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            storyScreen.add(panelWithButtons);
        }

        public void actionPerformed(ActionEvent e) {
            characterName = name.getText();
            if (e.getSource() == moreButton) {
                controller.getStory(genre, gender, characterName);
            } else if (e.getSource() == undoButton) {
                controller.undo();
            } else if (e.getSource() == saveButton) {
                this.saveScreen = new JFrame("Выберите директорию для сохраниения");
                saveScreen.setVisible(true);
                saveScreen.setSize(700, 500);
                saveScreen.getContentPane().setLayout(new BoxLayout(saveScreen.getContentPane(), BoxLayout.Y_AXIS));
                JPanel savePanel = new JPanel();
                savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.X_AXIS));

                saver = new JFileChooser();
                saver.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                saver.setControlButtonsAreShown(false);
                savePanel.add(saver);
                savePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
                saveScreen.add(savePanel);

                JPanel buttonPanel = new JPanel();
                buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
                JLabel fileNameLabel = new JLabel("Название Вашей истории: ");
                fileNameField = new JTextField();
                selectButton = new JButton("Выбрать");
                selectButton.addActionListener(this);
                buttonPanel.add(fileNameLabel);
                buttonPanel.add(Box.createHorizontalStrut(5));
                buttonPanel.add(fileNameField);
                buttonPanel.add(Box.createHorizontalStrut(15));
                buttonPanel.add(selectButton);
                buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
                saveScreen.add(buttonPanel);
            } else if (e.getSource() == selectButton) {
                String fileName = fileNameField.getText();
                if (fileName == null) {
                    JOptionPane.showMessageDialog(saveScreen, "Вы не назвали историю!");
                } else if (saver.getSelectedFile() == null) {
                    JOptionPane.showMessageDialog(saveScreen, "Вы не выбрали директорию!");
                } else {
                    try {
                        saveScreen.dispatchEvent(new WindowEvent(saveScreen, WindowEvent.WINDOW_CLOSING));
                        controller.save(saver.getSelectedFile().getAbsolutePath(), fileName);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            } else {
                controller.clearTrash();
                createStoryFrame(story);
                controller.getStory(genre, gender, characterName);
            }
        }

        @Override
        public void update(String story) {
            this.story = story;
            theStory.setText(story);
        }
    }
}
