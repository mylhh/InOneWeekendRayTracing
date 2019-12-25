import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public abstract class AbstractImage {
    public static final int OPAQUE = 255;

    public int width = 0;
    public int height = 0;
    public int[] data = null;
    public final int intensity = 255;
    public final int channel = 3;

    public AbstractImage(int width, int height){
        this.width = width;
        this.height = height;
        this.data = new int[width * height];
    }

    public AbstractImage setPixelsData(int[] data) {
        int dataLength = width*height;
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

    public void setPixelARGB(int index,int argb){
        this.data[index] = argb;
    }

    public void setPixelRGB(int index,Vector3 color){
        int red = (int)(255.999*color.r());
        int green = (int)(255.999*color.g());
        int blue = (int)(255.999*color.b());
        this.setPixelARGB(index,Utils.to4ByteARGB(red,green,blue,OPAQUE));
    }

    abstract void save(File outputFile);

}
