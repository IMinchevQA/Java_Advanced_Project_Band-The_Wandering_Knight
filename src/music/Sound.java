package music;

import sun.audio.*;
import java.io.*;

public class Sound{
    private AudioPlayer MGP;
    private AudioStream BGM;
    private AudioData MD;
    private ContinuousAudioDataStream loop;

    public Sound() {
        try{
            this.MGP = AudioPlayer.player;
            this.BGM = new AudioStream(new FileInputStream("1_-_Hour_converted.wav"));
            this.MD = BGM.getData();
            this.loop = new ContinuousAudioDataStream(MD);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public void startMusic(){
        this.MGP.start(this.loop);
    }

    public void stopMusic(){
        this.MGP.stop(this.loop);
    }


    }

