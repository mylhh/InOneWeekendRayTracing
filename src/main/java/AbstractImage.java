import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public abstract class AbstractImage {
    public int width = 0;
    public int height = 0;
    public int[] data = null;
    public final int intensity = 255;
    public final int channel = 3;

    public AbstractImage(int width, int height){
        this.width = width;
        this.height = height;
        this.data = new int[width * height * channel];
    }

    public AbstractImage setPixelsData(int[] data) {
        int dataLength = width*height*channel;
        assert data.length == dataLength:"image size not suitable";
        this.data = data;
        return this;
    }

    private File createFile(String path){
        File file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            }catch (Exception e){
                System.out.println("can't not create file!");
                System.out.println(e.getMessage());
            }
        }
        return file;
    }

    public void save(String path){
        save(createFile(path));
    }

    public void save(int[] pixelData,String path){
        setPixelsData(pixelData);
        save(createFile(path));
    }

    abstract void save(File outputFile);

}
