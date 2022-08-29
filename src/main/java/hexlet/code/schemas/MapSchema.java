package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema {

    private final Map<String, BaseSchema> shapes = new HashMap<>();

    public MapSchema required() {
        addChecker(map -> map instanceof Map);
        return this;
    }

    public MapSchema sizeOf(int exceptedSize) {
        addChecker(map -> map instanceof Map && ((Map<?, ?>) map).size() == exceptedSize);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> shapes) {
        this.shapes.putAll(shapes);
        return this;
    }

    @Override
    public boolean isValid(Object obj) {
        if (!super.isValid(obj)) {
            return false;
        }
        if (!shapes.isEmpty()) {
            Map<?, ?> map = (Map<?, ?>) obj;
            return shapes.entrySet().stream()
                    .allMatch(entry -> shapes.get(entry.getKey()).isValid(map.get(entry.getKey())));
        }
        return true;
    }
}
