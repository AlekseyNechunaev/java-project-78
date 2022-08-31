package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    private static final Validator VALIDATOR = new Validator();

    @Test
    void validatorStringSchemaTestRequired() {
        StringSchema stringSchema = VALIDATOR.string();
        assertThat(stringSchema.isValid(null)).isTrue();
        assertThat(stringSchema.isValid("")).isTrue();
        stringSchema.required();
        assertThat(stringSchema.isValid(null)).isFalse();
        assertThat(stringSchema.isValid("")).isFalse();
        assertThat(stringSchema.isValid(1)).isFalse();
        assertThat(stringSchema.isValid("text")).isTrue();
    }

    @Test
    void validatorStringSchemaTestMinLength() {
        StringSchema stringSchema = VALIDATOR.string();
        stringSchema.minLength(4);
        assertThat(stringSchema.isValid(null)).isTrue();
        stringSchema.required();
        assertThat(stringSchema.isValid("tex")).isFalse();
        assertThat(stringSchema.isValid("texta")).isTrue();
        assertThat(stringSchema.isValid("text")).isTrue();
    }

    @Test
    void validatorStringSchemaTestContains() {
        StringSchema stringSchema = VALIDATOR.string();
        String text = "this is text";
        stringSchema.contains("te");
        assertThat(stringSchema.isValid(null)).isTrue();
        stringSchema.required();
        stringSchema.required().contains("te");
        assertThat(stringSchema.isValid(text)).isTrue();
        stringSchema.contains("text");
        assertThat(stringSchema.isValid(text)).isTrue();
        stringSchema.contains("texta");
        assertThat(stringSchema.isValid(text)).isFalse();
        stringSchema.contains("text");
        assertThat(stringSchema.isValid(text)).isFalse();
    }

    @Test
    void validatorNumberSchemaRequired() {
        NumberSchema numberSchema = VALIDATOR.number();
        assertThat(numberSchema.isValid(null)).isTrue();
        assertThat(numberSchema.isValid("1")).isTrue();
        numberSchema.required();
        assertThat(numberSchema.isValid(null)).isFalse();
        assertThat(numberSchema.isValid("1")).isFalse();
        assertThat(numberSchema.isValid(0)).isTrue();
        assertThat(numberSchema.isValid(1)).isTrue();
        assertThat(numberSchema.isValid(-1)).isTrue();
    }

    @Test
    void validatorNumberSchemaPositive() {
        NumberSchema numberSchema = VALIDATOR.number();
        numberSchema.positive();
        assertThat(numberSchema.isValid(null)).isTrue();
        numberSchema.required();
        assertThat(numberSchema.isValid(null)).isFalse();
        assertThat(numberSchema.isValid(0)).isFalse();
        assertThat(numberSchema.isValid(1)).isTrue();
        assertThat(numberSchema.isValid(-1)).isFalse();
    }

    @Test
    void validatorNumberSchemaRange() {
        NumberSchema numberSchema = VALIDATOR.number();
        numberSchema.range(5, 10);
        assertThat(numberSchema.isValid(null)).isTrue();
        numberSchema.required();
        assertThat(numberSchema.isValid(4)).isFalse();
        assertThat(numberSchema.isValid(5)).isTrue();
        assertThat(numberSchema.isValid(11)).isFalse();
        assertThat(numberSchema.isValid(10)).isTrue();
        assertThat(numberSchema.isValid(7)).isTrue();
    }

    @Test
    void validatorMapSchemaRequired() {
        MapSchema mapSchema = VALIDATOR.map();
        assertThat(mapSchema.isValid(null)).isTrue();
        assertThat(mapSchema.isValid(List.of())).isTrue();
        mapSchema.required();
        assertThat(mapSchema.isValid(null)).isFalse();
        assertThat(mapSchema.isValid(List.of())).isFalse();
        assertThat(mapSchema.isValid(Map.of())).isTrue();
    }

    @Test
    void validatorMapSchemaSizeOf() {
        MapSchema mapSchema = VALIDATOR.map();
        mapSchema.required();
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        assertThat(mapSchema.isValid(map)).isTrue();
        mapSchema.sizeof(3);
        assertThat(mapSchema.isValid(map)).isFalse();
        map.put("key3", "value3");
        assertThat(mapSchema.isValid(map)).isTrue();
        map.put("key4", "value4");
        assertThat(mapSchema.isValid(map)).isFalse();
    }

    @Test
    void validatorMapSchemaShape() {
        MapSchema mapSchema = VALIDATOR.map();
        mapSchema.required().sizeof(3);
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", VALIDATOR.string().required().contains("Jh"));
        schemas.put("age", VALIDATOR.number().positive().range(18, 60));
        schemas.put("friends", VALIDATOR.map().required().sizeof(2));
        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Jhony");
        human1.put("age", 60);
        assertThat(mapSchema.isValid(human1)).isFalse();

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Jhony");
        human2.put("age", 60);
        human2.put("friends", Map.of("name", "Tom"));
        assertThat(mapSchema.isValid(human2)).isFalse();

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "Jhony");
        human3.put("age", 61);
        human3.put("friends", Map.of("name", "Tom", "age", 57));
        assertThat(mapSchema.isValid(human3)).isFalse();

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Jony");
        human4.put("age", 60);
        human4.put("friends", Map.of("name", "Tom", "age", 57));
        assertThat(mapSchema.isValid(human4)).isFalse();

        Map<String, Object> human5 = new HashMap<>();
        human5.put("name", "Jhony");
        human5.put("age", 60);
        human5.put("friends", Map.of("name", "Tom", "age", 57));
        assertThat(mapSchema.isValid(human5)).isTrue();
    }

}
