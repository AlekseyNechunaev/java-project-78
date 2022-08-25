package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private final List<Predicate<Object>> checked = new ArrayList<>();


    public List<Predicate<Object>> getChecked() {
        return checked;
    }

    protected boolean isValid(Object obj) {
        return checked.stream().allMatch(check -> check.test(obj));
    }
}
