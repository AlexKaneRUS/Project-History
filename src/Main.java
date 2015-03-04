/**
 * Created by alexkane on 3/4/15.
 */
public class Main {
    public static void main(String[] args) {
        String expositionSource = ("/home/alexkane/Desktop/Library/Mystery/Exposition/Exposition");
        String actionSource = ("/home/alexkane/Desktop/Library/Mystery/Action/Action");
        String endingSource = ("/home/alexkane/Desktop/Library/Mystery/CliffHanger/CliffHanger");
        Creator newStoryCreator = new MysteryStoryCreator(expositionSource, actionSource, endingSource);
        System.out.println(newStoryCreator.getStory(newStoryCreator));
    }
}
