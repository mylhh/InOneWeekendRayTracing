import javafx.util.Pair;

import java.util.Optional;

public class Dielectric implements Material {

    double refIdx;
    public Dielectric(double refIdx){
        this.refIdx = refIdx;
    }
    @Override
    public Optional<Pair<Vector3, Ray>> scatter(Ray rayIn, HitRecord record) {
        double reflectProb;
        double cosine;
        double niOverNt;
        Ray scattered;
        Vector3 outwardNormal;
        Vector3 reflected = Material.reflect(rayIn.getDirection(),record.normal);
        Vector3 attenuation = new Vector3(1.0,1.0,1.0);
        Vector3 refracted;
        boolean existenceRefraction = rayIn.getDirection().dot(record.normal) > 0;
        if(existenceRefraction){
            outwardNormal = record.normal.scale(-1.0);
            niOverNt = refIdx;
            cosine = refIdx * rayIn.getDirection().dot(record.normal)/rayIn.getDirection().length();
        }else{
            outwardNormal = record.normal;
            niOverNt = 1.0 / refIdx;
            cosine = -1.0 * rayIn.getDirection().dot(record.normal)/rayIn.getDirection().length();
        }
        Optional<Vector3> optionalRefracted = Material.refract(rayIn.getDirection(),outwardNormal,niOverNt);
        if(optionalRefracted.isPresent()){
            reflectProb = schlick(cosine,refIdx);
        }else{
            scattered = new Ray(record.hitPoint,reflected);
            reflectProb = 1.0;
        }
        if(Math.random() < reflectProb){
            scattered = new Ray(record.hitPoint,reflected);
        }else{
            refracted = optionalRefracted.get();
            scattered = new Ray(record.hitPoint,refracted);
        }
        return Optional.of(new Pair<>(attenuation,scattered));
    }

    // TODO doc
    double schlick(double cosine,double refIdx){
        double r0 = (1.0 - refIdx) / (1+refIdx);
        r0 = r0*r0;
        return r0 + (1-r0)*Math.pow((1.0 - cosine),5);
    }
}
