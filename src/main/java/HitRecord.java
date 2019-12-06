import java.util.Objects;

/**
 * The {@code HitRecord} class record ray hit information,constain parameter {@code t},
 * collision point coordinates,collision point normal.
 */
public class HitRecord {
    double t;
    Vector3 hitPoint;
    Vector3 normal;
    Material material;

    @Override
    public String toString() {
        return "HitRecord{" +
                "t=" + t +
                ", hitPoint=" + hitPoint +
                ", normal=" + normal +
                ", material=" + material +
                '}';
    }
}
