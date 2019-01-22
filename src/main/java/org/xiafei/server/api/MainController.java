package org.xiafei.server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController  {
    @Autowired
    DataService dataService;


    @PostMapping("map")
    public boolean map(String key, String value) {
        dataService.get().put(key, value);
        return true;
    }

    @GetMapping("map")
    public String get(String key) {
        return dataService.get().get(key);
    }
}
