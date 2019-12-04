/**
 *
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

    public Vector3 addSphere(Ray ray){
        if(hitShere(new Vector3(0,0,1),0.5,ray)){
            return new Vector3(1,0,0);
        }
        Vector3 unitDrection = ray.getDirection().makeUnitVector();
        double t = (unitDrection.y()+1.0) / 2;
        return this.startColor.scale(1.0 - t).plus(this.endColor.scale(t));
    }

    public Vector3 visualizeSphereNormals(Ray ray){
        double t = hitPointOnShere(new Vector3(0.0,0.0,-1.0),0.5,ray);
        if(t > 0.0){
            Vector3 normal = ray.pointAtParameter(t).minus(new Vector3(0.0,0.0,-1.0)).makeUnitVector();
            return new Vector3(normal.x()+1,normal.y()+1,normal.z()+1).scale(0.5);
        }
        Vector3 unitDrection = ray.getDirection().makeUnitVector();
        t = (unitDrection.y()+1.0) / 2;
        return this.startColor.scale(1.0 - t).plus(this.endColor.scale(t));
    }

    /**
     * Set up a sphere in 3D space,and detecting whether the ray intersect with sphere.
     *
     * @param center sphere center
     * @param radius sphere radius
     * @param ray from view point to screen plane
     * @return {@code true} when ray intersects the sphere
     */
    public boolean hitShere(Vector3 center,double radius,Ray ray){
        Vector3 A = ray.getOrigin();
        Vector3 B = ray.getDirection();
        double a = B.dot(B);
        double b = B.scale(2.0).dot(A.minus(center));
        double c = A.minus(center).dot(A.minus(center)) - radius*radius;
        double discriminant = b*b - 4*a*c;
        return discriminant > 0;
    }

    public double hitPointOnShere(Vector3 center,double radius,Ray ray){
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
