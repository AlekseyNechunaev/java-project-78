package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        addChecker(number -> number instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        addChecker(number -> number == null || number instanceof Integer && (Integer) number > 0);
        return this;
    }

    public NumberSchema range(int lowBound, int highBound) {
        addChecker(number -> number instanceof Integer
                && (Integer) number >= lowBound
                && (Integer) number <= highBound);
        return this;
    }
}
