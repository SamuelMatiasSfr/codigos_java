package gpjecc.blogspot.com;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.audio.Sound;

class Bucket extends Movel {

	public int pontos = 3;

	public Bucket(Rectangle rectangle, Texture texture, Sound sound) {
		super(rectangle, texture, sound);
	}

	@Override
	public void handleEvent(OrthographicCamera camera) {
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			mRectangle.x = touchPos.x - 64 / 2;
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			mRectangle.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			mRectangle.x += 200 * Gdx.graphics.getDeltaTime();

		if (mRectangle.x < 0)
			mRectangle.x = 0;
		if (mRectangle.x > 800 - 64)
			mRectangle.x = 800 - 64;
	}

	@Override
	public void move() {}
	
}