package commands;

import entities.PersonHashSet;


/**
 * @author Arthur Kupriyanov
 */
@FunctionalInterface
public interface CollectionAction {
    String action(PersonHashSet set);
}
