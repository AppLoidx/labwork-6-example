package controls;

import entities.Person;
import entities.PersonHashSet;
import util.parser.PersonReader;

import java.io.File;
import java.io.IOException;

/**
 * @author Arthur Kupriyanov
 */
public class CollectionEditor {
    public static String addPerson(PersonHashSet set, Person p){
            set.add(p);
            return "Добавлен новый персонаж!\n" + p.getName() + ": " + p.getHelloMessage();
    }

    public static String addPersonsFromCSV(PersonHashSet set, File file){
        try {
            set.addAll(PersonReader.readPersons(file));
            return "Добавлены персонажи из файла : " + file.getName();
        } catch (IOException e) {
            return ("Не удалось прочитать файл по пути: " + file.getPath()
                    + "\nОшибка: " + e.getMessage()
                    + "\nВывести stacktrace?[y/n]");

        } catch (Exception e){
            return "Не удалось прочитать персонажа из файла : " + e.getMessage();
        }
    }
}
