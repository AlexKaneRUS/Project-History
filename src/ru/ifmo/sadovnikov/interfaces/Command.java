package ru.ifmo.sadovnikov.interfaces;

/**
 * Created by alexkane on 3/15/15.
 */
public interface Command {
    public String execute();
    public String undo();
}
