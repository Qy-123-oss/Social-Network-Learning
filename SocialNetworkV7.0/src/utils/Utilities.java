package utils;

public class Utilities {

    /**
     *  This method returns a string that was passed as a parameter, truncated to a specific length, also
     *  passed as a parameter.  If the original String is less than the passed length, then the original string
     *  is just returned.
     *
     * @param stringToTruncate  The string that will be truncated to a specific length
     * @param length The length to which to truncate the string to
     * @return The string truncated to a specific length
     */
    public static String truncateString(String stringToTruncate, int length){
        if (stringToTruncate.length() <= length) {
            return stringToTruncate;
        }
        else{
            return stringToTruncate.substring(0, length);
        }
    }

    /**
     * This method takes in a string, passed as a parameter and valdiates whether it is less than or equal to
     * a specific length or not.
     *
     * @param strToCheck The string that will be checked to see if it is a specific length
     * @param maxLength The length that the string will be validated against
     * @return True if the string is less than or equal the max length and false otherwise.
     */
    public static boolean validateStringLength(String strToCheck, int maxLength){
        return strToCheck.length() <= maxLength;
    }

    /**
     * This method returns true if the numberToCheck is between min and max (both inclusive)
     *
     * @param numberToCheck The number whose range is being checked.
     * @param min The minimum range number to check against (inclusive)
     * @param max The maximum range number to check against (inclusive)
     * @return Returns true if the numberToCheck is between min and max (both inclusive), false otherwise.
     */
    public static boolean validRange(double numberToCheck, double min, double max) {
        return ((numberToCheck >= min) && (numberToCheck <= max));
    }

}
