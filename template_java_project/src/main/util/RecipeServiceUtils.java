package main.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.platform.commons.util.StringUtils;

import javax.naming.directory.InvalidAttributeValueException;

public class RecipeServiceUtils {

    static final int RECIPE_ID_LENGTH = 5;

    public static boolean isValidString(final String stringToValidate) throws InvalidAttributeValueException {
        if (StringUtils.isBlank(stringToValidate)) {
            throw new InvalidAttributeValueException();
        }
        return true;
    }
    public static String generateRecipeId() {
        return RandomStringUtils.randomAlphanumeric(RECIPE_ID_LENGTH);
    }
}

