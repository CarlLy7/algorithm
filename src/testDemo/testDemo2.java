package testDemo;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @description:  字符串类型的ip地址转成int类型
 * @author: lyq
 * @createDate: 8/3/2023
 * @version: 1.0
 */
public class testDemo2 {
    public static int getIp(String str) throws UnknownHostException {
        InetAddress inetAddress = Inet4Address.getByName(str);
        byte[] address = inetAddress.getAddress();
        int result=0;
        for (byte b : address) {
            result<<=8;
            result  |= (b& 0xFF);
        }
        return result;
    }
}
