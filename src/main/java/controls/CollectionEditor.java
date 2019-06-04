package controls;

import entities.Person;
import entities.PersonHashSet;
import util.parser.PersonReader;

import java.io.File;
import java.io.IOException;

/**
 * Все операции по изменению коллекции должны производиться через этот класс.
 *
 * Это позволяет выполнять одну и ту же операцию в нескольких местах. Имея единую реализацию,
 * программа приобрктает гибкость, ведь мы в любой момент может изменить реализацию, при этом не
 * затрагивая другие части.
 *
 * Также все методы синхронизированы, что позволяет делать операции по работе с коллекциями атомарными
 *
 */
public class CollectionEditor {
    /**
     * Добавление персонажа в коллекцию
     *
     * @param set коллекция с которой необходимо работать
     * @param p пермонаж с которым нужно взаимодействовать
     * @return результат операции в виде строки для пользователя
     */
    public static synchronized String addPerson(PersonHashSet set, Person p){
            boolean res = set.add(p);
            if (res) return "Добавлен новый персонаж!\n" + p.getName() + ": " + p.getHelloMessage();
            else return "Такой персонаж уже есть в коллекции";
    }

    /**
     * Добавление персонажей из файла
     *
     * Может использоваться при инициализации сервера и в командах {@link commands.concrete.Import}, <code>load</code>
     *
     * @param set коллекция с которой необходимо работать
     * @param file файл с которого нужно добавить персонажей
     * @return результат операции в виде строки для сервера
     */
    public static synchronized String addPersonsFromCSV(PersonHashSet set, File file){
        try {
            set.addAll(PersonReader.readPersons(file));
            return "Добавлены персонажи из файла : " + file.getName();
        } catch (IOException e) {
            return ("Не удалось прочитать файл по пути: " + file.getPath()
                    + "\nОшибка: " + e.getMessage());

        } catch (Exception e){
            return "Не удалось прочитать персонажа из файла : " + e.getMessage();
        }
    }

    /**
     * Удаление персонажа
     * @param set коллекция с которой необходимо работать
     * @param p персонаж, которого надо удалить
     * @return результат операции в виде строки для пользователя
     */
    public static synchronized String removePerson (PersonHashSet set, Person p){
        boolean res = set.remove(p);
        if (res) return "Операция выполнена успешно. Персонаж " + p.getName() + " удален!";
        else {
            if (set.contains(p)) return "Операция не выполнилась. Персонаж " + p.getName() + " не удален!";
            else return "Запрашиваемый персонаж "+p.getName()+" не найден";
        }
    }
}
