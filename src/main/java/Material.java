import javafx.util.Pair;

import java.util.Optional;

public interface Material {

    static Vector3 reflect(Vector3 in,Vector3 normal){
        double length = in.dot(normal);
        return in.minus(normal.scale(2.0*length));
    }

    // TODO doc
    // niOverNt 入射光线的折射率比折射光线处的折射率
    static Optional<Vector3> refract(Vector3 in,Vector3 normal,double niOverNt){
        Vector3 uv = in.makeUnitVector();
        double dt = uv.dot(normal);
        double discriminant = 1.0 - niOverNt*niOverNt*(1-dt*dt);
        if(discriminant > 0){
            Vector3 refracted = uv.minus(normal.scale(dt)).scale(niOverNt).minus(normal.scale(Math.sqrt(discriminant)));
            return Optional.of(refracted);
        }
        return Optional.empty();
    }

    // TODO doc
    Optional<Pair<Vector3,Ray>> scatter(Ray rayIn, HitRecord record);
}
