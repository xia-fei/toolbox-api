package org.xiafei.server.api;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import org.junit.Test;

import java.math.BigDecimal;

public class AlipayServiceTest {
    private AlipayService alipayService = new AlipayService();

    @Test
    public void diver() {
        double d = 1.1 % 1;
        System.out.println(d);

        double result = BigDecimal.valueOf(1.1D).remainder(BigDecimal.valueOf(1)).doubleValue();
        System.out.println(result);

        System.out.println(4.1 % 3);

        System.out.println(3 % 4);
    }

    @Test
    public void test2() {
        System.out.println(alipayService.getUserinfoShareResponse("7bc376458bdb4fd989ec05aa1129YF42").getBody());
    }

    @Test
    public void test1() {
        AlipayClient alipayClient = alipayService.getAlipayClient();
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode("267895bc684c45f0af83390f09ecUX42");
        request.setGrantType("authorization_code");
        try {
            AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
            System.out.println(oauthTokenResponse.getAccessToken());
        } catch (AlipayApiException e) {
            //处理异常
            e.printStackTrace();
        }
    }
}
