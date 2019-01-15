package json.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class JsonRequest {

    private String type;

    private String login;

    private String password;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @SuppressWarnings("uncheked")
    @JsonProperty("request")
    private void unpackRequest(Map<String, Object> request){
        this.type = (String) request.get("type");
        this.login = (String) request.get("login");
        this.password = (String) request.get("password");
    }
}
