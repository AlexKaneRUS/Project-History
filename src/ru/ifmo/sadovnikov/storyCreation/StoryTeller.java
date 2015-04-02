package ru.ifmo.sadovnikov.storyCreation;

import ru.ifmo.sadovnikov.interfaces.Observer;
import ru.ifmo.sadovnikov.utilityClasses.SizedStack;
import ru.ifmo.sadovnikov.interfaces.Command;
import ru.ifmo.sadovnikov.interfaces.Creator;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by alexkane on 3/15/15.
 */
public class StoryTeller implements Command {
    private final Creator mystery = new MysteryStoryCreator();
    private final Command createCommand = new CreateMysteryStory(mystery);
    private Stack<String> stories = new SizedStack(15);
    private ArrayList<Observer> observers = new ArrayList<Observer>();

    public String execute() {
        String story = createCommand.execute();
        stories.push(story);
        notifyObservers(story);
        return story;
    }

    public String undo() {
        if (stories.size() == 1) {
            String story = stories.peek();
            notifyObservers(story);
            return story;
        }
        stories.pop();
        String story = stories.peek();
        notifyObservers(story);
        return story;
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
