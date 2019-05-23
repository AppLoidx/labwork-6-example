package entities;

import entities.Person;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author Arthur Kupriyanov
 */
public class PersonHashSet extends HashSet<Person> implements CSVWriteable{

    private Date changedDate = new Date();

    public List<Person> getSortedList(){
        List<Person> persons = new ArrayList<>(this);
        Collections.sort(persons);
        return persons;
    }

    public void setChangedDate(){
        changedDate = new Date();
    }

    public Date getChangedDate(){
        return changedDate;
    }

    public void saveTo(File file) throws IOException {
        if (!file.exists()){
            if (!file.mkdirs()){
                System.err.println("Укажите другой путь для сохранения файла, так как " +
                        "заданный путь недопустим для сохранения файла или введите exit, чтобы выйти " +
                        "без сохранения");
                String line;
                if ((line=new Scanner(System.in).nextLine()).equals("exit")){
                    return;
                } else {
                    saveTo(new File(line));
                }
            }
        }
        if (file.isDirectory()) {
            File data = new File(file.getPath() + "/saved-data.csv");
            writeToFile(data);
        } else {
            writeToFile(file);
        }
    }

    private void writeToFile(File file) throws IOException {
        System.out.println(file.getPath());
        if (file.exists()) {
            if (file.canWrite()) {

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    for (Person p : this) bw.write(p.CSVData() + "\n");
                }
            } else {
                System.err.println("Файл недопустим для записи. Измените права, либо выберите " +
                        "другой путь для сохранения файла");
                String line;
                if (!(line = new Scanner(System.in).nextLine()).equals("exit")) {
                    saveTo(new File(line));
                }
            }
        } else {
            if (file.createNewFile()) {
                saveTo(file);
            } else {
                System.err.println("Не удалось создать новый файл. Используйте другую директорию");
                String line;
                if (!(line = new Scanner(System.in).nextLine()).equals("exit")) {
                    saveTo(new File(line));
                }
            }
        }
    }
}
