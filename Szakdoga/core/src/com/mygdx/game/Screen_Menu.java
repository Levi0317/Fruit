package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
public class Screen_Menu  implements Screen {




    private final MyGdxGame app;
    private Stage stage;
    private Skin skin;
    private ShapeRenderer shapeRenderer;
    private TextButton buttonPlay, buttonExit,buttonHighsScore;

    public Screen_Menu(final MyGdxGame app) {
        this.app=app;
        this.stage= new Stage(new FitViewport(MyGdxGame.WORLD_WIDTH,MyGdxGame.WORLD_HEIGHT,app.camera));
        this.shapeRenderer=new ShapeRenderer();

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        this.skin= new Skin();

        initButtons();
    }

    public void update(float deltaTime){
        stage.act(deltaTime);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);
        stage.draw();
        app.batch.begin();
        app.font120.draw(app.batch,"MainMenu",300,1700);
        app.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        shapeRenderer.dispose();

    }

    private void initButtons(){

        buttonPlay = new TextButton("Play", skin, "default");
        buttonPlay.setPosition(300, 960);
        buttonPlay.setSize(480, 260);
        buttonPlay.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        buttonPlay.addListener(new ClickListener() {

          @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.screenGame);
                MyGdxGame.music.play();
            }
        });

        buttonHighsScore = new TextButton("HighScore", skin, "default");
        buttonHighsScore.setPosition(300, 600);
        buttonHighsScore.setSize(480, 260);
        buttonHighsScore.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -20, .5f, Interpolation.pow5Out))));
        buttonHighsScore.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScreen(app.screenHighScore);
            }
        });

        buttonExit = new TextButton("Exit", skin, "default");
        buttonExit.setPosition(300, 240);
        buttonExit.setSize(480, 260);
        buttonExit.addAction(sequence(alpha(0), parallel(fadeIn(.5f), moveBy(0, -50, .5f, Interpolation.pow5Out))));
        buttonExit.addListener(new ClickListener() {

             @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage.addActor(buttonPlay);
        stage.addActor(buttonHighsScore);
        stage.addActor(buttonExit);
    }
}

