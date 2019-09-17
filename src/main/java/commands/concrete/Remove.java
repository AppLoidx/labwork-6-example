package commands.concrete;


import commands.CollectionAction;
import commands.Command;
import controls.CollectionEditor;
import entities.Person;

import java.util.Set;
import java.util.stream.Collectors;



public class Remove extends Command {

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public CollectionAction getAction(String context) {

        if (context.matches("where\\s*?name\\s*?=.+")){
            String name = context.split("=")[1].trim();
            return deleteByName(name.trim());
        }
        try {
            int width = Integer.parseInt(context);
            return col -> {
                StringBuilder log = new StringBuilder();

                // TODO: Stream usage
                Set<Person> set = col.stream().filter(person -> person.getHeight() == width).collect(Collectors.toSet());
                set.forEach(p -> log.append(CollectionEditor.removePerson(col, p)).append("\n"));
                return "Deleting by width : " + width + "\nLogs:\n" + log.toString();
            };
        } catch (NumberFormatException e){
            return (col -> "Неверный синтаксис команды");
        }


    }

    private CollectionAction deleteByName(String name){
        return col -> {
            StringBuilder log = new StringBuilder();
            Set<Person> set = col.stream().filter(p -> p.getName().equals(name)).collect(Collectors.toSet());
            set.forEach(p->log.append(CollectionEditor.removePerson(col, p)).append("\n"));
            if (log.toString().isEmpty()){
                return "Персонаж с именем " + name + " не найден";
            }
            return log.toString();
        };
    }


}
