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
    public static synchronized String addPerson(PersonHashSet set, Person p){
            boolean res = set.add(p);
            if (res) return "Добавлен новый персонаж!\n" + p.getName() + ": " + p.getHelloMessage();
            else return "Такой персонаж уже есть в коллекции";
    }

    public static synchronized String addPersonsFromCSV(PersonHashSet set, File file){
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
    public static synchronized String removePerson (PersonHashSet set, Person p){
        boolean res = set.remove(p);
        if (res) return "Операция выполнена успешно. Персонаж " + p.getName() + " удален!";
        else {
            if (set.contains(p)) return "Операция не выполнилась. Персонаж " + p.getName() + " не удален!";
            else return "Запрашиваемый персонаж "+p.getName()+" не найден";
        }
    }
}
