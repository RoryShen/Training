package test.ckt.junit4;

import android.os.Environment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class DataDrivenTestsWithSpreadsheetTest {

    private double a;
    private double b;
    private double aTimesB;

    public DataDrivenTestsWithSpreadsheetTest(double a, double b, double aTimesB) {
        super();
        this.a = a;
        this.b = b;
        this.aTimesB = aTimesB;
    }

    @Parameterized.Parameters
    public static Collection spreadsheetData() throws IOException {
        //定义了文件的地址
        InputStream spreadsheet = new FileInputStream(Environment.getExternalStorageDirectory().getPath() + "/aTimesB.xls");
        //获取到一个存有excel数据的集合数组
        return new SpreadsheetData(spreadsheet).getData();

    }

    @Test
    public void shouldCalculateATimesB() {
        double calculatedValue = a * b;
        assertThat(calculatedValue, is(aTimesB));


    }
}