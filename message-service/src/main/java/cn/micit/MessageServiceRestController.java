package cn.micit;

import org.springframework.web.bind.annotation.*;

@RestController
public class MessageServiceRestController {

    @RequestMapping(value = "/greet/{userName}", method = RequestMethod.GET)
    public String greet(@PathVariable String userName) {
        return "Greet " + userName;
    }

    @RequestMapping(value = "/greet2/{userName}", method = RequestMethod.GET)
    public String greet2(@PathVariable String userName,
                         @RequestParam(name = "city") String cityName) {
        return "Greet2 " + userName + " at " + cityName;
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public LocationMessageResponse messages(@RequestBody LocationMessage locationMessage) {
        LocationMessageResponse locationMessageResponse = new LocationMessageResponse();
        locationMessageResponse.setUserName(locationMessage.getUserName());
        locationMessageResponse.setMessage("Hello "
                + locationMessage.getUserName()
                + " at "
                + locationMessage.getLocation());
        return locationMessageResponse;
    }
}
