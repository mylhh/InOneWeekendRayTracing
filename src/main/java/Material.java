import javafx.util.Pair;

import java.util.Optional;

public interface Material {

    static Vector3 reflect(Vector3 in,Vector3 normal){
        double length = in.dot(normal);
        return in.minus(normal.scale(2.0*length));
    }
    // TODO doc
    Optional<Pair<Vector3,Ray>> scatter(Ray rayIn, HitRecord record);
}
