import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * The {@code Image} class represent a pixel image after render,iamge format use ppm format currently.
 */
public class Image {

    private int width = 0;
    private int height = 0;
    private int[] data = null;

    private int intensity = 255;

    private int channel = 3;

    private Image(int width,int height){
        this.width = width;
        this.height = height;
        data = new int[width * height * 3];
    };

    public static Image createImage(int width,int height,PPMFormat type){
       return new Image(width,height);
    }

    public Image setData(int[] data) {
        int dataLength = width*height*channel;
        assert data.length == dataLength:"image size not suitable";
        this.data = data;
        return this;
    }

    public void save(String path){

        File image = new File(path);
        if(!image.exists()){
            try {
                image.createNewFile();
            }catch (Exception e){
                System.out.println("can't not create file!");
                System.out.println(e.getMessage());
            }
        }

        try (Writer out = new FileWriter(image)){
            StringBuilder sb = new StringBuilder();
            sb.append("P3\n")
              .append(String.valueOf(this.width)).append(' ').append(String.valueOf(this.height)).append('\n')
              .append(String.valueOf(this.intensity)).append('\n');

            for(int i = 0;i<this.height;i++){
                for(int j = 0;j<this.width;j++){
                    sb.append(data[i*width*3+j*3]).append(' ')
                      .append(data[i*width*3+j*3+1]).append(' ')
                      .append(data[i*width*3+j*3+2]).append(' ');
                }
                sb.append('\n');
            }
            out.write(sb.toString());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
