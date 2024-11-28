package lk.ijse.culinaryacademy.util;

import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFieldValid(TextFieldType fieldType, String text) {
        String patternString = "";


        switch (fieldType) {
            case NAME:
                patternString = "^[A-z\\s]{3,}$";
                break;
            case CONTACT:
                patternString = "^([+]94|0)([1-9]{2})([0-9]{7})$";
                break;
            case NIC:
                patternString = "^([0-9]{9}[xXvV]|[0-9]{12})$";
                break;
            case ADDRESS:
                patternString = "^([A-z0-9]|[-/,.@+]|\\s){4,}$";
                break;
            case PRICE:
                patternString = "^[0-9]+(\\.[0-9]+)?$";
                break;
            case QUANTITY:
                patternString = "^\\d+$";
                break;
            case PASSWORD:
                patternString = "^.*\\d.*\\d.*\\d.*$";
                break;
            case USERNAME:
                patternString = "^[a-zA-Z0-9_.]+$";
                break;
            case EMAIL:
                patternString = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
                break;
            case DATE:
                patternString = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
                break;
            case DURATION:
                patternString = "^\\d+$";
                break;
        }

        if (text == null || text.trim().isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);

        return matcher.matches();
    }

    public static boolean setTextColor(TextFieldType fieldType, TextField textField) {
        if (isTextFieldValid(fieldType, textField.getText())) {
            textField.setStyle(" -fx-text-box-border: #34d734;\n" +
                    "    -fx-focus-color: #12dc12;\n" +
                    "    -fx-faint-focus-color: rgba(246,68,68,0);;");

            return true;
        } else {
            textField.setStyle(" -fx-text-box-border: #f10000;\n" +
                    "    -fx-focus-color: #ff0000;\n" +
                    "    -fx-faint-focus-color: rgba(246,68,68,0);;");
            return false;
        }
    }


    public static boolean setTextColor(TextFieldType location, MFXTextField textField) {
        if (Regex.isTextFieldValid(location, textField.getText())) {
            textField.getStyleClass().removeAll("invalid-field");
            textField.getStyleClass().add("valid-field");
            return true;
        } else {
            // Apply invalid field CSS class
            textField.getStyleClass().removeAll("valid-field");
            textField.getStyleClass().add("invalid-field");
            return false;
        }
    }

    public static boolean setTextColor(TextFieldType location, MFXPasswordField field) {

        if (Regex.isTextFieldValid(location, field.getText())) {
            field.getStyleClass().removeAll("invalid-field");
            field.getStyleClass().add("valid-field");
            return true;
        } else {
            field.getStyleClass().removeAll("valid-field");
            field.getStyleClass().add("invalid-field");
            return false;
        }
    }
}
