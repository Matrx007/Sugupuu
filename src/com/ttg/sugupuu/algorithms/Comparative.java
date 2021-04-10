package com.ttg.sugupuu.algorithms;

import com.ttg.sugupuu.Person;

import java.util.ArrayList;
import java.util.Collections;

public class Comparative {

    public static ArrayList<Person> findParents(Person child, ArrayList<Person> roots) {
        ArrayList<Person> parents = new ArrayList<>();
        roots.forEach(person -> {
            if (!person.children.isEmpty()) {
                if (person == child) parents.add(person);
                else parents.addAll(findParents(child, person.children));
            }
        });
        return parents;
    }

    public static ArrayList<Person> nameStartsWith(String name, ArrayList<Person> roots) {
        ArrayList<Person> persons = new ArrayList<>();
        roots.forEach(person -> {
            if (!person.children.isEmpty()) {
                if (person.name.startsWith(name)) persons.add(person);
                else persons.addAll(nameStartsWith(name, person.children));
            }
        });
        return persons;
    }

    public static ArrayList<Person> nameEndsWith(String name, ArrayList<Person> roots) {
        ArrayList<Person> persons = new ArrayList<>();
        roots.forEach(person -> {
            if (!person.children.isEmpty()) {
                if (person.name.endsWith(name)) persons.add(person);
                else persons.addAll(nameEndsWith(name, person.children));
            }
        });
        return persons;
    }

    public static ArrayList<Person> getSiblings(Person person, ArrayList<Person> roots) {
        ArrayList<Person> siblings = new ArrayList<>();
        findParents(person, roots).forEach(parent -> {
            siblings.addAll(parent.children);
            siblings.remove(person);
        });
        return siblings;
    }

    public static ArrayList<Person> getSiblings(Person person, ArrayList<Person> roots, boolean isMale) {
        ArrayList<Person> siblings = new ArrayList<>();
        getSiblings(person, roots).forEach(sibling -> {
            if (sibling.isMale == isMale) siblings.add(sibling);
        });
        return siblings;
    }

    public static ArrayList<ArrayList<Person>> getGrandparents(Person person, ArrayList<Person> roots) {
        ArrayList<ArrayList<Person>> grandparents = new ArrayList<>();
        findParents(person, roots).forEach(parent -> grandparents.add(findParents(parent, roots)));
        return grandparents;
    }

}
