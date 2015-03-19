package ru.ifmo.sadovnikov;

import java.util.Stack;

/**
 * Created by alexkane on 3/15/15.
 */
class SizedStack extends Stack<String> {
    private int maxSize;

    public SizedStack(int size) {
        this.maxSize = size;
    }

    public String push(String object) {
        while (this.size() > maxSize) {
            this.remove(0);
        }
        return super.push(object);
    }
}
