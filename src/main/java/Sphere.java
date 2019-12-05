import java.util.Optional;

/**
 * Represent a Sphere in ray tracing scene.Implements interface {@link Hitable}.
 */
public class Sphere implements Hitable{
    private Vector3 center;
    private double radius;

    public Sphere(Vector3 center,double radius){
        this.center = center;
        this.radius = radius;
    }

    /**
     * Return whether the ray intersects the object.
     *
     * @param ray    from view point to screen plane
     * @param tMin   min value of parameter {@code t} range
     * @param tMax   max value of parameter {@code t} range
     * @return {@code Optional<HitRecord>} when ray intersects the sphere will contain a HitRecord
     */
    @Override
    public Optional<HitRecord> hit(Ray ray, double tMin, double tMax) {
        Vector3 A = ray.getOrigin();
        Vector3 B = ray.getDirection();
        double a = B.dot(B);
        double b = B.scale(2.0).dot(A.minus(center));
        double c = A.minus(center).dot(A.minus(center)) - radius*radius;
        double discriminant = b*b - 4*a*c;

        if(discriminant > 0){
            double t = (-b - Math.sqrt(discriminant)) / (2.0*a);
            if(t < tMax && t > tMin){
                HitRecord record = new HitRecord();
                record.t = t;
                record.hitPoint = ray.pointAtParameter(t);
                record.normal = record.hitPoint.minus(center).makeUnitVector();
                Optional<HitRecord> optionalHitRecord = Optional.of(record);
                return optionalHitRecord;
            }
            t = (-b + Math.sqrt(discriminant)) / (2.0*a);
            if(t < tMax && t > tMin){
                HitRecord record = new HitRecord();
                record.t = t;
                record.hitPoint = ray.pointAtParameter(t);
                record.normal = record.hitPoint.minus(center).makeUnitVector();
                Optional<HitRecord> optionalHitRecord = Optional.of(record);
                return optionalHitRecord;
            }
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
