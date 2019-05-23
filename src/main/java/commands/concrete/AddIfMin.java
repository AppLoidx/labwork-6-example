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
import java.util.Comparator;
import java.util.Optional;

/**
 * @author Arthur Kupriyanov
 */
public class AddIfMin extends Command {

    @Override
    public String getName() {
        return "add-if-min";
    }

    @Override
    public CollectionAction getAction(String context) {

        try {
            JSONPersonParser.validate(new JSONObject(context));
            Person person = JSONPersonParser.getPerson(context);

            return col -> {
                if (person== null){
                    return "Персонаж с таким классом не существует. Проверьте валидность ваших данных.";
                }else {
                    Optional<Person> minPerson = col.stream().min(Comparator.comparing(Person::getHeight));
                    if (!minPerson.isPresent()){
                        return CollectionEditor.addPerson(col, person);
                    }
                    else if ( minPerson.get().getHeight() > person.getHeight()){
                        return CollectionEditor.addPerson(col, person);
                    }

                    return "Не удалось добавить персонажа так как он соответствует условию add-if-min";
                }
            };
        }catch (JSONException e){
            return JSONContextReader.jsonException(e);
        } catch (FileNotFoundException e) {
            return col -> "Файл для валидации json не был найден по пути " + JSONPersonParser.schemaPath;
        } catch (ValidationException e){
            return JSONContextReader.validationException(e);
        } catch (ParseException e) {
            return col -> e.getMessage();
        }

    }

}
