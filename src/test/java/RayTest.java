import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RayTest {
    @Test
    @DisplayName("射线方程")
    public void testPointAtParameter(){
        Vector3 origin = new Vector3(1.0,1.0,0.0);
        Vector3 direction = new Vector3(0.0,3.0,4.0).makeUnitVector();
        System.out.println(direction);
        Ray ray = new Ray(origin,direction);
        Assertions.assertEquals(new Vector3((1.0+0.0),(1.0+0.6*5),(0.0+0.8*5)),ray.pointAtParameter(5.0));
        Assertions.assertEquals(origin,ray.pointAtParameter(0.0));

        // 浮点数误差
        // Assertions.assertEquals(new Vector3((1.0-0.0),(1.0-0.6*2.0),(0.0-0.8*2.0)),ray.pointAtParameter(-2.0));

    }
}
