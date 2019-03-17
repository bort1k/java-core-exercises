package ua.procamp;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * {@link FileReaders} privides an API that allow to read whole file into a {@link String} by file name.
 */
public class FileReaders {

    /**
     * Returns a {@link String} that contains whole text from the file specified by name.
     *
     * @param fileName a name of a text file
     * @return string that holds whole file content
     */
    public static String readWholeFile(String fileName) {
        if(fileName == null){
            return null;
        }

        URL url = FileReaders.class.getClassLoader().getResource(fileName);
        String path = url.getPath() ; // dont know how to make right

        if(path.isEmpty()){
            return null;
        }

        String res = "";

        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            res = stream.collect(Collectors.joining("\n"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
