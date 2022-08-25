package hexlet.code.schemas;

import hexlet.code.BaseSchema;

import java.util.HashMap;
import java.util.Map;

public final class MapSchema extends BaseSchema {

    private final Map<String, BaseSchema> shapes = new HashMap<>();

    public MapSchema required() {
        getChecked().add(map -> map instanceof Map);
        return this;
    }

    public MapSchema sizeOf(int exceptedSize) {
        getChecked().add(map -> map instanceof Map && ((Map<?, ?>) map).size() == exceptedSize);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> shapes) {
        this.shapes.putAll(shapes);
        return this;
    }

    @Override
    public boolean isValid(Object obj) {
        return super.isValid(obj);
    }
}
