package vn.simulator.common.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vn.simulator.exception.CustomException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {

    private static final Logger LOGGER = LogManager.getLogger(FileHelper.class);

    public static String readFile(String url, String... more) throws CustomException.FileException {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(url, more));
            return new String(encoded, Charset.defaultCharset());
        } catch (IOException e) {
            LOGGER.error("Not found file {}", url);
            throw new CustomException.FileException("Not found file");
        }
    }

}
