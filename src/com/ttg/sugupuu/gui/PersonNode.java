package com.ttg.sugupuu.gui;

import com.ttg.sugupuu.Person;
import processing.core.PConstants;

import static com.ttg.sugupuu.GUI.*;
import static com.ttg.sugupuu.Main.gui;
import static com.ttg.sugupuu.gui.DrawTree.NODE_HEIGHT;
import static com.ttg.sugupuu.gui.DrawTree.NODE_WIDTH;

public class PersonNode {
    public Person person;

    public float x, y;

    public PersonNode(Person person, float x, float y) {
        this.person = person;
        this.x = x;
        this.y = y;
    }

    public void update() {
        if(gui.game.input.isButtonDown(PConstants.LEFT)) {
            if (gui.game.mouseX-cameraX > x - NODE_WIDTH / 2 && gui.game.mouseX-cameraX < x + NODE_WIDTH / 2 &&
                    gui.game.mouseY-cameraY > y - NODE_WIDTH / 2 && gui.game.mouseY-cameraY < y + NODE_HEIGHT / 2) {
                System.out.println("mouse-over");
                if(multiSelecting) {
                    multiSelected.add(this);
                    if(selected != null) {
                        multiSelected.add(selected);
                    }
                    selected = null;
                    System.out.println("multi selected");
                } else if(canSelect) {
                    selected = this;
                    multiSelected.clear();
                    System.out.println("selected");
                }
            }
        }
    }

    public void render() {
        if(selected == this || multiSelected.contains(this)) {
            gui.game.noStroke();
            gui.game.fill(0, 255);
            gui.game.rect(x - NODE_WIDTH/2 - 8, y - NODE_HEIGHT/2 - 8,
                    NODE_WIDTH + 16, NODE_HEIGHT + 16, 8);
        }

        gui.game.noStroke();
        gui.game.fill(196, 255);
        gui.game.rect(x - NODE_WIDTH/2, y - NODE_HEIGHT/2,
                NODE_WIDTH, NODE_HEIGHT, 4);

        if(person.name != null) {
            gui.game.noStroke();
            gui.game.fill(0);
            gui.game.text(person.name, x, y);
        }
    }
}
