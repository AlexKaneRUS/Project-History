package ru.ifmo.sadovnikov.storyCreation;

import ru.ifmo.sadovnikov.interfaces.Creator;
import ru.ifmo.sadovnikov.interfaces.PartsCreator;

/**
 * Created by alexkane on 04.03.15.
 */
class MysteryStoryCreator implements Creator {
    public String getStory() {
        String story = "";
        String expositionSource = ("/home/alexkane/Desktop/Library/Mystery/Exposition/Exposition");
        PartsCreator expoCreator = new ThisPartCreator(expositionSource, story, 2, 4);
        String actionSource = ("/home/alexkane/Desktop/Library/Mystery/Action/Action");
        PartsCreator actionCreator = new ThisPartCreator(actionSource, story, 4, 4);
        String cliffHangerSource = ("/home/alexkane/Desktop/Library/Mystery/CliffHanger/CliffHanger");
        PartsCreator cliffHangerCreator = new ThisPartCreator(cliffHangerSource, story, 3, 4);
        story = expoCreator.createPart() + actionCreator.createPart() + cliffHangerCreator.createPart();
        return story;
    }
}
