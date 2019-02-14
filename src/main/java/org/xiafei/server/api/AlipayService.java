package org.xiafei.server.api;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.stereotype.Component;

@Component
public class AlipayService {
    private final String PRIVATE_KEY="MIIEowIBAAKCAQEAwL/8GceLwyse5JZhmsSc4sfAiXMdEPsstphgEFVqLifqXEf1" +
            "z+4gkAA/XIK5If9aOc+Ycb3hJ5nqZlhr5iVUIlpTXJAAeLdQBlMsCgP2T9F/YwBa" +
            "hE773ueeJUSS4sHOWaA5vxy7VF2BFuS9v9rNs4I4nx3z48RR2dzricQlg5G+JO41" +
            "GsSsbZiNlqvcJLSabr05ck8ja2Lj+KjIwZQUZGUrBR4TaAp6HuDLRld4DcAS0XLV" +
            "GcVaBBBG/HfX2WtmGYwpy3LepRWNTRA8JuAQCAnLQqV0tF7XfYMHdFR/pqq6K6i4" +
            "n5vCt8TitdsV7KqmckefgTBl0uwLquxPjynsiQIDAQABAoIBAQCgoh7d+R+v2grX" +
            "47+Z9MrucBw0MFwD39+mmJxOYHCp4Jf+qfXggIpgl+Q2G7df7ufpj+lyuRSgJTRk" +
            "cMITPf681lvsoxHRiKvHaZ8lBaJLaMNp5UyKQSzE2nBAjMKA+ZUXumEFtVMk/vvq" +
            "8Vx1PEr7k8XOTW8I3X59OxCMcov09Zgdfam5uIc3Ze2p5i2qEeFFaewbbYu50NNB" +
            "c2iyDsVPQcIQx+mD1zLLAxjrUzDUASy8f3dz6MLEq+XzWHWodAlVRNouXQ3kCix/" +
            "RaB8W+IWeOSQcrTicT4gXB9r/bBiLVxc/XuDGwfYlKawLhbPvsnuHZ304U6919Rc" +
            "5eEayNf1AoGBAPNpOMm1OtLU+k2iUcOYi7YM/FMFZwjfOB7dMnAeTKa+YS6mzFL9" +
            "n77JSlNKp/jvukh5/RyeWpidI43MtHVzpe79xClOyMk3s7+JJ/gDvsH8eVsGpM7/" +
            "t4XYwEqQjmS2lLAHJj+onMXIxUybTJbxAFXPBUwXfaiWOXJMJz7X90N/AoGBAMq4" +
            "Ab/TOg9xYYzsb58Z8wB/t8DB7w8LIYbHzWL3V2yIZtBIbieSimLUdNHYCMb/wLUK" +
            "OAEzt1TfeKbVyT0Xt5LNvR7vFQjiSOq/rJcNuNSBOgoTWWvH3tYBwz2LBrKUClQY" +
            "at2RXHvI4nHhyCOaPBG42uXnYkMTBDrrhSfPmrP3AoGALGjVMI/9JHwxErr7ufj5" +
            "/IQQAUGqE8dOIOtwiV4t0/pGFtHd04Jsg82L3vhu/w8V6KVBFdiaZN++nd9sSUWJ" +
            "T7pC8Npr7bJY/CgU/74HLrNWnNHwfcfP/v6VzsTjBeL968PSKakkuT0xKhHnfl6I" +
            "Fh/IMqLSjVCjOIRGYMPcXBcCgYA5NJ/ZwAO8Y+SBXh9JqIr4cxIppRCPEDcPz8ZX" +
            "zTCwgfVxhV8Ab8v5g7bLmMkxTu7Up+OSA0QTAVUMz3uQb+bv2HssBY/MB/9Hr3tG" +
            "zSqBXU5BlaBAWBC8F5c/AMKNFlWr0ZAmomSU0eWE+Y/+7AFThvTOt/L0tqMU9W2I" +
            "BH/FlQKBgCNyyXSy6VlKFlRsfPla4+LrMEi+7er/CWG+jubDxaWjvT/P4U7m35fY" +
            "iXnGkzgLPIc1ZlGuzR3rxDfC9oCBeqN6KdqK/GJ/Vjhqk9Ak1Ypxcr3U3vCBqYhG" +
            "h4CHBUtInQN/fXH2Uc3rZDxls868lZmeLdKawlDF/kqSILjv2gs9";

    private final String PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwL/8GceLwyse5JZhmsSc4sfAiXMdEPsstphgEFVqLifqXEf1z+4gkAA/XIK5If9aOc+Ycb3hJ5nqZlhr5iVUIlpTXJAAeLdQBlMsCgP2T9F/YwBahE773ueeJUSS4sHOWaA5vxy7VF2BFuS9v9rNs4I4nx3z48RR2dzricQlg5G+JO41GsSsbZiNlqvcJLSabr05ck8ja2Lj+KjIwZQUZGUrBR4TaAp6HuDLRld4DcAS0XLVGcVaBBBG/HfX2WtmGYwpy3LepRWNTRA8JuAQCAnLQqV0tF7XfYMHdFR/pqq6K6i4n5vCt8TitdsV7KqmckefgTBl0uwLquxPjynsiQIDAQAB";

    public AlipayClient getAlipayClient(){
        return new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2018112662314633", PRIVATE_KEY, "json", "UTF-8", PUBLIC_KEY, "RSA2");
    }
}
