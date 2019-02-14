package org.xiafei.server.api;

import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MainController {
    @Autowired
    DataService dataService;
    @Autowired
    AlipayService alipayService;

    @GetMapping("alipay")
    public Object alipay(String auth_code) {
        AlipayClient alipayClient = alipayService.getAlipayClient();
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(auth_code);
        request.setGrantType("authorization_code");
        try {
            AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
            AlipayUserInfoShareRequest requestUserInfo = new AlipayUserInfoShareRequest();
            String auth_token = oauthTokenResponse.getAccessToken();

            AlipayUserInfoShareResponse userinfoShareResponse = alipayClient.execute(requestUserInfo, auth_token);
            return new ObjectMapper().readValue(userinfoShareResponse.getBody(), Map.class);
        } catch (Exception e) {
            //处理异常
            e.printStackTrace();
            return e.toString();
        }

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
}
