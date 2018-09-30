package net.safedata.spring.training.rest.support;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * A Spring {@link Controller} used to showcase the usages of the {@link RequestMapping} Spring web annotation
 *
 * @author bogdan.solga
 */
@Controller
public class RequestResponseBodyController {

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/requestBody"
    )
    public @ResponseBody String requestBody(@RequestBody String request) {
        return "Getting the body of a POST request using the @RequestBody annotation - " + request;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/responseBody"
    )
    public @ResponseBody String responseBody() {
        return "Returning a response as a @ResponseBody";
    }
}
