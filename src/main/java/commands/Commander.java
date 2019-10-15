package commands;


import entities.PersonHashSet;


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
        if (command.isEmpty()) return "Command is empty";
        String[] commandParts = command.split(" ", 2);
        final String COMMAND = commandParts[0];
        final String CONTEXT = commandParts.length > 1? commandParts[1]:"";

        Command cmd = CommandContainer.getCommand(COMMAND);
        if (cmd == null) {
            return "Команда с именем " + COMMAND + " не найдена";
        } else {
            int oldHash = col.hashCode();
            CollectionAction operation = cmd.getAction(CONTEXT);
            if (operation == null) {
                CommandContainer.deleteCommand(COMMAND);
                return "Команда не правильно инициализирована и удалена!";
            }
            String response = operation.action(col);
            if (oldHash != col.hashCode()) {
                col.setChangedDate();
            }

            return response;
        }
    }
}
