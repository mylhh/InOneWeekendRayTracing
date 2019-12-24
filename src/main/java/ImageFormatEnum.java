/**
 * Portable pixmap format,use RGB color space.
 * The first two bytes (ASCII code) of each file are used as file
 * descriptors to indicate the specific format and encoding form.
 *
 */
public enum ImageFormatEnum {

    PPM_PIXEL_ASCILL("P3"),
    PPM_PIXEL_BINARY("P6");

    private String type;

    /**
     *
     * Constructor
     * @param type ppm file type
     */
    private ImageFormatEnum(String type){
        this.type = type;
    }

    /**
     * @return type value
     */

    public String value(){
        return type;
    }
}