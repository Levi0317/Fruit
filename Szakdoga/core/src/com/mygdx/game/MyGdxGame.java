package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class MyGdxGame extends Game {

	public static final int WORLD_WIDTH = 1080;
	public static final int WORLD_HEIGHT =1920;
	public OrthographicCamera camera;
	public static BitmapFont font30;
	public static BitmapFont font60;
	public static BitmapFont font120;
	public Screen_Game screenGame;
	public static Music music;
	public static Preferences pref;
	public static int highScore;

	public static Random random= new Random();
	public SpriteBatch batch;

	public AssetManager assets;
	public Screen_Loading screenLoading;
	public Screen_Splash screenSplash;
	public Screen_Menu screenMenu;
	public Screen_HighScore screenHighScore;


	@Override
	public void create() {
		assets=new AssetManager();

		batch=new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false,WORLD_WIDTH,WORLD_HEIGHT);

		initFonts();

		pref= Gdx.app.getPreferences("highScore");
		highScore=MyGdxGame.pref.getInteger("highScore",0);

		screenLoading=new Screen_Loading(this);
		screenSplash= new Screen_Splash(this);
		screenMenu=new Screen_Menu(this);
		screenGame= new Screen_Game(this);
		screenHighScore=new Screen_HighScore(this);

		//music= Gdx.audio.newMusic(Gdx.files.internal("Sounds/sound.ogg"));
		//music.setVolume(0.2f);
		//music.setLooping(true);

		//this.setScreen(screenLoading);
		//this.setScreen(screenMenu);
		this.setScreen(screenSplash);

	}
	public void pause(){
	}

	@Override
	public void dispose() {
		batch.dispose();
		font30.dispose();
		font60.dispose();
		font120.dispose();
		assets.dispose();
		screenLoading.dispose();
		screenSplash.dispose();
		screenMenu.dispose();
		screenGame.dispose();
		screenHighScore.dispose();
		getScreen().dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		screenGame.resize(width, height);
	}
	private void initFonts(){
		FreeTypeFontGenerator generator= new FreeTypeFontGenerator(Gdx.files.internal("Milkhouse-rg86O.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter paramsOne= new FreeTypeFontGenerator.FreeTypeFontParameter();
		paramsOne.size=60;
		paramsOne.color= Color.BLACK;
		font60= generator.generateFont(paramsOne);
		FreeTypeFontGenerator.FreeTypeFontParameter paramsTwo= new FreeTypeFontGenerator.FreeTypeFontParameter();

		paramsOne.size=120;
		paramsOne.color=Color.BLACK;
		font120= generator.generateFont(paramsOne);

		paramsOne.size=30;
		paramsOne.color=Color.BLACK;
		font30= generator.generateFont(paramsOne);
	}
}