package ru.ifmo.sadovnikov.storyCreation;

import ru.ifmo.sadovnikov.interfaces.Observer;
import ru.ifmo.sadovnikov.utilityClasses.SizedStack;
import ru.ifmo.sadovnikov.interfaces.Command;
import ru.ifmo.sadovnikov.interfaces.Creator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by alexkane on 3/15/15.
 */
public class StoryTeller implements Command {
    private final Creator thisCreator = new StoryCreator();
    private String thisStory = "";
    private int numberOfStory = 1;
    private final CreateStory createCommand = new CreateStory(thisCreator);
    private Stack<String> stories = new SizedStack(15);
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public void execute(String genre, String characterName) {
        createCommand.execute(genre, characterName);
        String story = createCommand.returnStory();
        thisStory = story;
        stories.push(story);
        notifyObservers(story);
    }

    public void undo() {
        if (stories.size() == 1) {
            String story = stories.peek();
            notifyObservers(story);
        }
        else {
            stories.pop();
            String story = stories.peek();
            notifyObservers(story);
        }
    }

    public void save() throws IOException {
        BufferedWriter saver = new BufferedWriter(new FileWriter("/home/alexkane/Desktop/Stories/ИсторияНомер" + numberOfStory));
        saver.write(thisStory);
        saver.close();
        numberOfStory++;
    }

    public void clearTrash() {
        stories.clear();
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        int i = observers.indexOf(observer);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    public void notifyObservers(String story) {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(story);
        }
    }
}
