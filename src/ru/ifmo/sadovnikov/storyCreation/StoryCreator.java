package ru.ifmo.sadovnikov.storyCreation;

import ru.ifmo.sadovnikov.interfaces.Creator;
import ru.ifmo.sadovnikov.interfaces.PartsCreator;

import java.util.ArrayList;

/**
 * Created by alexkane on 04.03.15.
 */
class StoryCreator implements Creator {
    public String getStory(String genre, String gender, String characterName) {
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
        } else {
            expositionSource = ("/home/alexkane/Desktop/Library/Horror/Exposition/Exposition");
            expoCreator = new ThisPartCreator(expositionSource, story, 2, 4);
            actionSource = ("/home/alexkane/Desktop/Library/Horror/Action/Action");
            actionCreator = new ThisPartCreator(actionSource, story, 4, 4);
            cliffHangerSource = ("/home/alexkane/Desktop/Library/Horror/CliffHanger/CliffHanger");
            cliffHangerCreator = new ThisPartCreator(cliffHangerSource, story, 3, 4);
        }
        Russianiser new1 = new Russianiser(characterName);
        new1.Russianise();
        ArrayList<String> name = new1.getRussianisedName();
        story = expoCreator.createPart() + actionCreator.createPart() + cliffHangerCreator.createPart();
        if (gender.equals("Мужской")) {
            story = story.replaceAll("i[А-Яа-яё]*", "").replaceAll("j", "");
        } else {
            story = story.replaceAll("[А-Яа-яё]*i", "").replaceAll("l", "");
        }
        story = story.replaceAll("Им", name.get(0)).replaceAll("Род", name.get(1)).replaceAll("Дат", name.get(2)).replaceAll("Вин", name.get(3)).replaceAll("Твор", name.get(4)).replaceAll("Предл", name.get(5));
        return story;
    }
}