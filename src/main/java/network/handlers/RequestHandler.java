package network.handlers;

import commands.Commander;
import entities.PersonHashSet;


public class RequestHandler implements Handler {
    private final PersonHashSet col;
    private final Commander commander;

    public RequestHandler(PersonHashSet col, Commander commander) {
        this.col = col;
        this.commander = commander;
    }


    @Override
    public String getResponse(String data) {
        return commander.execute(data);
    }
}
