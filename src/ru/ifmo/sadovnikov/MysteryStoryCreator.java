package ru.ifmo.sadovnikov;

import ru.ifmo.sadovnikov.Creator;
import ru.ifmo.sadovnikov.PartsCreator;

/**
 * Created by alexkane on 04.03.15.
 */
class MysteryStoryCreator implements Creator {
    final String expositionSource = ("/home/alexkane/Desktop/Library/Mystery/Exposition/Exposition");
    final String actionSource = ("/home/alexkane/Desktop/Library/Mystery/Action/Action");
    final String cliffHangerSource = ("/home/alexkane/Desktop/Library/Mystery/CliffHanger/CliffHanger");
    StringBuilder story = new StringBuilder();


    public void createExposition() {
        PartsCreator expoCreator = new ThisPartCreator(expositionSource, story, 2, 4);
        expoCreator.createPart();
    }

    public void createAction() {
        PartsCreator actionCreator = new ThisPartCreator(actionSource, story, 4, 4);
        actionCreator.createPart();
    }

    public void createEnding() {
        PartsCreator cliffHangerCreator = new ThisPartCreator(cliffHangerSource, story, 3, 4);
        cliffHangerCreator.createPart();
    }

    public StringBuilder getStory() {
        createExposition();
        createAction();
        createEnding();
        return story;
    }
}
