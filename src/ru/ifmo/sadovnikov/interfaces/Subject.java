package ru.ifmo.sadovnikov.interfaces;

/**
 * Created by alexkane on 4/2/15.
 */
public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers(String story);
}
