package me.mjaroszewicz.httptester;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Enumeration;

@Service
public class HttpServletRequestDeserializer {

    private ObjectMapper mapper = new ObjectMapper();

    /**
     *
     * @param request request to be parsed
     * @return JSON representation of provided request
     */
    public ObjectNode deserialize(HttpServletRequest request) {

        ObjectNode root = mapper.createObjectNode();

        //headers
        ObjectNode headers = root.putObject("Headers");
        parseHeaders(request, headers);

        //params
        ObjectNode params = root.putObject("Parameters");
        parseParams(request, params);

        ObjectNode others = root.putObject("Other");
        others.put("origin", request.getRemoteAddr());
        others.put("url", request.getRequestURI());


        return root;
    }

    private void parseHeaders(HttpServletRequest request, ObjectNode headers){

        Enumeration<String> headersEnum = request.getHeaderNames();
        String headerName;

        while (headersEnum.hasMoreElements()) {
            headerName = headersEnum.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
    }

    private void parseParams(HttpServletRequest request, ObjectNode params) {

        Enumeration<String> paramsEnum = request.getParameterNames();
        String paramName;

        while (paramsEnum.hasMoreElements()) {
            paramName = paramsEnum.nextElement();
            params.put(paramName, request.getParameter(paramName));
        }
    }


}
