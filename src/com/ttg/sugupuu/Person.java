package com.ttg.sugupuu;

import java.util.ArrayList;

public class Person implements java.io.Serializable {

    public Person(String name, int birth_year, ArrayList<Person> children) {
        this.name = name;
        this.birth_year = birth_year;
        this.children = children;
    }
    private static final long serialVersionUID = 1L;

    public String name;
    public int birth_year;
    public ArrayList<Person> children;
}
