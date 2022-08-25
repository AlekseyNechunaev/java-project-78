package hexlet.code.schemas;

import hexlet.code.BaseSchema;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        getChecked().add(text -> text instanceof String && !((String) text).isEmpty());
        return this;
    }

    public StringSchema minLength(int min) {
        getChecked().add(text -> ((String) text).length() >= min);
        return this;
    }

    public StringSchema contains(String subText) {
        getChecked().add(text -> ((String) text).contains(subText));
        return this;
    }
}
