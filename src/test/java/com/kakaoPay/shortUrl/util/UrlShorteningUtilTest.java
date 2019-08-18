package com.kakaoPay.shortUrl.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class UrlShorteningUtilTest {
	
	public static final String baseKey = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final int baseKeyLeng = baseKey.length();
	
    /**
     * com.kakaoPay.shortUrl.util
     * UrlShorteningUtil.java.UrlToKey
     * @title ShortUrl 52진수로 변경 method
     */
	@Test
    public void UrlToKey() {
    	StringBuffer resultSb = new StringBuffer();
    	//int urlId = 2147483637;
    	int urlId = 21474836;
    	int subUrlId = 214;
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
    	while(subUrlId > 0) {
    		resultSb.append(baseKey.charAt( (int)(subUrlId%baseKeyLeng) ));
    		subUrlId = subUrlId/baseKeyLeng;
    	}
    	/**************************************************************************
    	 * urlId의 문자열 6자리 와 subUrlId의 문자열 2자리를 합쳐 8자리 문자열을 반환한다.
    	 **************************************************************************/
    	assertNotNull(resultSb);
    	System.out.println("변환된 URL : " +resultSb.toString());
    }
}
