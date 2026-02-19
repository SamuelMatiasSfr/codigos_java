package gpjecc.blogspot.com;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class SimpleGame extends ApplicationAdapter {
	private Texture dropImage;
	private Texture bucketImage;
	private Texture raioImage;
	private Texture bolhaSabaoImage;
	private Texture meteoroImage;
	private Sound dropSound;
	private Sound raioSound;
	private Music rainMusic;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private BitmapFont font;
	private Movel bucket;
	private Array<Movel> moveis;
	private long lastDropTime;

	@Override
	public void create() {
		Texture.setEnforcePotImages(false);

		dropImage = new Texture(Gdx.files.internal("assets/droplet.png"));
		raioImage = new Texture(Gdx.files.internal("assets/raio.png"));
		bucketImage = new Texture(Gdx.files.internal("assets/bucket.png"));
		bolhaSabaoImage = new Texture(Gdx.files.internal("assets/bubble.png")); 
		meteoroImage = new Texture(Gdx.files.internal("assets/meteoro.png"));

		dropSound = Gdx.audio.newSound(Gdx.files.internal("assets/drop.wav"));
		raioSound = Gdx.audio.newSound(Gdx.files.internal("assets/thunder.mp3"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/rain.mp3"));

		rainMusic.setLooping(true);
		rainMusic.play();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		font = new BitmapFont();
		font.setColor(Color.YELLOW);
		font.setScale(2);

		spawnBucket();

		moveis = new Array<Movel>();
		spawnMovel();
	}

	private void spawnMovel() {
		int x = MathUtils.random(0, 3);

		Rectangle rectangle = new Rectangle();
		rectangle.x = MathUtils.random(0, 800 - 64);
		rectangle.y = 480;
		rectangle.width = 64;
		rectangle.height = 64;

		if(x == 0){
			Movel movel = new Chuva(rectangle, dropImage, dropSound);
			moveis.add(movel);
			lastDropTime = TimeUtils.nanoTime();
		}else if(x == 1){
			Movel movel = new Raio(rectangle, raioImage, raioSound);
			moveis.add(movel);
			lastDropTime = TimeUtils.nanoTime();
		}else if(x == 2){
			rectangle.x = MathUtils.random(0, 800 - 64);
			rectangle.y = 0-64;
			Movel movel = new BolaSabao(rectangle, bolhaSabaoImage, null);
			moveis.add(movel);
			lastDropTime = TimeUtils.nanoTime();
		}else if(x == 3){
			Movel movel = new Meteoro(rectangle, meteoroImage, null);
			moveis.add(movel);
			lastDropTime = TimeUtils.nanoTime();
		}
	}

	private void spawnBucket() {
		Rectangle rectangleBucket = new Rectangle();
		rectangleBucket.x = 800 / 2 - 64 / 2;
		rectangleBucket.y = 20; 
		rectangleBucket.width = 64;
		rectangleBucket.height = 64;
		bucket = new Bucket(rectangleBucket, bucketImage, null);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(bucket.getmImage(), bucket.getmRectangle().x, bucket.getmRectangle().y);
		for (Movel movel : moveis) {
			movel.desenha(batch);
		}
		font.draw(batch, "Pontos = " + ((Bucket) bucket).pontos, 650, 460);
		batch.end();

		bucket.handleEvent(camera);

		if (TimeUtils.nanoTime() - lastDropTime > 1000000000){
			spawnMovel();
		}

		Iterator<Movel> iter = moveis.iterator();
		while (iter.hasNext()) {
			Movel movel = iter.next();

			movel.move();

			if (movel instanceof Chuva) { 
				if (movel.getmRectangle().y + 64 < 0)
					iter.remove();
				if (movel.getmRectangle().overlaps(bucket.getmRectangle())) {
					movel.PlaySound();
					bucket.PlaySound();
					((Bucket) bucket).pontos++;
					iter.remove();
				}
			}

			if (movel instanceof Raio) { 
				if (movel.getmRectangle().y + 64 < 0)
					iter.remove();
				if (movel.getmRectangle().overlaps(bucket.getmRectangle())) {
					movel.PlaySound();
					((Bucket) bucket).pontos = 0;
					iter.remove();
				}
			}

			if (movel instanceof Meteoro) { 
				if (movel.getmRectangle().y + 64 < 0)
					iter.remove();
				if (movel.getmRectangle().overlaps(bucket.getmRectangle())) {
					movel.PlaySound();
					Gdx.app.exit();
					iter.remove();
				}
			}
		}
	}

	@Override
	public void dispose() {
		dropImage.dispose();
		bucketImage.dispose();
		raioImage.dispose();
		bolhaSabaoImage.dispose();
		meteoroImage.dispose();

		font.dispose();

		dropSound.dispose();
		rainMusic.dispose();
		raioSound.dispose();
		batch.dispose();
	}
}