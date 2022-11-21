package helpers;

import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.IOException;

import static java.lang.String.format;
import static org.junit.Assert.assertNotNull;

public class Cleaner {
    private static final String fileSeparator = File.separator;

    public static void main(String[] args) {
        clearReports();
        clearDownloads();
    }

    private static void clearReports() {
        clean("build" + fileSeparator + "downloads");
    }

    private static void clearDownloads() {
        clean("build" + fileSeparator + "reports" + fileSeparator + "tests");
    }

    private static void clean(String path) {
        new File(path).mkdirs();
        File[] elementsArray = new File(path).listFiles();
        assertNotNull(format("Необходимо создать папку %s", path), elementsArray);
        for (File element : elementsArray) {
            try {
                if (element.isFile()) {
                    FileUtils.forceDelete(element);
                } else {
                    FileUtils.deleteDirectory(element);
                }
            } catch (IOException | IllegalArgumentException e) {
                //ignored
            }
        }
    }

}

