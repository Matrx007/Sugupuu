package com.ttg.sugupuu;

import com.ydgames.mxe.Game;
import processing.core.PConstants;

public class Main {
    public static void main(String[] args) {
        Game.createGame(1366, 768, new GUI(), 60f, PConstants.P2D);
    }
}
