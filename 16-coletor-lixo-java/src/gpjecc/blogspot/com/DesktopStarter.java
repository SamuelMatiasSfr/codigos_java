package gpjecc.blogspot.com;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class DesktopStarter{

	public static void main (String[] args) {
        new LwjglApplication(new SimpleGame(), "Atividade LLP2", 640, 480, false);
		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
	}
}