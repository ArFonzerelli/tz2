package service;

import json.request.JsonRequest;
import json.response.JsonBalanceResponse;
import json.response.JsonResponse;

public interface JsonService {

    JsonResponse executeRequest(JsonRequest jsonRequest);

}
