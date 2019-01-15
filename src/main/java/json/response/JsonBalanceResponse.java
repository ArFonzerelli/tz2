package json.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class JsonBalanceResponse extends JsonResponse {

    private String balance;

    public JsonBalanceResponse(int code, String message, String balance) {
        super(code, message);
        this.balance = balance;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
