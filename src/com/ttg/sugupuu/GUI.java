package com.ttg.sugupuu;

import com.ttg.sugupuu.gui.DrawTree;
import com.ttg.sugupuu.gui.PersonNode;
import com.ydgames.mxe.Game;
import com.ydgames.mxe.GameContainer;
import processing.core.PConstants;

import java.util.ArrayList;

import static com.ttg.sugupuu.Main.gui;

public class GUI extends GameContainer {

    // ENGINE
    public Game game;

    // DATA
    DrawTree drawTree;

    // RENDERING
    public static float cameraX, cameraY;

    // PROGRAM FLOW
    public static boolean multiSelecting = false;
    public static boolean canSelect = true;
    public static PersonNode selected = null;
    public static ArrayList<PersonNode> multiSelected = new ArrayList<>();


    @Override
    public void setup() {
        game = getGame();

        drawTree = new DrawTree(RandomTree.root);
    }

    float dragStartCameraX, dragStartCameraY;
    float dragStartMouseX, dragStartMouseY;
    boolean dragging;

    @Override
    public void update(double v) {
        if(gui.game.input.isKey(PConstants.SHIFT) ||
                gui.game.input.isKeyDown(PConstants.SHIFT)) {
            multiSelecting = true;
            canSelect = false;
        } else {
            multiSelecting = false;
            canSelect = true;
        }

        if(game.input.isButtonDown(PConstants.LEFT)) {
            dragging = true;

            dragStartCameraX = cameraX;
            dragStartCameraY = cameraY;

            dragStartMouseX = gui.game.mouseX;
            dragStartMouseY = gui.game.mouseY;
        }

        if(dragging) {
            if(!game.input.isButton(PConstants.LEFT)) {
                dragging = false;
            }

            cameraX = dragStartCameraX + (gui.game.mouseX - dragStartMouseX);
            cameraY = dragStartCameraY + (gui.game.mouseY - dragStartMouseY);
        }

        drawTree.update();
    }

    @Override
    public void updateTick() {
    }

    @Override
    public void render() {
        gui.game.background(255);

        gui.game.pushMatrix();
        gui.game.translate(cameraX, cameraY);
        drawTree.render();
        gui.game.popMatrix();
    }


    @Override
    public void settings() {

    }

    @Override
    public void init() {

    }
}
