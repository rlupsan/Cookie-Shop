package api;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Request implements Serializable {

    /**
     * Key is param name
     * Value is param value
     */
    private final Map<String, Object> parameters = new HashMap<>();
    private String commandName;

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public Object getParameter(Object key) {
        return parameters.get(key);
    }

    public Object putParameter(String key, Object value) {
        return parameters.put(key, value);
    }

    public Object removeParameter(Object key) {
        return parameters.remove(key);
    }
}
