package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    public MapSchema() {
        super(Map.class);
    }

    public MapSchema required() {
        addChecker(map -> map instanceof Map);
        setCalledRequired(true);
        return this;
    }

    public MapSchema sizeof(int exceptedSize) {
        Predicate<Map<?, ?>> p = map -> map.size() == exceptedSize;
        addChecker(p);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> shapes) {
        Predicate<Map<?, ?>> p = map -> shapes.entrySet().stream().allMatch(entry ->
                shapes.get(entry.getKey()).isValid(map.get(entry.getKey())));
        addChecker(p);
        return this;
    }
}
