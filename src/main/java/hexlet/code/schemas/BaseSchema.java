package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private final List<Predicate<Object>> checks = new ArrayList<>();

    protected final void addChecker(Predicate<Object> p) {
        checks.add(p);
    }

    /**
     * @param obj - the object being checked for validity
     * @return - checks the object for compliance with the list of predicates,
     * returns true if the object matches all checks from the list, or false if the object does not match at least one
     */
    public boolean isValid(Object obj) {
        return checks.stream().allMatch(check -> check.test(obj));
    }
}
