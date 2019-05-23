package commands.concrete;


import commands.CollectionAction;
import commands.Command;
import controls.CollectionEditor;
import entities.Person;
import org.everit.json.schema.ValidationException;
import org.json.JSONException;
import org.json.JSONObject;
import util.JSONPersonParser;

import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * @author Arthur Kupriyanov
 */
public class Add extends Command {

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public CollectionAction getAction(String context) {
        String json = JSONContextReader.readJSONContext(context);

        try {
            JSONPersonParser.validate(new JSONObject(json));
            Person person = JSONPersonParser.getPerson(json);

            return col -> {
                if (person == null) {
                    return "Персонаж с таким классом не существует. Проверьте валидность ваших данных.";
                } else {
                    return CollectionEditor.addPerson(col, person);
                }
            };
        }catch (JSONException e){
            return JSONContextReader.jsonException(e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return col -> "Файл для валидации json не был найден по пути " + JSONPersonParser.schemaPath;
        } catch (ValidationException e){
            return JSONContextReader.validationException(e);
        } catch (ParseException e) {
            return  col -> e.getMessage();
        }
    }

}
