package service.impls;

import domain.User;
import json.request.JsonRequest;
import json.response.JsonBalanceResponse;
import json.response.JsonResponse;
import service.JsonService;
import service.UserService;

public class JsonServiceImpl implements JsonService {

    private UserService userService = new UserServiceImpl();

    @Override
    public JsonResponse executeRequest(JsonRequest jsonRequest) {

        String requestType = jsonRequest.getType();
        User user = new User(jsonRequest.getLogin(), jsonRequest.getPassword());

        if (requestType.equals("CREATE_CLIENT"))
            return executeRegistrationRequest(user);
        if (requestType.equals("BALANCE")){
            return executeBalanceRequest(user);
        }

        return new JsonResponse(-1, "неизвестный тип запроса");
    }

    private JsonResponse executeRegistrationRequest(User user){
        int responseCode = -1;
        String responseMessage = "";

        try {
            boolean userExists = userService.checkUserExists(user);

            if (!userExists) {
                boolean registered = userService.register(user);

                if (registered){
                    responseCode = 0;
                }
            }
            else {
                responseCode = 1;
                responseMessage = "пользователь с таким логином уже существует";
            }
        }
        catch (Exception e){
            responseCode = 2;
            responseMessage = "техническая ошибка";
        }

        return new JsonResponse(responseCode, responseMessage);
    }

    private JsonResponse executeBalanceRequest(User user){
        int responseCode = -1;
        String responseMessage = "";

        try {
            int authResult = userService.checkAuth(user);

            if (authResult == 0){
                responseCode = 0;
                int balance = user.getBalance();

                return new JsonBalanceResponse(responseCode, responseMessage, String.valueOf(balance));
            }
            else if (authResult == 1){
                responseCode = 1;

                return new JsonResponse(responseCode, "пользователь не существует");
            }
            else if (authResult == 2) {
                responseCode = 2;

                return new JsonResponse(responseCode, "пароль не верен");
            }
        }
        catch (Exception e){
            responseCode = 3;
            responseMessage = "техническая ошибка";
        }

        return new JsonResponse(responseCode, responseMessage);
    }

}
