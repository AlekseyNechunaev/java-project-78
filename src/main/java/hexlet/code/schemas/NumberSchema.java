package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {

    public NumberSchema() {
        super(Integer.class);
    }

    public NumberSchema required() {
        addChecker(number -> number instanceof Integer);
        setCalledRequired(true);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> p = number -> number > 0;
        addChecker(p);
        return this;
    }

    public NumberSchema range(int lowBound, int highBound) {
        Predicate<Integer> p = number -> number >= lowBound && number <= highBound;
        addChecker(p);
        return this;
    }
}
