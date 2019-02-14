package org.xiafei.server.api;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import org.springframework.stereotype.Component;

@Component
public class AlipayService {
    private final String PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDAv/wZx4vDKx7k\n" +
            "lmGaxJzix8CJcx0Q+yy2mGAQVWouJ+pcR/XP7iCQAD9cgrkh/1o5z5hxveEnmepm\n" +
            "WGvmJVQiWlNckAB4t1AGUywKA/ZP0X9jAFqETvve554lRJLiwc5ZoDm/HLtUXYEW\n" +
            "5L2/2s2zgjifHfPjxFHZ3OuJxCWDkb4k7jUaxKxtmI2Wq9wktJpuvTlyTyNrYuP4\n" +
            "qMjBlBRkZSsFHhNoCnoe4MtGV3gNwBLRctUZxVoEEEb8d9fZa2YZjCnLct6lFY1N\n" +
            "EDwm4BAICctCpXS0Xtd9gwd0VH+mqrorqLifm8K3xOK12xXsqqZyR5+BMGXS7Auq\n" +
            "7E+PKeyJAgMBAAECggEBAKCiHt35H6/aCtfjv5n0yu5wHDQwXAPf36aYnE5gcKng\n" +
            "l/6p9eCAimCX5DYbt1/u5+mP6XK5FKAlNGRwwhM9/rzWW+yjEdGIq8dpnyUFokto\n" +
            "w2nlTIpBLMTacECMwoD5lRe6YQW1UyT+++rxXHU8SvuTxc5Nbwjdfn07EIxyi/T1\n" +
            "mB19qbm4hzdl7anmLaoR4UVp7Btti7nQ00FzaLIOxU9BwhDH6YPXMssDGOtTMNQB\n" +
            "LLx/d3PowsSr5fNYdah0CVVE2i5dDeQKLH9FoHxb4hZ45JBytOJxPiBcH2v9sGIt\n" +
            "XFz9e4MbB9iUprAuFs++ye4dnfThTr3X1Fzl4RrI1/UCgYEA82k4ybU60tT6TaJR\n" +
            "w5iLtgz8UwVnCN84Ht0ycB5Mpr5hLqbMUv2fvslKU0qn+O+6SHn9HJ5amJ0jjcy0\n" +
            "dXOl7v3EKU7IyTezv4kn+AO+wfx5Wwakzv+3hdjASpCOZLaUsAcmP6icxcjFTJtM\n" +
            "lvEAVc8FTBd9qJY5ckwnPtf3Q38CgYEAyrgBv9M6D3FhjOxvnxnzAH+3wMHvDwsh\n" +
            "hsfNYvdXbIhm0EhuJ5KKYtR00dgIxv/AtQo4ATO3VN94ptXJPRe3ks29Hu8VCOJI\n" +
            "6r+slw241IE6ChNZa8fe1gHDPYsGspQKVBhq3ZFce8jiceHII5o8Ebja5ediQxME\n" +
            "OuuFJ8+as/cCgYAsaNUwj/0kfDESuvu5+Pn8hBABQaoTx04g63CJXi3T+kYW0d3T\n" +
            "gmyDzYve+G7/DxXopUEV2Jpk376d32xJRYlPukLw2mvtslj8KBT/vgcus1ac0fB9\n" +
            "x8/+/pXOxOMF4v3rw9IpqSS5PTEqEed+XogWH8gyotKNUKM4hEZgw9xcFwKBgDk0\n" +
            "n9nAA7xj5IFeH0moivhzEimlEI8QNw/PxlfNMLCB9XGFXwBvy/mDtsuYyTFO7tSn\n" +
            "45IDRBMBVQzPe5Bv5u/YeywFj8wH/0eve0bNKoFdTkGVoEBYELwXlz8Awo0WVavR\n" +
            "kCaiZJTR5YT5j/7sAVOG9M638vS2oxT1bYgEf8WVAoGAI3LJdLLpWUoWVGx8+Vrj\n" +
            "4uswSL7t6v8JYb6O5sPFpaO9P8/hTubfl9iJecaTOAs8hzVmUa7NHevEN8L2gIF6\n" +
            "o3op2or8Yn9WOGqT0CTVinFyvdTe8IGpiEaHgIcFS0idA399cfZRzetkPGWzzryV\n" +
            "mZ4t0prCUMX+SpIguO/aCz0=";

    private final String PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqrFD2SJqn6DyMQZq6M1hJNn3MO/w/VxpD1gXtvpfoczJDk6SZMLtGhjGrROwVxc2nfeqqiELmiSLRCvvYAC59am2LtmuiS8DWKRP5q3yKlu/EhIhm2vLn0Xjw4VuUsaOlbkFYyjcUgPG6rQGSzHRhKlGE7vGqsPWqAyn+cjgOUQEhE6rEPm9W75/NjKDiTouHU/dPYEo6DWestVN58cvELIc/2MSzQT1PUvXeZvMipl/O26LD7TAIF7l282UIh+hg8cXSRWdqwpUjSEOWM2o0LpRmAhQjlgxh6a2QWIxcWfxRxQKR9liIXRuZ56lMP0SF2yXqySXMWmfoeqkOuvv0wIDAQAB";

    public AlipayClient getAlipayClient(){
        return new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2018112662314633", PRIVATE_KEY, "json", "UTF-8", PUBLIC_KEY, "RSA2");
    }

    public AlipayUserInfoShareResponse getUserinfoShareResponse(String authCode){
        AlipayClient alipayClient = getAlipayClient();
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode(authCode);
        request.setGrantType("authorization_code");
        try {
            AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
            AlipayUserInfoShareRequest requestUserInfo = new AlipayUserInfoShareRequest();
            String auth_token = oauthTokenResponse.getAccessToken();
            return alipayClient.execute(requestUserInfo, auth_token);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }


}
