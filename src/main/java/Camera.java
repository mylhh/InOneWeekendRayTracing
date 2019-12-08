
public class Camera {
    Vector3 lowerLeftPoint;
    Vector3 horizontal;
    Vector3 vertical;
    Vector3 origin;
    double lensRadius;
    Vector3 u,v,w;

    public Camera(){
        this.lowerLeftPoint = new Vector3(-2.0,-1.0,-1.0);
        this.horizontal = new Vector3(4.0,0.0,0.0);
        this.vertical = new Vector3(0.0,2.0,0.0);
        this.origin = Vector3.getZeroVector();
    }
    // FOV(field of view)
    public Camera(Vector3 lookfrom,Vector3 lookat,Vector3 viewUp,double verticalFOV,double aspect){

        double theta = verticalFOV * Math.PI / 180; // 弧度
        double halfHeight = Math.tan(theta / 2);
        double halfWidth = aspect * halfHeight;
        this.origin = lookfrom;
        // 局部坐标
        this.w = lookfrom.minus(lookat).makeUnitVector();
        this.u = viewUp.cross(w);
        this.v = w.cross(u);
        this.lowerLeftPoint = origin.minus(u.scale(halfWidth)).minus(v.scale(halfHeight)).minus(w);
        this.horizontal = u.scale(2 * halfWidth);
        this.vertical = v .scale(2 * halfHeight);
    }

    // defocus blur
    public Camera(Vector3 lookfrom,Vector3 lookat,Vector3 viewUp,double verticalFOV,double aspect,double aperture,double focusDist){
        this.lensRadius = aperture / 2;
        double theta = verticalFOV * Math.PI / 180; // 弧度
        double halfHeight = Math.tan(theta / 2);
        double halfWidth = aspect * halfHeight;
        this.origin = lookfrom;
        // 局部坐标
        w = lookfrom.minus(lookat).makeUnitVector();
        u = viewUp.cross(w);
        v = w.cross(u);
        this.lowerLeftPoint = origin.minus(u.scale(halfWidth*focusDist)).minus(v.scale(halfHeight*focusDist)).minus(w.scale(focusDist));
        this.horizontal = this.u.scale(2 * halfWidth*focusDist);
        this.vertical = v .scale(2 * halfHeight*focusDist);
    }

    Vector3 randomInUnitDisk(){
        Vector3 result;
        Vector3 q = new Vector3(1,1,0);
        do {
            result = new Vector3(Math.random(),Math.random(),0).scale(2.0).minus(q);
        }while (result.squaredLength() >= 1.0);
        return result;
    }

    public Ray getRay(double u,double v){
        return new Ray(origin,lowerLeftPoint.plus(horizontal.scale(u).plus(vertical.scale(v))).minus(origin));
    }

    public Ray getRayDefocus(double s,double t){
        Vector3 rd =  randomInUnitDisk().scale(lensRadius);
        Vector3 offset = u.scale(rd.x()).plus(v.scale(rd.y()));
        return new Ray(origin.plus(offset),lowerLeftPoint.plus(horizontal.scale(s).plus(vertical.scale(t))).minus(origin.plus(offset)));
    }


}
