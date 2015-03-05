/**
 * Created by alexkane on 04.03.15.
 */
class MysteryStoryCreator implements Creator<MysteryStoryCreator> {
    final String expositionSource;
    final String actionSource;
    final String cliffHangerSource;
    StringBuilder story;

    public MysteryStoryCreator(final String expositionSource, final String actionSource, final String cliffHangerSource) {
        this.expositionSource = expositionSource;
        this.actionSource = actionSource;
        this.cliffHangerSource = cliffHangerSource;
        StringBuilder story = new StringBuilder();
        this.story = story;
    }

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

    public StringBuilder getStory(Creator<MysteryStoryCreator> mysteryStoryCreator) {
        mysteryStoryCreator.createExposition();
        mysteryStoryCreator.createAction();
        mysteryStoryCreator.createEnding();
        return story;
    }
}
