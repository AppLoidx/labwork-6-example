package util;

import com.google.gson.GsonBuilder;
import entities.Gender;
import entities.Person;
import entities.PersonData;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Парсер персонажа из JSON-данных. Имеет валидацию
 *
 */
public class JSONPersonParser {
    public static final String schemaPath = "person-validate-schema.json";

    /**
     * Валидация представленной JSON-schema. Для этого загружается {@link Schema} из файла
     * @param json объект валидации
     * @throws FileNotFoundException выбрасывается если не найдена {@link Schema} из файла
     * @throws ValidationException если объект не прошел валидацию
     */
    public static void validate(JSONObject json) throws FileNotFoundException, ValidationException {

        File file = new File(schemaPath);
        if (!file.exists()){
            throw new FileNotFoundException("Файл " + file.getPath() + " не найден!");
        }
        Schema schema = SchemaLoader.load(new JSONObject(new JSONTokener(new FileReader(file))));
        schema.validate(json);
    }

    /**
     * Сериализация персонажа из JSON с помощью {@link com.google.gson.Gson}
     * @param json JSON-schema в виде обычного {@link String}
     * @return десериализованный {@link Person}
     * @throws ParseException ошибка при чтении персонажа. Следует обработать ошибку и сообщить пользователю
     */
    public static Person getPerson(String json) throws ParseException {
        PersonData data = new GsonBuilder().create().fromJson(json, PersonData.class);

        return PersonFabric.getPerson(data.getClassOf(), data.getName(), data.getHeight(), Gender.getGender(data.getGender()), new SimpleDateFormat("dd.MM.yyyy").parse(data.getBirthDate()), data.getHelloMessage());
    }
}
