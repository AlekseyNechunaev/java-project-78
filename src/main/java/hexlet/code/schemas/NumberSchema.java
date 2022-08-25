package hexlet.code.schemas;

import hexlet.code.BaseSchema;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        getChecked().add(number -> number instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        getChecked().add(number -> (Integer) number > 0);
        return this;
    }

    public NumberSchema range(int lowBound, int highBound) {
        getChecked().add(number -> (Integer) number >= lowBound && (Integer) number <= highBound);
        return this;
    }
}
