package test.ckt.junit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Rory on 2017/08/24   .
 */
@RunWith(Parameterized.class)
public class Pa {
    //定义数据源
    // @Parameterized.Parameters
    public static String[] data1() {

        return new String[]{"Hello", "World", "你好啊"};
    }

    // @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"H", "W", "你好啊"}, {"w", "men", "你好啊"}, {"2", "1"}, {"3", "02"}, {"4", "3"}, {"5", "5"}, {"6", "8"}
        });
    }

    @Parameterized.Parameters
    public static Collection<Object[]> database() {
        Object[][] data = new Object[][]{
                {"Hello", "World", "HelloWorld"},
                {"Base", "Tools", "BaseTools"},
                {"What", "This", "What This"}
        };
        return Arrays.asList(data);
    }

    //定义传参名字

    private String za;
    private String zb;
    private String zc;

    public Pa(String a, String b, String c) {
        za = a;
        zb = b;
        zc = c;

    }


    @Test
    public void print() {
        Assert.assertEquals("The name does not match", za+zb, zc);


    }
}
