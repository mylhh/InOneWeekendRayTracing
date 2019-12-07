
public class Camera {
    Vector3 lowerLeftPoint;
    Vector3 horizontal;
    Vector3 vertical;
    Vector3 origin;

    public Camera(){
        this.lowerLeftPoint = new Vector3(-2.0,-1.0,-1.0);
        this.horizontal = new Vector3(4.0,0.0,0.0);
        this.vertical = new Vector3(0.0,2.0,0.0);
        this.origin = Vector3.getZeroVector();
    }
    // FOV(field of view)
    public Camera(Vector3 lookfrom,Vector3 lookat,Vector3 viewUp,double verticalFOV,double aspect){
        Vector3 u,v,w;
        double theta = verticalFOV * Math.PI / 180; // 弧度
        double halfHeight = Math.tan(theta / 2);
        double halfWidth = aspect * halfHeight;
        this.origin = lookfrom;
        // 局部坐标
        w = lookfrom.minus(lookat).makeUnitVector();
        u = viewUp.cross(w);
        v = w.cross(u);
        this.lowerLeftPoint = origin.minus(u.scale(halfWidth)).minus(v.scale(halfHeight)).minus(w);
        this.horizontal = u.scale(2 * halfWidth);
        this.vertical = v .scale(2 * halfHeight);
    }

    public Ray getRay(double u,double v){
        return new Ray(origin,lowerLeftPoint.plus(horizontal.scale(u).plus(vertical.scale(v))).minus(origin));
    }
}
