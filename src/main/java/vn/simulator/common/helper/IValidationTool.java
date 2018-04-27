package vn.simulator.common.helper;

import java.util.List;

/**
 * Created by MILA on 2/24/2017.
 */
public interface IValidationTool {

    boolean checkValidPhoneNumber(String phone);

    boolean checkValidEmailAddress(String email);

    IValidationTool isValidPhoneNumber(String phone);

    IValidationTool isValidEmailAddress(String email);

    IValidationTool isValidEmailOrPhone(String input);

    IValidationTool checkNullAll(Object object);

    IValidationTool checkNullBlack(Object object, List<String> blackList);

    IValidationTool checkNullWhite(Object object, List<String> whiteList);

    IValidationTool checkNotNullWithAnnotation(Object object);

    IValidationTool checkCanNullWithAnnotation(Object object);

    IValidationTool checkHMAC(Object object, String hmac);

    IValidationTool checkNotNullAll(Object object);

    IValidationTool isValidPassword(char[] password);

}
