package com.ttg.sugupuu;

import com.ttg.sugupuu.gui.DrawTree;
import com.ttg.sugupuu.gui.InfoDialog;
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
    InfoDialog infoDialog;

    // RENDERING
    public static float cameraX, cameraY;

    // PROGRAM FLOW
    public static boolean multiSelecting = false;
    public static boolean clickedOnNode = false;
    public static boolean canSelect = true;
    public static PersonNode selected = null;
    public static ArrayList<PersonNode> multiSelected = new ArrayList<>();


    @Override
    public void setup() {
        game = getGame();

        RelationTree relationTree = new RelationTree();
        relationTree.rootNodes.add(RandomTree.root);
        drawTree = new DrawTree(relationTree);
        infoDialog = new InfoDialog();
    }

    float dragStartCameraX, dragStartCameraY;
    float dragStartMouseX, dragStartMouseY;
    boolean dragging;

    @Override
    public void update(double v) {
        clickedOnNode = false;

        if(gui.game.input.isKey(PConstants.SHIFT) ||
                gui.game.input.isKeyDown(PConstants.SHIFT)) {
            multiSelecting = true;
            canSelect = false;
        } else {
            multiSelecting = false;
            canSelect = true;
        }

        drawTree.update();
        infoDialog.update();

        if(game.input.isButtonDown(PConstants.LEFT) && !clickedOnNode) {
            selected = null;

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

        infoDialog.render();

        float currentY = game.pixelHeight;
        gui.game.textAlign(PConstants.RIGHT, PConstants.BOTTOM);
        gui.game.text("S to save", game.pixelWidth - 8, currentY);
        currentY -= 32;
        gui.game.text("L to load", game.pixelWidth - 8, currentY);
        currentY -= 32;
        gui.game.text("N to create a new node", game.pixelWidth - 8, currentY);
        currentY -= 32;
        //gui.game.text("D to delete a node", game.pixelWidth - 8, currentY);
        //currentY -= 32;
    }


    @Override
    public void settings() {

    }

    @Override
    public void init() {

    }
}
