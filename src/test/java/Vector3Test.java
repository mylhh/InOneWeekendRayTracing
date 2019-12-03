import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Vector3Test {
    private Vector3 v1;
    private Vector3 v2;

    @BeforeEach
    public void create(){
        v1 = new Vector3(1.0,2.0,3.0);
        v2 = new Vector3(5.0,2.0,4.0);
    }

    @Test
    @DisplayName("不同向量")
    public void testNotEqual(){
        Assertions.assertNotEquals(v1,v2);
    }

    @Test
    @DisplayName("同一向量")
    public void testEqual(){
        Assertions.assertEquals(v1,new Vector3(1.0,2.0,3.0));
    }

    @Test
    @DisplayName("加法")
    public void testPlus(){
        Assertions.assertEquals(v1.plus(v2),new Vector3(1.0+5.0,2.0+2.0,3.0+4.0));
    }

    @Test
    @DisplayName("减法")
    public void testMinus(){
        Assertions.assertEquals(v1.minus(v2),new Vector3(1.0-5.0,2.0-2.0,3.0-4.0));
    }

    @Test
    @DisplayName("数乘")
    public void testScale(){
        Assertions.assertEquals(v1.scale(1.6),new Vector3(1.0*1.6,2.0*1.6,3.0*1.6));
    }

    @Test
    @DisplayName("模长")
    public void testLength(){
        Assertions.assertEquals(v1.length(),Math.sqrt(1.0*1.0+2.0*2.0+3.0*3.0));
        Assertions.assertEquals(v2.length(),Math.sqrt(5.0*5.0+2.0*2.0+4.0*4.0));
    }

    @Test
    @DisplayName("初始向量不是3维时应该报错")
    public void testDimensionNotThree(){
        Assertions.assertThrows(IllegalArgumentException.class,()->new Vector3(1.0,2.0));
    }

    @Test
    @DisplayName("零向量")
    public void testZeroVector(){
        Vector3 zero = new Vector3(0.0,0.0,0.0);
        Assertions.assertThrows(ArithmeticException.class,()->zero.makeUnitVector());
        Assertions.assertEquals(zero.dot(v1),0.0);
        Assertions.assertEquals(v1.dot(zero),0.0);
        Assertions.assertEquals(v2.cross(zero),zero);
        Assertions.assertEquals(zero.cross(v2),zero);
    }

    @Test
    @DisplayName("叉乘")
    public void testCross(){
        double u1 = 1.0;
        double u2 = 2.0;
        double u3 = 3.0;
        double v1 = 5.0;
        double v2 = 2.0;
        double v3 = 4.0;
        Vector3 u = new Vector3(u1,u2,u3);
        Vector3 v = new Vector3(v1,v2,v3);
        double x = u2*v3 - u3*v2;
        double y = u3*v1 - u1*v3;
        double z = u1*v2 - u2*v1;
        Vector3 result = u.cross(v);
        Assertions.assertEquals(new Vector3(x,y,z),result);
        Assertions.assertEquals(Vector3.getZeroVector(),v.cross(v.scale(2.0)));
    }


}
