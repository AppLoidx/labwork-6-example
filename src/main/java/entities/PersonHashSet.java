package entities;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *
 * Коллекция для работы с персонажами
 *
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

    @Override
    public void saveTo(File file) throws IOException {
        if (!file.exists()){
            if (!file.mkdirs()){
                throw new IOException("Укажите другой путь для сохранения файла, так как " +
                        "заданный путь недопустим для сохранения файла");
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
        if (file.exists()) {
            if (file.canWrite()) {

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    for (Person p : this) bw.write(p.CSVData() + "\n");
                }
            } else {
                throw new IOException("Файл недопустим для записи. Измените права, либо выберите " +
                        "другой путь для сохранения файла");
            }
        } else {
            if (file.createNewFile()) {
                saveTo(file);
            } else {
                throw new IOException("Не удалось создать новый файл. Используйте другую директорию");
            }
        }
    }
}
