package ru.ifmo.sadovnikov.storyCreation;

import ru.ifmo.sadovnikov.interfaces.Command;
import ru.ifmo.sadovnikov.interfaces.Creator;

/**
 * Created by alexkane on 3/15/15.
 */
class CreateStory implements Command {
    private Creator storyCreator;
    private String genre;
    private String gender;
    private String characterName;
    private String story = null;

    public CreateStory(Creator storyCreator, String genre, String gender, String characterName) {
        this.storyCreator = storyCreator;
        this.genre = genre;
        this.gender = gender;
        this.characterName = characterName;
    }

    public void execute() {
        story = storyCreator.getStory(genre, gender, characterName);
    }

    public String returnStory() {
        return story;
    }
}
