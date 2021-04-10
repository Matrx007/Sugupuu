package com.ttg.sugupuu.gui;

import com.ttg.sugupuu.GUI;
import com.ttg.sugupuu.Person;
import com.ttg.sugupuu.RelationTree;
import com.ttg.sugupuu.SaveUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static com.ttg.sugupuu.GUI.*;
import static com.ttg.sugupuu.Main.gui;

public class DrawTree {

    public static final float NODE_WIDTH = 128;
    public static final float NODE_HEIGHT = 32;

    public static RelationTree relationTree = new RelationTree();

    private ArrayList<PersonNode> nodes = new ArrayList<>();
    private ArrayList<PersonLine> lines = new ArrayList<>();

    public DrawTree(RelationTree relationTree) {
        DrawTree.relationTree = relationTree;
        for (Person root : relationTree.rootNodes) {
            visualizeTree(root, 0, 0, 0);
        }
    }

    public void update() {
        for(PersonNode node : nodes) {
            node.update();
        }

        if(gui.game.input.isKeyDown('N')) {
            if(GUI.selected != null) {
                Person newPerson = createNewPerson();
                PersonNode newPersonNode = new PersonNode(newPerson, GUI.selected.x, GUI.selected.y + NODE_HEIGHT + 32);
                nodes.add(newPersonNode);
                lines.add(new PersonLine(GUI.selected, newPersonNode));

                GUI.selected.person.children.add(newPerson);
            } else if(GUI.multiSelected.size() > 0) {
                Person newPerson = createNewPerson();
                PersonNode newPersonNode = new PersonNode(newPerson,
                        -cameraX+gui.game.pixelWidth/2f, -cameraY+gui.game.pixelHeight/2f);

                nodes.add(newPersonNode);

                for(PersonNode parent : GUI.multiSelected) {
                    parent.person.children.add(newPerson);
                    lines.add(new PersonLine(parent, newPersonNode));
                }
            } else {
                Person newPerson = createNewPerson();
                PersonNode newPersonNode = new PersonNode(newPerson,
                        -cameraX+gui.game.pixelWidth/2f, -cameraY+gui.game.pixelHeight/2f);
                relationTree.rootNodes.add(newPerson);
            }
        }

        if(gui.game.input.isKeyDown('S')) {
            SaveUtil.dataToFile(DrawTree.relationTree);
        }

        if(gui.game.input.isKeyDown('L')) {
            DrawTree.relationTree = (RelationTree) SaveUtil.loadFromFile();
            nodes.clear();
            lines.clear();
            xCoordinatesByLevel.clear();
            for (Person root : relationTree.rootNodes) {
                visualizeTree(root, 0, 0, 0);
            }
        }

        /*if(gui.game.input.isKeyDown('D')) {
            if(GUI.selected != null) {
                if(relationTree.rootNodes.contains(selected.person)) {
                    relationTree.rootNodes.addAll(selected.person.children);
                }
                relationTree.rootNodes.remove(selected.person);

                nodes.clear();
                lines.clear();
                xCoordinatesByLevel.clear();
                for (Person root : relationTree.rootNodes) {
                    visualizeTree(root, 0, 0, 0);
                }
            } else {

            }
        }*/
    }

    public void render() {
        for(PersonLine line : lines) {
            line.render();
        }
        for(PersonNode node : nodes) {
            node.render();
        }
    }



    HashMap<Integer, Float> xCoordinatesByLevel = new HashMap<>();
    private void visualizeTree(Person root, float x, float y, int depth) {
        if(root == null) return;

        PersonNode rootNode = new PersonNode(root, x, y);
        nodes.add(rootNode);

        if(!xCoordinatesByLevel.containsKey(depth+1)) {
            xCoordinatesByLevel.put(depth+1, 0f);
        }
        for(Person child : root.children) {
            float currentX = xCoordinatesByLevel.get(depth+1);

            PersonNode childNode = new PersonNode(child, currentX, y + NODE_HEIGHT + 64);
            nodes.add(childNode);
            lines.add(new PersonLine(rootNode, childNode));

            xCoordinatesByLevel.put(depth+1, currentX + NODE_WIDTH + 32);

            visualizeTree(child, currentX, y + NODE_HEIGHT + 64, depth+1);
        }
    }/*

    private void visualizeTree(RelationTree relationTree) {
        float currentY = 0;
        for(Person person : relationTree.rootNodes) {
            PersonNode rootNode = new PersonNode(person, 0, currentY);
            nodes.add(rootNode);

            visualizeTree(person, );
        }
    }

    private void visualizeTree(PersonNode root, float x, float y, int depth) {
        if(root == null) return;

        if(!xCoordinatesByLevel.containsKey(depth+1)) {
            xCoordinatesByLevel.put(depth+1, 0f);
        }
        for(Person child : root.person.children) {
            float currentX = xCoordinatesByLevel.get(depth+1);

            PersonNode childNode = new PersonNode(child, currentX, y + NODE_HEIGHT + 64);
            nodes.add(childNode);
            lines.add(new PersonLine(root, childNode));

            xCoordinatesByLevel.put(depth+1, currentX + NODE_WIDTH + 32);

            visualizeTree(child, currentX, y + NODE_HEIGHT + 64, depth+1);
        }
    }*/

    public static Person createNewPerson() {
        String name = JOptionPane.showInputDialog("Person name: ");
        if(name == null) return null;
        String birth_year = JOptionPane.showInputDialog("Birth year: ");
        if(birth_year == null) return null;
        String gender = JOptionPane.showInputDialog("Gender (male/female): ");
        if(gender == null) return null;

        boolean isMale = false;
        if(gender.equalsIgnoreCase("male")) {
            isMale = true;
        } else if(gender.equalsIgnoreCase("female")) {
            isMale = false;
        } else {
            return null;
        }

        return new Person(name, Integer.parseInt(birth_year), new ArrayList<>(), isMale);
    }
}



