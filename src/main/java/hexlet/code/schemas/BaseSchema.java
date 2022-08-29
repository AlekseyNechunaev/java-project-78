package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private final List<Predicate<Object>> checks = new ArrayList<>();

    protected void addChecker(Predicate<Object> p) {
        checks.add(p);
    }

    public boolean isValid(Object obj) {
        return checks.stream().allMatch(check -> check.test(obj));
    }
}
