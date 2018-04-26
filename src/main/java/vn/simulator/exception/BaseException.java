package vn.simulator.exception;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -1282169081085899678L;

    public BaseException(String msg){
        super(msg);
    }

    public BaseException(String msg, Throwable cause){
        super(msg, cause);
    }

}
