package com.ttg.sugupuu.gui;

import com.ttg.sugupuu.Person;

import java.util.ArrayList;
import java.util.HashMap;

import static com.ttg.sugupuu.Main.gui;

public class DrawTree {

    public static final float NODE_WIDTH = 128;
    public static final float NODE_HEIGHT = 32;

    private ArrayList<PersonNode> nodes = new ArrayList<>();
    private ArrayList<PersonLine> lines = new ArrayList<>();

    public DrawTree(Person root) {
        visualizeTree(root, 0, 0, 0);
    }

    public void update() {
        for(PersonNode node : nodes) {
            node.update();
        }
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
    }
}



