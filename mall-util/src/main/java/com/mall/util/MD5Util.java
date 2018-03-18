package com.mall.util;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2016/12/28.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2016/12/28
 */
public class MD5Util {
    public static String getMd5(String plainText) throws Exception {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("md5加密出错！");

        }
    }

    public static void main(String args[]) throws Exception {
        System.out.println(getMd5("reyub"));

    }
}
