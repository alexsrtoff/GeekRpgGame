package com.geekbrains.rpg.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.geekbrains.rpg.game.logic.GameController;
import com.geekbrains.rpg.game.logic.WorldRenderer;
import com.geekbrains.rpg.game.screens.utils.Assets;

public class GameScreen extends AbstractScreen {
    private GameController gc;
    private WorldRenderer worldRenderer;
    private Stage stage;
    private int click = 0;



    public GameScreen(SpriteBatch batch) {
        super(batch);
    }

    @Override
    public void show() {
        gc = new GameController();
        worldRenderer = new WorldRenderer(gc, batch);
        createGui();
    }

    @Override
    public void render(float delta) {
        if(click  % 2 == 0){
            gc.update(delta);
        }
        worldRenderer.render();
        stage.draw();

    }

    public void createGui() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin();
        skin.addRegions(Assets.getInstance().getAtlas());

        BitmapFont font12 = Assets.getInstance().getAssetManager().get("fonts/font12.ttf");
        TextButton.TextButtonStyle menuBtnStyle = new TextButton.TextButtonStyle(
                skin.getDrawable("shortButton"), null, null, font12);

        final TextButton btnPause = new TextButton("PAUSE", menuBtnStyle);
        btnPause.setPosition(1135, 680);
        btnPause.setSize(60,30);
        TextButton btnMenu = new TextButton("MENU", menuBtnStyle);
        btnMenu.setPosition(1200, 680);
        btnMenu.setSize(60,30);


        btnPause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click++;
                if(click % 2 == 0){
                    btnPause.setText("PAUSE");
                }else btnPause.setText("RESUME");
            }

        });

        btnMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.MENU);
            }
        });

        stage.addActor(btnPause);
        stage.addActor(btnMenu);
        skin.dispose();
    }
}