package org.example;

import org.example.StringBuilderImplementation.CustomStringBuilder;

public class Main {
    public static void main(String[] args) {
        CustomStringBuilder customStringBuilder = new CustomStringBuilder();
        customStringBuilder.append("one");
        customStringBuilder.append("two");
        customStringBuilder.insert(3, "three");
        System.out.println(customStringBuilder);
        customStringBuilder.delete(3, 8);
        System.out.println(customStringBuilder);
        customStringBuilder.undo();
        System.out.println(customStringBuilder);
    }
}