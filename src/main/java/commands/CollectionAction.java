package commands;

import entities.PersonHashSet;


/**
 *
 * Фунциональный интерфейс отвечающий за работу с коллекцией
 *
 * @author Arthur Kupriyanov
 */
@FunctionalInterface
public interface CollectionAction {
    /**
     * Какая-то работа с коллекцией, при этом результат моэет быть и удачным или наоборот
     *
     * @param set коллекция с которой необходимо работать
     * @return результат операции в виде строки, которая поакзывается исполнителю
     */
    String action(PersonHashSet set);
}
