package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping
public class JsonToolsController {

    private ObjectMapper mapper;

    @Autowired
    public JsonToolsController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @PostMapping(path = "minify", consumes = "application/json")
    public String minify(@RequestBody String json) throws IOException {

        // log the parameters
        log.debug("minify called with parameters {}", json);
        JsonNode jsonNode = mapper.readTree(json);
        return jsonNode.toString();
    }

    @PostMapping(path = "deminify", produces = "application/json")
    public JsonNode deminify(@RequestBody String json) throws IOException {

        // log the parameters
        log.debug("deminify called with parameters {}", json);

        // do the transformation, you should run your logic here, below just a silly example
        return mapper.readTree(json);
    }


}


