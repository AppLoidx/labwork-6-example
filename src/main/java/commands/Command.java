package commands;

/**
 * Superclass for all commands
 *
 */
public class Command {
    private CollectionAction action;
    private String name;

    /**
     * Пустой конструктор для имплементирующих команд
     *
     * С этим надо быть осторожнее, так как если не переопределить поля {@link #name}
     * и {@link #name}, то будут использоваться их значения по умолчанию, то есть <code>null</code>
     */
    public Command(){}

    /**
     * Этот конструктор предназначен для создания анонимных команд
     * @param name имя команды
     * @param action действие команды
     */
    public Command(String name, CollectionAction action) {
        this.action = action;
        this.name = name;
    }

    /**
     * Через этот метод контейнер команд, скорее всего {@link CommandContainer} и {@link Commander},
     * делают выборку (соответствие первого слова). Иными словами, этот метод должен возвращать уникальный
     * идентификатор команды, понятный польщователю
     * @return идентификатор команды ( имя )
     */
    public String getName(){
        return name;
    }

    /**
     * Метод используется для отдельных классов-команд, которые переопределяют этот метод.
     *
     * В случае объявления анонимного объявления (реализацию по умолчанию), возвращает {@link #action}
     * что в свою очередь может являться <code>null</code>
     * @param context
     * @return
     */
    public CollectionAction getAction(String context){
        return action;
    }

}
