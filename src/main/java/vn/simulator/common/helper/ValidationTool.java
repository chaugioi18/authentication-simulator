package vn.simulator.common.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.simulator.common.helper.annotation.*;
import vn.simulator.exception.CustomException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by MILA on 2/24/2017.
 */
public class ValidationTool implements IValidationTool {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean checkValidPhoneNumber(String phone) {
        if (phone == null) {
            return false;
        }
        if (!phone.trim().matches("\\d{10,13}")) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkValidEmailAddress(String email) {
        try {
            /*String patternEmail = "([^.@][a-z0-9A-Z!#$%&'*-+/=?^_`{|}~.][^.]{3,})@([^.][a-z0-9A-Z:.\\-]{5,}[^.])";
            if(!email.matches(patternEmail)){
                throw new CommonExceptions.BadRequest("email not validate");
            }*/
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }

    @Override
    public IValidationTool isValidPhoneNumber(String phone) {
        if (phone == null) {
            throw new CustomException.ValidationError("Phone invalid");
        }
        if (!phone.trim().matches("\\d{10,13}")) {
            throw new CustomException.ValidationError("Phone invalid");
        }
        return this;
    }

    @Override
    public IValidationTool isValidEmailAddress(String email) throws CustomException.ValidationError {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            throw new CustomException.ValidationError("Email invalid");
        }
        return this;
    }

    @Override
    public IValidationTool isValidEmailOrPhone(String input) {
        if (input.trim().matches("\\d{10,13}")) {
            return this;
        }
        try {
            InternetAddress emailAddr = new InternetAddress(input);
            emailAddr.validate();
            return this;
        } catch (AddressException e) {
            throw new CustomException.ValidationError("Phone and Email invalid");
        }

    }

    @Override
    public IValidationTool checkNullAll(Object object) {
        if (object == null) {
            throw new CustomException.ValidationError("Object is null");
        }
        for (Field f : object.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                if (f.get(object) == null) {
                    throw new CustomException.ValidationError("Properties " + f.getName() + " is null");
                }
            } catch (IllegalAccessException e) {
                LOGGER.error("Error with accessible object cause by {} ", e.getMessage());
            }
        }
        return this;
    }

    //check object null or properties in black list null
    @Override
    public IValidationTool checkNullBlack(Object object, List<String> blackList) {
        if (object == null) {
            throw new CustomException.ValidationError("Object is null");
        }
        for (Field f : object.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                if (f.get(object) == null && blackList.contains(f.getName())) {
                    throw new CustomException.ValidationError("Properties " + f.getName() + " is null");
                }
            } catch (IllegalAccessException e) {
                LOGGER.error("Error with accessible object cause by {} ", e.getMessage());
            }
        }
        return this;
    }

    //check object null or properties out of white list null
    @Override
    public IValidationTool checkNullWhite(Object object, List<String> whiteList) {
        if (object == null) {
            throw new CustomException.ValidationError("Object is null");
        }
        for (Field f : object.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                if (f.get(object) == null && !whiteList.contains(f.getName())) {
                    throw new CustomException.ValidationError("Properties " + f.getName() + " is null");
                }
            } catch (IllegalAccessException e) {
                LOGGER.error("Error with accessible object cause by {} ", e.getMessage());
            }
        }
        return this;
    }

    /**
     * Check null and empty all field with annotation NotNullAndEmpty
     * Check null all field with annotation NotNull
     * @param object
     * @throws vn.simulator.exception.CustomException.ValidationError
     */
    @Override
    public IValidationTool checkNotNullWithAnnotation(Object object) {
        if (object == null) {
            throw new CustomException.ValidationError("Object is null");
        }
        for (Field f : object.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                Annotation[] annotations = f.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof NotNullAndEmpty && f.get(object) instanceof String) {
                        if (States.isNullOrEmpty((String) f.get(object))) {
                            throw new CustomException.ValidationError("Properties " + f.getName() + " is null or empty");
                        }
                    }
                    if (annotation instanceof NotNull) {
                        if (null == f.get(object)) {
                            throw new CustomException.ValidationError("Properties " + f.getName() + " is null");
                        }
                    }
                }

            } catch (IllegalAccessException e) {
                LOGGER.error("Error with accessible object cause by {} ", e.getMessage());
            }
        }
        return this;
    }

    /**
     * Check not null and empty all field without annotation CanNullOrEmpty
     * Check not null all field without annotation CanEmpty
     * @param object
     * @throws vn.simulator.exception.CustomException.ValidationError
     */
    @Override
    public IValidationTool checkCanNullWithAnnotation(Object object) {
        if (object == null) {
            throw new CustomException.ValidationError("Object is null");
        }
        for (Field f : object.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                Annotation[] annotations = f.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof CanNullOrEmpty) {
                        break;
                    }
                    if (annotation instanceof CanEmpty) {
                        if (States.isNull(f.get(object))) {
                            throw new CustomException.ValidationError("Properties " + f.getName() + " is null");
                        }
                        break;
                    }
                    if (States.isNullOrEmpty((String) f.get(object))) {
                        throw new CustomException.ValidationError("Properties " + f.getName() + " is null or empty");
                    }
                }

            } catch (IllegalAccessException e) {
                LOGGER.error("Error with accessible object cause by {} ", e.getMessage());
            }
        }
        return this;
    }

    /**
     * Sum all field object with annotation HMAC and compare with input hmac
     * @param object has annotation in field
     * @param hmac client key
     * @throws vn.simulator.exception.CustomException.ValidationError if not equal
     */
    @Override
    public IValidationTool checkHMAC(Object object, String hmac) {
        if (object == null) {
            throw new CustomException.ValidationError("Object is null");
        }
        StringBuilder lock = new StringBuilder();
        for (Field f : object.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                Annotation[] annotations = f.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof HMAC) {
                        if (null != f.get(object)) {
                            lock.append(String.valueOf(f.get(object)));
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                LOGGER.error("Error with accessible object cause by {} ", e.getMessage());
                throw new CustomException.ValidationError("Error on access object");
            }
        }
        if (!Generator.generateSha256(lock.toString()).equals(hmac)) {
            throw new CustomException.ValidationError("Validation error when compare HMAC with input object");
        }
        return this;
    }

    /**
     * Check null all field in object
     * @throws vn.simulator.exception.CustomException.ValidationError
     * @param object
     */
    @Override
    public IValidationTool checkNotNullAll(Object object) {
        if (object == null) {
            throw new CustomException.ValidationError("Object is null");
        }
        boolean result = false;
        for (Field f : object.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);
                if (f.get(object) != null) {
                    result = true;
                    break;
                }
            } catch (IllegalAccessException e) {
                LOGGER.error("Error with accessible object cause by {} ", e.getMessage());
            }
        }
        if (!result) {
            throw new CustomException.ValidationError("Object don't have property");
        }
        return this;
    }

    @Override
    public IValidationTool isValidPassword(char[] password) {
        if (password.length < 32 || password.length > 256) {
            throw new CustomException.ValidationError("password is invalid");
        }
        return this;
    }
}
