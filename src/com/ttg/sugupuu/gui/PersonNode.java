package com.ttg.sugupuu.gui;

import com.ttg.sugupuu.GUI;
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

    float dragStartOffsetX, dragStartOffsetY;
    float dragStartMouseX, dragStartMouseY;
    boolean dragging;

    public void update() {
        if(gui.game.input.isButtonDown(PConstants.LEFT) && !clickedOnNode) {
            if (gui.game.mouseX-cameraX > x - NODE_WIDTH / 2 && gui.game.mouseX-cameraX < x + NODE_WIDTH / 2 &&
                    gui.game.mouseY-cameraY > y - NODE_WIDTH / 2 && gui.game.mouseY-cameraY < y + NODE_HEIGHT / 2) {

                dragging = true;
                dragStartOffsetX = x;
                dragStartOffsetY = y;
                dragStartMouseX = (gui.game.mouseX + cameraX);
                dragStartMouseY = (gui.game.mouseY + cameraY);

                clickedOnNode = true;
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

        if(dragging) {
            if(!gui.game.input.isButton(PConstants.LEFT)) {
                dragging = false;
            }

            x = dragStartOffsetX + ((gui.game.mouseX + cameraX) - dragStartMouseX);
            y = dragStartOffsetY + ((gui.game.mouseY + cameraY) - dragStartMouseY);
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

            gui.game.textAlign(PConstants.CENTER, PConstants.CENTER);
            gui.game.textSize(16f);
            gui.game.text(person.name, x, y);
        }
    }
}
