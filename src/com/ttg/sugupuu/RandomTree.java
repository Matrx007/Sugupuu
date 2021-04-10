package com.ttg.sugupuu;

import java.util.ArrayList;

public class RandomTree {
    public static final Person root = new Person();

    static {
        Person child1 = new Person("1");
        Person child2 = new Person("2");
        Person child3 = new Person("3");
        root.children.add(child1);
        root.children.add(child2);
        root.children.add(child3);


        Person child1_1 = new Person("1_1");
        Person child1_2 = new Person("1_2");
        Person child2_1 = new Person("2_1");
        Person child2_2 = new Person("2_2");
        Person child3_1 = new Person("3_1");
        Person child3_2 = new Person("3_2");
        Person child3_3 = new Person("3_3");
        Person child3_4 = new Person("3_4");
        child1.children.add(child1_1);
        child1.children.add(child1_2);
        child2.children.add(child2_1);
        child2.children.add(child2_2);
        child3.children.add(child3_1);
        child3.children.add(child3_2);
        child3.children.add(child3_3);
        child3.children.add(child3_4);
    }
}
