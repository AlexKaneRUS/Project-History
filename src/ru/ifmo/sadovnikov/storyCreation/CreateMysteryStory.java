package ru.ifmo.sadovnikov.storyCreation;

import ru.ifmo.sadovnikov.interfaces.Command;
import ru.ifmo.sadovnikov.interfaces.Creator;

/**
 * Created by alexkane on 3/15/15.
 */
class CreateMysteryStory implements Command {
    public Creator mysteryStoryCreator;

    public CreateMysteryStory(Creator mysteryStoryCreator) {
        this.mysteryStoryCreator = mysteryStoryCreator;
    }

    public String execute() {
        return mysteryStoryCreator.getStory();
    }

    @Override
    public String undo() {
        return null;
    }
}
