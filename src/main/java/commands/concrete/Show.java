package commands.concrete;

import commands.CollectionAction;
import commands.Command;



public class Show extends Command {
    private String decor = "--------------------\n";
    @Override
    public String getName() {
        return "show";
    }

    @Override
    public CollectionAction getAction(String context) {
           if (context!= null && !context.isEmpty()){
               if (context.equals("sorted")){
                   return col -> {
                       StringBuilder sb = new StringBuilder();
                       col.getSortedList().forEach(e -> sb.append(decor).append(e).append("\n").append(decor));
                       return sb.toString();
                   };
               }
           }


        return col -> {
            StringBuilder sb = new StringBuilder();
            col.forEach(e -> sb.append(decor).append(e).append("\n").append(decor));
            return sb.toString();
        };
    }


}
