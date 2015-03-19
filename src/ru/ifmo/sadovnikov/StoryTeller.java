package ru.ifmo.sadovnikov;

import java.util.Stack;

/**
 * Created by alexkane on 3/15/15.
 */
public class StoryTeller {
    static String firstStory;
    final static Creator mystery = new MysteryStoryCreator();
    final static Command createCommand = new CreateMysteryStory(mystery);
    static Stack<String> stories = new SizedStack(15);


    public static String tellMysteryStory() {
        String story = createCommand.execute();
        stories.push(story);
        return story;
    }

    public static String undo() {
        if (stories.size() == 1){
            return stories.peek();
        }
        if (stories.size() == 2){
            stories.pop();
            return stories.peek();
        }
        stories.pop();
        return stories.pop();
    }
}
