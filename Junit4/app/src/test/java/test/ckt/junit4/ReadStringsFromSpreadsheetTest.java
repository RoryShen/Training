package test.ckt.junit4;



import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class ReadStringsFromSpreadsheetTest {

    private Collection<Object[]> spreadsheetData;
    private Object[] secondRow;
    
    @Before
    public void loadTestSpreadsheet() throws IOException {
        InputStream excelFile = new FileInputStream(new File("F:\\06.SampleCode\\wakaleo-jxlunit-9a4cec950bdf\\src\\test\\resources\\strings-spreadsheet.xls"));
        spreadsheetData = new SpreadsheetData(excelFile).getData();                
        Iterator<Object[]> iterator = spreadsheetData.iterator();
        iterator.next();
        secondRow = (Object[]) iterator.next();
    }

    @Test
    public void stringCellsShouldBeReturnedAsStrings() throws IOException {
        Object cell = secondRow[0];
        assertThat(cell, CoreMatchers.<Object>is(String.class));
        assertThat((String) cell, is("michael"));
    }

    @Test
    public void formattedStringCellsShouldBeReturnedAsStrings() throws IOException {
        Object cell = secondRow[1];
        assertThat(cell, CoreMatchers.<Object>is(String.class));
        assertThat((String) cell, is("smith"));
    }

    @Test
    public void emptyStringCellsShouldBeReturnedAsNull() throws IOException {
        Object cell = secondRow[2];
        assertThat(cell, is(nullValue()));
    }
    
    @Test
    public void calculatedStringCellsShouldBeReturnedAsStrings() throws IOException {
        Object cell = secondRow[3];
        assertThat(cell, CoreMatchers.<Object>is(String.class));
        assertThat((String) cell, is("michael smith"));
    }    
}
