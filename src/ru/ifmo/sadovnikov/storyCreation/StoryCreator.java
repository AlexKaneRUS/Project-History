package ru.ifmo.sadovnikov.storyCreation;

import ru.ifmo.sadovnikov.interfaces.Creator;
import ru.ifmo.sadovnikov.interfaces.PartsCreator;

import java.util.ArrayList;

/**
 * Created by alexkane on 04.03.15.
 */
class StoryCreator implements Creator {
    private final String rootDirectory = "/home/alexkane/Desktop/Library";

    public String getStory(String genre, String gender, String characterName) {
        String story = "";
        PartsCreator actionCreator;
        PartsCreator cliffHangerCreator;
        PartsCreator expoCreator;
        String cliffHangerSource = "";
        String actionSource = "";
        String expositionSource = "";
        if (genre.equals("Таинственная")) {
            expositionSource = (rootDirectory + "/Mystery/Exposition/Exposition");
            expoCreator = new ThisPartCreator(expositionSource, story, 2, 4);
            actionSource = (rootDirectory + "/Mystery/Action/Action");
            actionCreator = new ThisPartCreator(actionSource, story, 4, 4);
            cliffHangerSource = (rootDirectory + "/Mystery/CliffHanger/CliffHanger");
            cliffHangerCreator = new ThisPartCreator(cliffHangerSource, story, 3, 4);
        } else if (genre.equals("Хоррор")) {
            expositionSource = (rootDirectory + "/Horror/Exposition/Exposition");
            expoCreator = new ThisPartCreator(expositionSource, story, 3, 4);
            actionSource = (rootDirectory + "/Horror/Action/Action");
            actionCreator = new ThisPartCreator(actionSource, story, 3, 4);
            cliffHangerSource = (rootDirectory + "/Horror/CliffHanger/CliffHanger");
            cliffHangerCreator = new ThisPartCreator(cliffHangerSource, story, 2, 4);
        } else if (genre.equals("Детективная")) {
            expositionSource = (rootDirectory + "/Detective/Exposition/Exposition");
            expoCreator = new ThisPartCreator(expositionSource, story, 2, 4);
            actionSource = (rootDirectory + "/Detective/Action/Action");
            actionCreator = new ThisPartCreator(actionSource, story, 3, 4);
            cliffHangerSource = (rootDirectory + "/Detective/CliffHanger/CliffHanger");
            cliffHangerCreator = new ThisPartCreator(cliffHangerSource, story, 2, 4);
        } else if (genre.equals("Смешная")) {
            expositionSource = (rootDirectory + "/Comedy/Exposition/Exposition");
            expoCreator = new ThisPartCreator(expositionSource, story, 3, 4);
            actionSource = (rootDirectory + "/Comedy/Action/Action");
            actionCreator = new ThisPartCreator(actionSource, story, 3, 4);
            cliffHangerSource = (rootDirectory + "/Comedy/CliffHanger/CliffHanger");
            cliffHangerCreator = new ThisPartCreator(cliffHangerSource, story, 2, 4);
        } else {
            expositionSource = (rootDirectory + "/Cool/Exposition/Exposition");
            expoCreator = new ThisPartCreator(expositionSource, story, 1, 6);
            actionSource = (rootDirectory + "/Cool/Action/Action");
            actionCreator = new ThisPartCreator(actionSource, story, 4, 4);
            cliffHangerSource = (rootDirectory + "/Cool/CliffHanger/CliffHanger");
            cliffHangerCreator = new ThisPartCreator(cliffHangerSource, story, 1, 6);
        }
        Russianiser new1 = new Russianiser(characterName);
        new1.Russianise();
        ArrayList<String> name = new1.getRussianisedName();
        story = expoCreator.createPart() + actionCreator.createPart() + cliffHangerCreator.createPart();
        if (gender.equals("Мужской")) {
            story = story.replaceAll("i[А-Яа-яё]*", "");
        } else {
            story = story.replaceAll("[А-Яа-яё]*i", "");
        }
        story = story.replaceAll("Им", name.get(0)).replaceAll("Род", name.get(1)).replaceAll("Дат", name.get(2)).replaceAll("Вин", name.get(3)).replaceAll("Твор", name.get(4)).replaceAll("Предл", name.get(5));
        return story;
    }
}