import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * The {@code JPPM} class represent a pixel image after render,iamge format use ppm format currently.
 */
public class JPPM extends AbstractImage {

    private JPPM(int width, int height){
        super(width,height);
    }

    public static JPPM createImage(int width, int height){
       return new JPPM(width,height);
    }

    @Override
    void save(File outputFile) {
        try (Writer out = new FileWriter(outputFile)){
            StringBuilder sb = new StringBuilder();
            sb.append("P3\n")
              .append(String.valueOf(this.width)).append(' ').append(String.valueOf(this.height)).append('\n')
              .append(String.valueOf(this.intensity)).append('\n');

            for(int i = 0;i<this.height;i++){
                for(int j = 0;j<this.width;j++){
                    int[] rgb = Utils.decodeToRGB(data[(height - 1 - i)*width+j]);
                    sb.append(rgb[0]).append(' ')
                      .append(rgb[1]).append(' ')
                      .append(rgb[2]).append(' ');
                }
                sb.append('\n');
            }
            out.write(sb.toString());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
