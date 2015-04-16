package ru.ifmo.sadovnikov.storyCreation;

import ru.ifmo.sadovnikov.interfaces.Controller;

import java.io.IOException;

/**
 * Created by alexkane on 4/2/15.
 */
public class StoryController implements Controller {
    private StoryTeller model;

    public StoryController(StoryTeller model) {
        this.model = model;
        View storyInterface = new View(this, model);
        storyInterface.createHomeScreen();
    }

    @Override
    public void getStory(String genre, String characterName) {
        model.execute(genre, characterName);
    }

    @Override
    public void undo() {
        model.undo();
    }

    @Override
    public void save() throws IOException { model.save(); }

    @Override
    public void clearTrash() {
        model.clearTrash();
    }

}
