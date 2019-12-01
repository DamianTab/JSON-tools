package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.tools.JsonTools;
import pl.put.poznan.transformer.tools.JsonToolsFilter;
import pl.put.poznan.transformer.tools.JsonToolsFilterImpl;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping
public class JsonToolsController {

    private final JsonToolsFilterImpl jsonFilterTools;
    private final JsonTools jsonTools;

    @Autowired
    public JsonToolsController(@Qualifier("JsonTools") JsonTools jsonTools) {
        this.jsonTools = jsonTools;
        jsonFilterTools = new JsonToolsFilterImpl(jsonTools);
    }

    @PostMapping(path = "minify", consumes = "application/json")
    public String minify(@RequestBody String json) {
        log.debug("minify called with params: JSON: {}", json);
        JsonNode jsonNode = jsonTools.parseJson(json);
        return jsonNode.toString();
    }

    @PostMapping(path = "deminify", produces = "application/json")
    public JsonNode deminify(@RequestBody String json) {
        log.debug("deminify called with params: JSON: {}", json);
        return jsonTools.parseJson(json);
    }

    @PostMapping(path = "ignore", produces = "application/json")
    public JsonNode ignore(@RequestBody String json, @RequestParam Set<String> ignored) {
        log.debug("ignore called with params: JSON: {}, ignoreSet: {}", json, ignored);
        JsonNode jsonNode = jsonTools.parseJson(json);
        return jsonFilterTools.ignore(jsonNode, ignored);
    }

    @PostMapping(path = "select", produces = "application/json")
    public JsonNode select(@RequestBody String json, @RequestParam Set<String> selected) {
        log.debug("select called with params: JSON: {}, selected: {}", json, selected);
        JsonNode jsonNode = jsonTools.parseJson(json);
        return jsonFilterTools.select(jsonNode, selected);
    }

}


