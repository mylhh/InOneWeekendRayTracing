import javafx.util.Pair;

import java.util.Optional;

public class Metal implements Material {

    double fuzz;
    Vector3 albedo;

    public Metal(Vector3 albedo,double fuzz){
        this.albedo = albedo;
        this.fuzz = fuzz < 1?fuzz:1;
    }

    @Override
    public Optional<Pair<Vector3, Ray>> scatter(Ray rayIn, HitRecord record) {
        Vector3 reflected = Material.reflect(rayIn.getDirection().makeUnitVector(),record.normal);
        Ray scattered = new Ray(record.hitPoint,reflected.plus(Render.randomInUnitSphere().scale(fuzz)));
        if(scattered.getDirection().dot(record.normal) > 0){
            Pair<Vector3, Ray> pair = new Pair<>(albedo,scattered);
            return Optional.of(pair);
        }
        return Optional.empty();
    }
}
