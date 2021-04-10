package com.ttg.sugupuu;

import java.util.ArrayList;

public class Person implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public Person(String name, int birth_year, ArrayList<Person> children) {
        this.name = name;
        this.birth_year = birth_year;
        this.children = children;
    }

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public String name;
    public int birth_year;
    public ArrayList<Person> children = new ArrayList<>();
}
