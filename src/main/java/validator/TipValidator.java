package validator;

public class TipValidator {

    public Boolean isValidate(Float value, String message) {
      return valueIsPositive(value) && messageIsNotEmpty(message);
    }

    boolean valueIsPositive (Float value) {
        return value > 0;
    }
    boolean messageIsNotEmpty (String message) { return !message.isEmpty(); }
}
