package cn.nest.web;

import cn.nest.manager.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by botter
 * on 17-3-29.
 */
@RestController
@RequestMapping("/api")
public class FaroopController {

    @Autowired
    ResourceManager faroopManager;

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public @ResponseBody  String test() {
        return faroopManager.testReource("test","90999999");
    }

    @RequestMapping(value = "test2", method = RequestMethod.POST)
    public @ResponseBody  String test3() {
        return faroopManager.testReource("test2","990");
    }
}
