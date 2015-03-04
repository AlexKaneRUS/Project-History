/**
 * Created by alexkane on 3/4/15.
 */
public interface Creator<T> {
    public void createExposition();
    public void createAction();
    public void createEnding();
    public StringBuilder getStory(Creator<T> storyCreator);
}
