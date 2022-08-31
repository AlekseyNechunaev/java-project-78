package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    public StringSchema() {
        super(String.class);
    }

    public StringSchema required() {
        Predicate<String> p = text -> !text.isEmpty();
        addChecker(p);
        setCalledRequired(true);
        return this;
    }

    public StringSchema minLength(int min) {
        Predicate<String> p = text -> text.length() >= min;
        addChecker(p);
        return this;
    }

    public StringSchema contains(String subText) {
        Predicate<String> p = text -> text.contains(subText);
        addChecker(p);
        return this;
    }
}
