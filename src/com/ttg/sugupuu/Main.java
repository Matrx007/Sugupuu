package com.ttg.sugupuu;

import com.ydgames.mxe.Game;
import processing.core.PConstants;

public class Main {

    public static final GUI gui = new GUI();

    public static void main(String[] args) {
        Game.createGame(1366, 768, gui, 60f, PConstants.P2D);
    }
}
