package ru.ifmo.sadovnikov.storyCreation;

import ru.ifmo.sadovnikov.interfaces.Command;
import ru.ifmo.sadovnikov.interfaces.Creator;

/**
 * Created by alexkane on 3/15/15.
 */
class CreateStory implements Command {
    private Creator storyCreator;
    private String story = null;

    public CreateStory(final Creator storyCreator) {
        this.storyCreator = storyCreator;
    }

    public void execute(String genre, String characterName) {
        story = storyCreator.getStory(genre, characterName);
    }

    @Override
    public void undo() {

    }

    public String returnStory() {
        return story;
    }
}
