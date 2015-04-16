package ru.ifmo.sadovnikov.interfaces;

import java.io.IOException;

/**
 * Created by alexkane on 4/2/15.
 */
public interface Controller {
    public void getStory(String genre, String characterName);
    public void undo();
    public void save() throws IOException;
    public void clearTrash();
}
