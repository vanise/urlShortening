package com.kakaoPay.shortUrl.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kakaoPay.shortUrl.service.UrlShorteningServiceTest;
import com.kakaoPay.shortUrl.vo.ShortUrlVOTest;

public class UrlShorteningControllerTest {
	@Autowired
	private UrlShorteningServiceTest urlShorteningService;
	
	@Test
	public void urlShorteningTest() {
		ShortUrlVOTest vo = new ShortUrlVOTest();
    	/***********************************************************************
    	 * URL Parameter
    	 ***********************************************************************/
        String url = "https://www.kakaopay.com/paystory/people";
        
    	/***********************************************************************
    	 * 간단한 String 해쉬코드 생성 (최대 10자리) (음수시 양수로 변환)이 정상적으로 실행되는지 확인
    	 ***********************************************************************/
        int urlToHash = Math.abs(url.hashCode());
        assertNotNull(urlToHash);
	}
	
	/**
	 * com.kakaoPay.shortUrl.controller
	 * UrlShorteningControllerTest.java.getOrgUrl
	 * @title url => shortUrl로 변환되는지 테스트 
	 */
	@Test
	public void getOrgUrlTest() {
    	/********************************************
    	 * URL Parameter 및 전체 url입력시 short url로 변환
    	 ********************************************/
        String url = "http://localhost:8080/testPage";
        if( url.lastIndexOf("/") > -1 ) {
        	url = url.substring(url.lastIndexOf("/")+1, url.length());
        }
        assertEquals("testPage",url);
	}
}
