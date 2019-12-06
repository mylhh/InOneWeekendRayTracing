import javafx.util.Pair;

import java.util.Optional;

/**
 * Diffuse materials.
 */
public class Lambertian implements Material{

    Vector3 albedo;

    public Lambertian(Vector3 albedo){
        this.albedo = albedo;
    }

    @Override
    public Optional<Pair<Vector3,Ray>> scatter(Ray rayIn, HitRecord record) {
        Vector3 target = record.hitPoint.plus(record.normal).plus(Render.randomInUnitSphere());
        Ray scattered = new Ray(record.hitPoint,target.minus(record.hitPoint));
        Pair<Vector3,Ray> pair = new Pair<Vector3,Ray>(albedo,scattered);
        return Optional.of(pair);
    }
}
