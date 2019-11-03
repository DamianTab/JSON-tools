package pl.put.poznan.transformer.rest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.TextTransformer;

import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/{text}")
public class TextTransformerController {


    @GetMapping(produces = "application/json")
    public String get(@PathVariable String text,
                              @RequestParam(value="transforms", defaultValue="upper,escape") String[] transforms) {

        // log the parameters
        log.debug(text);
        log.debug(Arrays.toString(transforms));

        // do the transformation, you should run your logic here, below just a silly example
        TextTransformer transformer = new TextTransformer(transforms);
        return transformer.transform(text);
    }

    @PostMapping(produces = "application/json")
    public String post(@PathVariable String text,
                      @RequestBody String[] transforms) {

        // log the parameters
        log.debug(text);
        log.debug(Arrays.toString(transforms));

        // do the transformation, you should run your logic here, below just a silly example
        TextTransformer transformer = new TextTransformer(transforms);
        return transformer.transform(text);
    }



}


