package fr.stl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex de mail et de mot de passe
 */
public class RegexValidator {

    /** Le pattern */
    private Pattern pattern;

    /** Le matcher */
    private Matcher matcher;

    /** Le REGEX email */
    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    // --Start of group
    // -- (?=.*\d) # must contains one digit from 0-9
    // -- (?=.*[a-z]) # must contains one lowercase characters
    // -- (?=.*[A-Z]) # must contains one uppercase characters
    // -- . # match anything with previous condition checking
    // -- {6,20} # length at least 6 characters and maximum of 20
    // --End of group
    /** Le REGEX password */
    public static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";

    /**
     * Constructeur
     * @param pattern le pattern
     */
    public RegexValidator(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    /**
     * Validate hex with regular expression
     * @param value value for validation
     * @return true valid value, false invalid value
     */
    public boolean validateValue(final String value) {
        matcher = pattern.matcher(value);
        return matcher.matches();

    }
}
