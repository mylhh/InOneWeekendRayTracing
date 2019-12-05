import java.util.Optional;

/**
 * The {@code Render} class render scenes and objects.
 */
public class Render {
    Vector3 startColor;
    Vector3 endColor;

    public Render() {
        // default color
        this.startColor = new Vector3(1.0, 1.0, 1.0);
        this.endColor = new Vector3(0.5, 0.7, 1.0);
    }

    /**
     * Using linear interpolation generate background,default colors are
     * white(t=0) and bule(t=1).Reference this function:
     * {@code blended_value=(1-t)start_value+t*end_value}.
     *
     * @param ray from origin(0,0,0) to screen flat
     * @return color at the intersection of a ray and a plane
     */
    public Vector3 backgroundColor(Ray ray){
        Vector3 unitDrection = ray.getDirection().makeUnitVector();
        double t = (unitDrection.y()+1.0) / 2;
        return this.startColor.scale(1.0 - t).plus(this.endColor.scale(t));
    }

    /**
     * In the scene base the method {@link Render#backgroundColor(Ray)} add a Sphere.
     *
     * @param ray from origin(0,0,0) to screen flat
     * @return color at the intersection of a ray with object
     */
    public Vector3 addSphere(Ray ray){
        if(hitSphere(new Vector3(0,0,1),0.5,ray)){
            return new Vector3(1,0,0);
        }
        Vector3 unitDrection = ray.getDirection().makeUnitVector();
        double t = (unitDrection.y()+1.0) / 2;
        return this.startColor.scale(1.0 - t).plus(this.endColor.scale(t));
    }

    /**
     * Base the method {@link Render#addSphere(Ray)} we get a red sphere
     * in the scene,now we compute and visualize normals on surface.
     *
     * @param ray from origin(0,0,0) to screen flat
     * @return color correspond normal vector
     */
    public Vector3 visualizeSphereNormals(Ray ray){
        double t = hitPointOnSphere(new Vector3(0.0,0.0,-1.0),0.5,ray);
        if(t > 0.0){
            Vector3 normal = ray.pointAtParameter(t).minus(new Vector3(0.0,0.0,-1.0)).makeUnitVector();
            return new Vector3(normal.x()+1,normal.y()+1,normal.z()+1).scale(0.5);
        }
        Vector3 unitDrection = ray.getDirection().makeUnitVector();
        t = (unitDrection.y()+1.0) / 2;
        return this.startColor.scale(1.0 - t).plus(this.endColor.scale(t));
    }

    /**
     * Base the method {@link Render#visualizeSphereNormals(Ray)} we add other sphere in scene.
     *
     * @return color at the intersection of a ray with objects
     */
    public Vector3 addMutipleObject(Ray ray,Hitable world){
        Optional<HitRecord> optionalHitRecord = world.hit(ray,0.0,Double.MAX_VALUE);
        if(optionalHitRecord.isPresent()){
            HitRecord record = optionalHitRecord.get();
            return new Vector3(record.normal.x()+1,record.normal.y()+1,record.normal.z()+1).scale(0.5);
        }
        return backgroundColor(ray);
    }
    /**
     * Set up a sphere in 3D space,and detecting whether the ray intersect with sphere.
     *
     * @param center sphere center
     * @param radius sphere radius
     * @param ray from view point to screen plane
     * @return {@code true} when ray intersects the sphere,otherwise return {@code false}
     */
    public boolean hitSphere(Vector3 center, double radius, Ray ray){
        Vector3 A = ray.getOrigin();
        Vector3 B = ray.getDirection();
        double a = B.dot(B);
        double b = B.scale(2.0).dot(A.minus(center));
        double c = A.minus(center).dot(A.minus(center)) - radius*radius;
        double discriminant = b*b - 4*a*c;
        return discriminant > 0;
    }

    /**
     * Return parameter {@code t} on the ray {@code P(t)},when ray intersects the sphere.
     * Otherwise,retuen {@code -1}.
     *
     * @param center sphere center
     * @param radius sphere radius
     * @param ray from view point to screen plane
     * @return {@code true} when ray intersects the sphere,otherwise,retuen {@code -1}
     */
    public double hitPointOnSphere(Vector3 center, double radius, Ray ray){
        Vector3 A = ray.getOrigin();
        Vector3 B = ray.getDirection();
        double a = B.dot(B);
        double b = B.scale(2.0).dot(A.minus(center));
        double c = A.minus(center).dot(A.minus(center)) - radius*radius;
        double discriminant = b*b - 4*a*c;
        if(discriminant < 0)
            return -1.0;
        return (-b - Math.sqrt(discriminant)) / (2.0*a);
    }

}
