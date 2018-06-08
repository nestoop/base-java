package cn.nest.webflux.web;

import cn.nest.webflux.base.UserBean;
import cn.nest.webflux.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author botter
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public Mono<String> testMono() {
        return testService.test();
    }

    /**
     * do userlist data
     * @param users
     * @return lastest add success  data
     */
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public Flux<UserBean> add(@RequestBody Flux<UserBean> users) {
        return testService.createOrUpdate(users);
    }

    /**
     * get one data
     * @param id
     * @return
     */
    @RequestMapping(value = "getUser/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public Mono<UserBean> getUser(@PathVariable int id) {
        return testService.getUserBeanById(id);
    }

    @RequestMapping(value = "deleteUser/{id}", method = RequestMethod.POST)
    public Mono<UserBean> delete(@PathVariable int id) {
        return testService.deleteById(id);
    }
}
