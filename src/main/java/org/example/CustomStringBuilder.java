package org.example;

import java.util.Stack;

public class CustomStringBuilder {
    private StringBuilder builder;
    private Stack<Snapshot> snapshots;

    public CustomStringBuilder() {
        this.builder = new StringBuilder();
        this.snapshots = new Stack<>();
    }

    private void saveState() {
        snapshots.push(new Snapshot(builder.toString()));
    }

    public CustomStringBuilder append(String str) {
        saveState();
        builder.append(str);
        return this;
    }

    public CustomStringBuilder insert(int offset, String str) {
        saveState();
        builder.insert(offset, str);
        return this;
    }

    public CustomStringBuilder delete(int start, int end) {
        saveState();
        builder.delete(start, end);
        return this;
    }

    public CustomStringBuilder replace(int start, int end, String str) {
        saveState();
        builder.replace(start, end, str);
        return this;
    }

    public CustomStringBuilder undo() {
        if (!snapshots.isEmpty()) {
            Snapshot snapshot = snapshots.pop();
            builder = new StringBuilder(snapshot.getState());
        } else {
            System.out.println("Нет состояний для восстановления.");
        }
        return this;
    }

    @Override
    public String toString() {
        return builder.toString();
    }

    private static class Snapshot {
        private final String state;

        public Snapshot(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }
}

