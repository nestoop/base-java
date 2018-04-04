package cn.nest.web;

import cn.nest.converter.model.Vscode;
import org.springframework.web.bind.annotation.*;

/**
 * Created by botter-common
 * on 17-3-5.
 */
@RestController
public class ConverterController {

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public @ResponseBody String testJsonConverter(@RequestBody Vscode vscode){
        System.out.println("json code-------" + vscode.getVisaCode());
        return "ok";
    }

    @RequestMapping(value = "/xml", method = RequestMethod.POST)
    public @ResponseBody String testXmlConverter(@RequestBody Vscode vscode){
        System.out.println("xml code-------" + vscode.getVisaCode());
        return "ok";
    }
}
