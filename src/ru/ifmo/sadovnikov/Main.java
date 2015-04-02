package ru.ifmo.sadovnikov;

import ru.ifmo.sadovnikov.interfaces.Command;
import ru.ifmo.sadovnikov.storyCreation.StoryController;
import ru.ifmo.sadovnikov.storyCreation.StoryTeller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by alexkane on 3/4/15.
 */
public class Main {
    public static void main(String[] args) {
        StoryTeller new1 = new StoryTeller();
        StoryController new2 = new StoryController(new1);
    }
}
