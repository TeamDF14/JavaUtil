package util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.String;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class FileSystemTest {

    private File testFile;
    private String baseDir = System.getProperty("user.dir");
    private String subDir = "subDir";
    private String fileName = "test.txt";

    @Before
    public void setUp() {
        testFile = new File(baseDir + "\\" +  subDir + "\\" + fileName);

        // Create directories and file
        try {
            testFile.getParentFile().mkdirs();
            testFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void cleanUp(){
        // Delete created directory and file
        testFile.delete();
        new File(subDir).delete();
    }

    @Test
    public void getFiles() {

        File[] files;
        assertNull(FileSystem.getFiles(null, null));

        files = FileSystem.getFiles(testFile.getParentFile(), null);
        assertNotNull(files);
        assertEquals(1, files.length);
        //assertSame(files[0], testFile);
    }

    @Test
    public void wasCreatedBefore(){

        assertTrue(FileSystem.wasCreatedBefore(testFile, new Date()));

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) - 1);
        assertFalse(FileSystem.wasCreatedBefore(testFile, cal.getTime()));

        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 5);
        assertTrue(FileSystem.wasCreatedBefore(testFile, cal.getTime()));

        cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) - 1000);
        assertFalse(FileSystem.wasCreatedBefore(testFile, cal.getTime()));
    }

    @Test
    public void bCheckFileExists() {

        File wrongFile = new File(baseDir + "\\wrongFile.txt");

        assertTrue(util.FileSystem.bCheckFileExists(testFile));
        assertFalse(util.FileSystem.bCheckFileExists(wrongFile));
        assertFalse(util.FileSystem.bCheckFileExists(null));

    }
}