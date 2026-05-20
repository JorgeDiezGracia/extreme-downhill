package com.svalero.extremedownhill;

import com.badlogic.gdx.Game;
import com.svalero.extremedownhill.screens.MenuScreen;

public class ExtremeDownhill extends Game {

    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
