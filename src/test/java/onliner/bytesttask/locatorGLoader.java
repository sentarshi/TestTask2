package onliner.bytesttask;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class locatorGLoader {
    private final static String FILE_NAME = "src/test/resources/TestData.json";
    private final Gson GSON = new Gson();
    private HashMap<String, String> locators = new HashMap<>();


    public locatorGLoader read() throws IOException {
        this.locators = GSON.fromJson(fileReader(), locators.getClass());
        return this;
    }
    private String fileReader() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(FILE_NAME))) {
            return stream.collect(Collectors.joining("\r\n"));
        }}
    public String getLocator(String locator) throws CloneNotSupportedException {
        return locators.get(locator);
    }
}
