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
 * @author Arthur Kupriyanov
 */
public class JSONPersonParser {
    public static final String schemaPath = "src\\main\\resources\\json-schemas\\person-validate-schema.json";
    public static void validate(JSONObject json) throws FileNotFoundException, ValidationException {

        File file = new File(schemaPath);
        if (!file.exists()){
            throw new FileNotFoundException("Файл " + file.getPath() + " не найден!");
        }
        Schema schema = SchemaLoader.load(new JSONObject(new JSONTokener(new FileReader(file))));
        schema.validate(json);
    }

    public static Person getPerson(String json) throws ParseException {
        PersonData data = new GsonBuilder().create().fromJson(json, PersonData.class);

        return PersonFabric.getPerson(data.getClassOf(), data.getName(), data.getHeight(), Gender.getGender(data.getGender()), new SimpleDateFormat("dd.MM.yyyy").parse(data.getBirthDate()), data.getHelloMessage());
    }
}
