package org.xiafei.server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MainController {
    private final
    DataService dataService;
    private final
    AlipayService alipayService;

    public MainController(DataService dataService, AlipayService alipayService) {
        this.dataService = dataService;
        this.alipayService = alipayService;
    }

    @GetMapping("/html")
    public String html() {
        return dataService.get().get("html");
    }

    @GetMapping("/html/{id}")
    public String html(@PathVariable("id") String id) {
        return dataService.get().get(id);
    }

    @GetMapping("alipay")
    public Object alipay(String auth_code) {
        return alipayService.getUserinfoShareResponse(auth_code);
    }

    @PostMapping("map")
    public boolean map(String key, @RequestBody String value) {
        dataService.get().put(key, value);
        return true;
    }

    @GetMapping("map")
    public String get(String key) {
        return dataService.get().get(key);
    }

    @PostMapping("/servlet")
    @ResponseBody
    public String servlet(HttpServletRequest request,@RequestBody String body){
        return request.getQueryString()+":"+body;
    }
}
