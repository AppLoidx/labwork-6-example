package commands.concrete;

import commands.CollectionAction;
import org.everit.json.schema.ValidationException;
import org.json.JSONException;

/**
 * @author Arthur Kupriyanov
 */
class JSONContextReader {

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
