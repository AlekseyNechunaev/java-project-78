package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        Validator v = new Validator();
        StringSchema stringSchema = v.string();
        System.out.println(stringSchema.isValid(""));
        System.out.println(stringSchema.isValid(null));
//        stringSchema.required();
//        System.out.println(stringSchema.isValid("what does the fox say"));
//        System.out.println(stringSchema.contains("what").isValid("wha does the fox say"));
        System.out.println(" _________________________");
//        NumberSchema numberSchema = v.number();
//        System.out.println(numberSchema.isValid(null));
//        numberSchema.required();
//        System.out.println(numberSchema.isValid(null));
//        System.out.println(numberSchema.isValid(5));
//        System.out.println(numberSchema.isValid("10"));
//        System.out.println(numberSchema.isValid(-10));
//        numberSchema.positive();
//        System.out.println(numberSchema.isValid(-10));
        MapSchema mapSchema = v.map();
        System.out.println(mapSchema.isValid(null));
        mapSchema.required();
        System.out.println(mapSchema.isValid(null));
        System.out.println(mapSchema.isValid(new HashMap<>()));
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        System.out.println(mapSchema.isValid(data));
        mapSchema.sizeOf(2);
        System.out.println(mapSchema.isValid(data));
        data.put("key2", "value2");
        System.out.println(mapSchema.isValid(data));


    }
}
