package me.mjaroszewicz.httptester;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    private final static Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private HttpServletRequestDeserializer deserializer;

    @RequestMapping
    public ObjectNode handleRequest(HttpServletRequest request){

        log.info("Handling request from address:  " + request.getRemoteAddr());

        return deserializer.deserialize(request);
    }

}
