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
     * @param record hit point information
     * @return {@code true} when ray intersects the sphere,otherwise return {@code false}
     */
    @Override
    public boolean hit(Ray ray, double tMin, double tMax, HitRecord record) {
        Vector3 A = ray.getOrigin();
        Vector3 B = ray.getDirection();
        double a = B.dot(B);
        double b = B.scale(2.0).dot(A.minus(center));
        double c = A.minus(center).dot(A.minus(center)) - radius*radius;
        double discriminant = b*b - 4*a*c;
        if(discriminant > 0){
            double t = (-b - Math.sqrt(discriminant)) / (2.0*a);
            if(t < tMax && t > tMin){
                record.t = t;
                record.hitPoint = ray.pointAtParameter(t);
                record.normal = record.hitPoint.minus(center).scale(1/radius);
                return true;
            }
            t = (-b + Math.sqrt(discriminant)) / (2.0*a);
            if(t < tMax && t > tMin){
                record.t = t;
                record.hitPoint = ray.pointAtParameter(t);
                record.normal = record.hitPoint.minus(center).scale(1/radius);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
