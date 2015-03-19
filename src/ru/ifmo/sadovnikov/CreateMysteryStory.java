package ru.ifmo.sadovnikov;

import java.util.Stack;

/**
 * Created by alexkane on 3/15/15.
 */
class CreateMysteryStory implements Command {
    public Creator mysteryStoryCreator;

    public CreateMysteryStory(Creator mysteryStoryCreator) {
        this.mysteryStoryCreator = mysteryStoryCreator;
    }

    public String execute() {
        return mysteryStoryCreator.getStory().toString();
    }
}
