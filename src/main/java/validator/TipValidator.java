package validator;

public class TipValidator {

    public Boolean valueValidator(Float value) {
      return valueIsPositive(value);
    }

    boolean valueIsPositive (Float value) {
        return value > 0;
    }
}
