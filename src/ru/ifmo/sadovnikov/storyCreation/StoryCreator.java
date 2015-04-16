package ru.ifmo.sadovnikov.storyCreation;

import ru.ifmo.sadovnikov.interfaces.Creator;
import ru.ifmo.sadovnikov.interfaces.PartsCreator;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by alexkane on 04.03.15.
 */
class StoryCreator implements Creator {
    public String getStory(String genre, String characterName) {
        String story = "";
        PartsCreator actionCreator;
        PartsCreator cliffHangerCreator;
        PartsCreator expoCreator;
        String cliffHangerSource = "";
        String actionSource = "";
        String expositionSource = "";
        if (genre.equals("Таинственная")) {
            expositionSource = ("/home/alexkane/Desktop/Library/Mystery/Exposition/Exposition");
            expoCreator = new ThisPartCreator(expositionSource, story, 2, 4);
            actionSource = ("/home/alexkane/Desktop/Library/Mystery/Action/Action");
            actionCreator = new ThisPartCreator(actionSource, story, 4, 4);
            cliffHangerSource = ("/home/alexkane/Desktop/Library/Mystery/CliffHanger/CliffHanger");
            cliffHangerCreator = new ThisPartCreator(cliffHangerSource, story, 3, 4);
        }else{
            expositionSource = ("/home/alexkane/Desktop/Library/Horror/Exposition/Exposition");
            expoCreator = new ThisPartCreator(expositionSource, story, 2, 4);
            actionSource = ("/home/alexkane/Desktop/Library/Horror/Action/Action");
            actionCreator = new ThisPartCreator(actionSource, story, 4, 4);
            cliffHangerSource = ("/home/alexkane/Desktop/Library/Horror/CliffHanger/CliffHanger");
            cliffHangerCreator = new ThisPartCreator(cliffHangerSource, story, 3, 4);
        }
        story = expoCreator.createPart() + actionCreator.createPart() + cliffHangerCreator.createPart();
        story = story.replaceAll("Герой", characterName);
        Random randomNumber = new Random();
        if (randomNumber.nextInt(10) < 3){
            story = story.replaceAll("\\s+[Оо]н\\s+", " " + characterName + " ");
        }
        return story;
    }
}