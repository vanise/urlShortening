package com.kakaoPay.shortUrl.util;

import java.util.Random;

public class UrlShorteningUtil {
	
	public static final String baseKey = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final int baseKeyLeng = baseKey.length();
	
    /**
     * com.kakaoPay.shortUrl.util
     * UrlShorteningUtil.java.UrlToKey
     * @title ShortUrl 52진수로 변경 method
     */
    public static String UrlToKey(int urlId , int subUrlId) {
    	StringBuffer resultSb = new StringBuffer();
    	/**************************************************************************
    	 * String hashCode 는 최대 10자리의 숫자를 반환함으로 ( 2147483647을 넘지 않는다) 최대 6자리의 문자열을 만들어준다
    	 **************************************************************************/
    	while(urlId > 0) {
    		resultSb.append(baseKey.charAt( (int)(urlId%baseKeyLeng) ));
    		urlId = urlId/baseKeyLeng;
    	}
    	/**************************************************************************
    	 * 8자리의 shortening Url 생성을위해 subUrl을 더 계산해 2자리 문자열을 만들어준다
    	 **************************************************************************/
//        Random random = new Random(System.currentTimeMillis());
//        StringBuffer sb = new StringBuffer();
//        for(int i=0; i < 3; i++) {
//        	sb.append(random.nextInt(10));
//        }
//        int rndInt = Integer.parseInt(sb.toString());
    	while(subUrlId > 0) {
    		resultSb.append(baseKey.charAt( (int)(subUrlId%baseKeyLeng) ));
    		subUrlId = subUrlId/baseKeyLeng;
    	}
    	/**************************************************************************
    	 * urlId의 문자열 6자리 와 subUrlId의 문자열 2자리를 합쳐 8자리 문자열을 반환한다.
    	 **************************************************************************/
    	return resultSb.toString();
    }
}
