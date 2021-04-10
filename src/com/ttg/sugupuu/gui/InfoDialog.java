package com.ttg.sugupuu.gui;

import com.ttg.sugupuu.GUI;
import com.ttg.sugupuu.Person;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.HashSet;

import static com.ttg.sugupuu.Main.gui;
import static com.ttg.sugupuu.algorithms.Comparative.findParents;
import static com.ttg.sugupuu.algorithms.Comparative.getSiblings;

public class InfoDialog {
    public InfoDialog() {
    }


    public void updateInfo() {

    }

    public void render() {
        if(GUI.selected != null) {
            float currentY = 8;

            gui.game.textSize(16f);

            gui.game.noStroke();
            gui.game.fill(240, 128);
            gui.game.rect(0, 0, 480, 128);

            gui.game.noStroke();
            gui.game.fill(0);

            gui.game.textAlign(PConstants.LEFT, PConstants.TOP);
            gui.game.text("Name: "+GUI.selected.person.name, 8, currentY);
            currentY += 32;
            gui.game.text("Gender: "+(GUI.selected.person.isMale ? "male" : "female"), 8, currentY);
            currentY += 32;
            gui.game.text("Birth year: "+GUI.selected.person.birth_year, 8, currentY);
            currentY += 32;

            currentY += 32;
            gui.game.text("Children: ", 8, currentY);
            currentY += 32;
            for(Person person : GUI.selected.person.children) {
                gui.game.text(person.name, 8+32, currentY);
                currentY += 32;
            }


            currentY += 32;
            gui.game.text("Parents: ", 8, currentY);
            currentY += 32;
            HashSet<Person> parents = findParents(GUI.selected.person, DrawTree.relationTree.rootNodes);
            for(Person person : parents) {
                if(person.name == null) {
                    continue;
                }
                gui.game.text(person.name, 8+32, currentY);
                currentY += 32;
            }


            currentY += 32;
            gui.game.text("Siblings: ", 8, currentY);
            currentY += 32;
            HashSet<Person> siblings = getSiblings(GUI.selected.person, DrawTree.relationTree.rootNodes);
            for(Person person : siblings) {
                gui.game.text(person.name, 8+32, currentY);
                currentY += 32;
            }


        }
    }

    public void update() {

    }
}
