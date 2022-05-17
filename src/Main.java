import javax.sound.sampled.*;
import java.io.File;
import java.util.Arrays;

public class Main {

    public static Clip clip;

    public static int[][] clipConnections = {
            {},
            {1, 2, 4, 5, 7, 10},
            {1, 2, 3, 4, 5, 7, 8, 9, 10, 11},
            {5},
            {1, 2, 3, 8, 9, 11},
            {2, 4, 11},
            {},
            {1, 2, 3, 4, 5, 8},
            {1, 3, 4, 7, 8, 10, 11},
            {1, 3, 4, 5, 7, 8, 9, 10, 11},
            {1, 2, 3, 4, 7, 8},
            {7},
    };

    public static void main(String[] args){

        int[] indices = new int[10];

        int curr = 1;
        for(int i = 0; i < indices.length; i++)
        {
            indices[i] = curr;

            int rand = (int)(Math.random() * clipConnections[curr].length);
            curr = clipConnections[curr][rand];
        }

        System.out.println(Arrays.toString(indices));
        playSound(indices);

    }

    public static String getPathFromIndex(int index){

        return switch(index){
            case 1  -> "src/samples/cw_amen01_175.wav";
            case 2  -> "src/samples/cw_amen02_165.wav";
            case 3  -> "src/samples/cw_amen03_167.wav";
            case 4  -> "src/samples/cw_amen04_170.wav";
            case 5  -> "src/samples/cw_amen06_169.wav";
            case 6  -> "src/samples/cw_amen07_172.wav";
            case 7  -> "src/samples/cw_amen08_165.wav";
            case 8  -> "src/samples/cw_amen09_175.wav";
            case 9  -> "src/samples/cw_amen13_173.wav";
            case 10 -> "src/samples/cw_amen14_175.wav";
            case 11 -> "src/samples/cw_amen20_164.wav";

            default -> throw new IllegalStateException("Unexpected value: " + index);
        };

    }
    public static void playSound(int index){

        try {

            String path = getPathFromIndex(index);

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            System.out.println("PLAYING: CLIP #" + index + " ~ " + path);

            Thread.sleep(clip.getMicrosecondLength() / 1000);

        } catch(Exception ex) {

            System.out.println("Error with playing sound.");
            ex.printStackTrace();

        }


    }
    public static void playSound(int[] indices){

        clearSoundBarrier(indices[0]);
        for(int i : indices) playSound(i);

    }

    public static void clearSoundBarrier(int beginning){

        try {
            String path = getPathFromIndex(beginning);

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        }catch (Exception ignored) {}

    }

}