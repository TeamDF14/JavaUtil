package util;

import java.io.File;

public class FileSystem {

    /**
     * <p>Checks if a given file already exists.</p>
     * @return True if the file exists and is no directory, false otherwise.
     */
    public static boolean bCheckFileExists(final File file){
        if(file == null) {
            return false;
        }

        return (file.exists() && !file.isDirectory());
    }

}
