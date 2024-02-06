package org.example.Java_Core.StringBuilderImplementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomStringBuilder{

    private char[] value;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private List<CustomStringBuilderMemento> history;

    public CustomStringBuilder() {
        value = new char[DEFAULT_CAPACITY];
        size = 0;
        history = new ArrayList<>();
    }

    public CustomStringBuilder(int capacity) {
        value = new char[capacity];
        size = 0;
    }

    public CustomStringBuilder append(String str) {
        if (str == null) {
            return this;
        }

        saveToHistory();
        int strLen = str.length();
        ensureCapacity(size + strLen);
        str.getChars(0, strLen, value, size);
        size += strLen;

        return this;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > value.length) {
            expandCapacity(minCapacity);
        }
    }

    private void expandCapacity(int minCapacity) {
        int newCapacity = value.length * 2;
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        value = Arrays.copyOf(value, newCapacity);
    }

    public CustomStringBuilder insert(int index, String str) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (str == null) {
           return this;
        }
        saveToHistory();
        int strLen = str.length();
        ensureCapacity(size + strLen);

        System.arraycopy(value, index, value, index + strLen, size - index);

        str.getChars(0, strLen, value, index);
        size += strLen;

        return this;
    }

    public CustomStringBuilder delete(int start, int end) {

        if (start < 0 || end > size || start >= end) {
            throw new IndexOutOfBoundsException("Invalid start or end index");
        }

        saveToHistory();
        System.arraycopy(value, end, value, start, size - end);
        size -= (end - start);

        return this;
    }

    public void undo() {
        if (!history.isEmpty()) {
            CustomStringBuilderMemento memento = history.remove(history.size() - 1);
            value = memento.getState();
            size = memento.getSize();
        }
    }

    private void saveToHistory() {
        history.add(new CustomStringBuilderMemento(value, size));
    }

    @Override
    public String toString() {
        return new String(value, 0, size);
    }
}


class CustomStringBuilderMemento {
    private char[] state;
    private int size;

    public CustomStringBuilderMemento(char[] state, int size) {
        this.state = Arrays.copyOf(state, state.length);
        this.size = size;
    }

    public char[] getState() {
        return Arrays.copyOf(state, state.length);
    }

    public int getSize() {
        return size;
    }
}
