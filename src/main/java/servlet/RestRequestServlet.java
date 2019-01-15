package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import json.request.JsonRequest;
import json.response.JsonResponse;
import service.JsonService;
import service.impls.JsonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RestRequestServlet", urlPatterns = "/rest")
public class RestRequestServlet extends HttpServlet {

    private JsonService jsonService = new JsonServiceImpl();

    private ObjectMapper objectMapper = new ObjectMapper();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!request.getContentType().equals("application/json"))
            response.sendError(415);

        BufferedReader bufferedReader = request.getReader();

        StringBuilder sb = new StringBuilder();

        while (bufferedReader.ready())
            sb.append(bufferedReader.readLine());

        JsonRequest jsonRequest = objectMapper.readerFor(JsonRequest.class).readValue(sb.toString());

        JsonResponse jsonResponse = jsonService.executeRequest(jsonRequest);

        response.setContentType("application/json; charset=UTF-8");

        PrintWriter printWriter = response.getWriter();

        objectMapper.writeValue(printWriter, jsonResponse);

        printWriter.flush();

    }

}
