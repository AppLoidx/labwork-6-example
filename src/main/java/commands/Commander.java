package commands;


import entities.PersonHashSet;

/**
 * @author Arthur Kupriyanov
 */
public class Commander {
    private PersonHashSet col;
    public Commander(PersonHashSet col){
        this.col = col;
    }

    /**
     * Выполнение команды
     * @param command полный введенный текст команды
     */
    public String execute(String command){
        if (command.isEmpty()) return "Список инициализированный команд пуст";
        String[] commandParts = command.split(" ", 2);
        final String COMMAND = commandParts[0];
        final String CONTEXT = commandParts.length > 1? commandParts[1]:"";

        Command cmd = CommandContainer.getCommand(COMMAND);
        if (cmd == null) {
            return "Команда с именем " + COMMAND + " не найдена";
        } else {
            return cmd.getAction(CONTEXT).action(col);
        }
    }
}
