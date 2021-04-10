package com.ttg.sugupuu.gui;

import static com.ttg.sugupuu.Main.gui;

public class PersonLine {
    PersonNode from, to;

    public PersonLine(PersonNode from, PersonNode to) {
        this.from = from;
        this.to = to;
    }

    public PersonLine() {
    }

    public void render() {
        gui.game.noFill();
        gui.game.stroke(0);
        gui.game.strokeWeight(2);

        gui.game.line(from.x, from.y - DrawTree.NODE_HEIGHT/2,
                to.x, to.y - DrawTree.NODE_HEIGHT/2);
    }
}
