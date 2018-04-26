package vn.simulator.exception;

public class CustomException {

    public static class FileException extends BaseException {
        public FileException(String msg) {
            super(msg);
        }

        public FileException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }

    public static class ConfigException extends BaseException {
        public ConfigException(String msg) {
            super(msg);
        }

        public ConfigException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }

    public static class JsonException extends BaseException {
        public JsonException(String msg) {
            super(msg);
        }

        public JsonException(String msg, Throwable cause) {
            super(msg, cause);
        }
    }


}
