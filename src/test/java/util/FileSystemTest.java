package util;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.String;

import static org.junit.Assert.*;

public class FileSystemTest {

    @Test
    public void bCheckFileExists() {

        String currentUserPath = System.getProperty("user.dir");
        String fileName = "Test.txt";
        File utilTestFile = new File(currentUserPath + "/" + fileName);
        File wrongFile = new File(currentUserPath + "\\wrongFile.txt");

        // Create file
        try {
            utilTestFile.getParentFile().mkdirs();
            utilTestFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(util.FileSystem.bCheckFileExists(utilTestFile));
        assertFalse(util.FileSystem.bCheckFileExists(wrongFile));
        assertFalse(util.FileSystem.bCheckFileExists(null));

        // Delete created file
        utilTestFile.delete();
    }
}