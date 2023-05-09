/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLibs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author LENOVO
 */
public class libs {

    public static boolean isIncludeDigits(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isIncludeAlphabet(String input) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isLetter(c)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isIncludeSpecialChars(String input) {
        String strPattern = "[^a-zA-Z0-9_]";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(input);
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (m.find()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^0[0-9]{9}$";
        return phoneNumber.matches(phoneRegex);
    }

    public static boolean isValidFullname(String fullname) {
        return fullname.matches("[a-zA-Z][a-zA-Z\\s]*");
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9\\s]).{8,}$";
        return password.matches(regex);
    }
}
