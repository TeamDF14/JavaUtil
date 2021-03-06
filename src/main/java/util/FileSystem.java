package util;

import com.sun.javafx.util.Utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class FileSystem {


    /**
     * <p>Returns the delimiting string of hierarchies, depending on the OS. </p>
     * <p>If the current OS is Windows, <i>\\</i> is returned.</p>
     * <p>If the current OS is other than Windows, <i>//</i> is returned.</p>
     * @return The delimiter.
     */
    public static java.lang.String getPathDelimiter(){
        if (Utils.isWindows()){
            return "\\";
        }
        else{
            return "//";
        }
    }

    /**
     * <p>Changes the creation date of the given file.</p>
     * <p>The file must exist, else no change will be applied.</p>
     * @param creationDate The date that will be set as the new creation date
     * @param file The file to apply the changes to
     * @return True if the creation date was changed, false if not.
     */
    public static boolean setCreationDate(final File file, final Date creationDate){
        if (!bCheckFileExists(file))
            return false;
        BasicFileAttributeView attributes = Files.getFileAttributeView(Paths.get(file.getAbsolutePath()), BasicFileAttributeView.class);
        FileTime time = FileTime.fromMillis(creationDate.getTime());
        try {
            attributes.setTimes(time, time, time);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    /**
     * <p>Returns the creation date of the given file.</p>
     * <p>The file must exist, else null will be returned.</p>
     * @param file The file to extract the creation date from.
     * @return True if the creation date could be determined, false false if not.
     */
    public static Date getCreationDate(final File file){
        if (!bCheckFileExists(file))
            return null;
        BasicFileAttributeView attributes = Files.getFileAttributeView(Paths.get(file.getAbsolutePath()), BasicFileAttributeView.class);
        FileTime creationDate = null;
        try {
            creationDate = attributes.readAttributes().creationTime();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Date(creationDate.toMillis());
    }

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

    /**
     * <p>Checks if a given directory already exists.</p>
     * @return True if the directory exists and is no file, false otherwise.
     */
    public static boolean bCheckDirectoryExists(final File file){
        if(file == null) {
            return false;
        }
        return (file.exists() && file.isDirectory() && !file.isFile());
    }

    /**
     * <p>Get all the files in the given directory.</p>
     * <p>When an encoding is provided, only these files that end on this value are recognized.</p>
     * <p>When no encoding is provided, return all files in the directory.</p>
     * @param path The path where the files are located at. If it is invalid or not accessible, null is returned.
     * @param encoding The encoding (with the dot, case insensitive), e.g. '.xml'.
     * @return A list of files.
     */
    public static File[] getFiles(final File path, final java.lang.String encoding){

        File[] files;

        if (path == null || !path.exists() || !path.canRead()){
            return null;
        }

        if(util.String.isEmpty(encoding)){
            files = path.listFiles(new java.io.FileFilter() {
                @Override
                public boolean accept(File file) {
                    return true;
                }
            });
        }
        else{
            files = path.listFiles(new java.io.FilenameFilter() {
                @Override
                public boolean accept(File file, java.lang.String s) {
                    return s.toLowerCase().endsWith(encoding);
                }
            });

        }
        return files;
    }


    /**
     * <p>Checks if the given file was created <i>before</i> the given date.</p>
     * <p>The hours, minutes and seconds of the dates are ignored.</p>
     * <p><b>Example:</b></p>
     * <p>When today is the 10.10.1998 and the file was created on 02.10.1998, true will be returned. </p>
     * @param f The file to check
     * @return True if the creation date is older than the given date, false if not or the file does not exist.
     */
    public static boolean wasCreatedBefore(final File f, final Date date){
        if (f == null){
            return false;
        }
        BasicFileAttributes attr = null;
        try {
            attr = Files.readAttributes(f.toPath(),
                    BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LocalDateTime ldt = attr.creationTime()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        Date localDate = Date.from(ldt.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        return util.Date.bIsEarlier(localDate, date);
    }

    /**
     * <p>Checks if the given file was modified <i>before</i> the given date.</p>
     * <p>The hours, minutes and seconds of the dates are ignored.</p>
     * <p><b>Example:</b></p>
     * <p>When today is the 10.10.1998 and the file was modified on 02.10.1998, true will be returned. </p>
     * @param f The file to check
     * @return True if the modified date is older than the given date, false if not or the file does not exist.
     */
    public static boolean wasModifiedBefore(final File f, final Date date){
        if (f == null){
            return false;
        }
        BasicFileAttributes attr = null;
        try {
            attr = Files.readAttributes(f.toPath(),
                    BasicFileAttributes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LocalDateTime ldt = attr.lastModifiedTime()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        Date localDate = Date.from(ldt.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
        return util.Date.bIsEarlier(localDate, date);
    }

    /**
     * <p>Creates the given directory (including all necessary subdirectories)</p>
     * <p>In addition, the user permissions in order to write and modify the directories are checked.</p>
     * ToDo: Test case
     * @param dir The directory to create.
     * @return True if the directory was created, false if there was an error or missing permissions.
     */
    public static boolean createDirectory(File dir) {
        return dir.mkdirs() && dir.canWrite();
    }

    /**
     * <p>Deletes the given directory. </p>
     * ToDo: Test case
     * @param dir The directory to delete.
     * @return True if the directory was deleted, false if there was an error.
     */
    public static boolean deleteDirectory(final File dir){
        if (util.FileSystem.bCheckDirectoryExists(dir)){
            try{
                dir.delete();
            }
            catch (Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * <p>Deletes the given file. </p>
     * ToDo: Test case
     * @param file The file to delete.
     * @return True if the file was deleted, false if there was an error.
     */
    public static boolean deleteFile(final File file){
        if (util.FileSystem.bCheckFileExists(file)){
            try{
                file.delete();
            }
            catch (Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }
}
