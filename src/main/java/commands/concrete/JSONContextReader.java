package commands.concrete;

import commands.CollectionAction;
import org.everit.json.schema.ValidationException;
import org.json.JSONException;

import java.util.Scanner;

/**
 * @author Arthur Kupriyanov
 */
class JSONContextReader {
    static String readJSONContext(String context) {
        if (context.equals("") || (context.matches(".*\\{.*") && !context.matches(".*\\{.*}"))){
            Scanner sc = new Scanner(System.in);

            String line;
            StringBuilder sb = new StringBuilder();
            do {
                line=sc.nextLine();
                sb.append(line).append(" ");
            }while(!line.matches(".*}"));

            return context + sb.toString();
        } else return  context;
    }
    static CollectionAction validationException(ValidationException e){
        return col -> {
            StringBuilder sb = new StringBuilder();
            if (e.getCausingExceptions().isEmpty()){
                return e.getMessage();
            }else for (org.everit.json.schema.ValidationException exp : e.getCausingExceptions()) {
                sb.append(exp.getMessage()).append("\n");
            }

            return sb.toString();
        };
    }
    static CollectionAction jsonException(JSONException e){
        return col -> e.getMessage();
    }
}
