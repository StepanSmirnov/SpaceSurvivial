package framework;

/**
 * Created by Степаашка on 01.04.2017.
 */

public interface Game {
//    public Input getInput();
    public FileIO getFileIO();
//    public Graphics getGraphics();
    public Audio getAudio();
    public void setScreen(Screen screen);
    public Screen getCurrentScreen();
    public Screen getStartScreen();
}
