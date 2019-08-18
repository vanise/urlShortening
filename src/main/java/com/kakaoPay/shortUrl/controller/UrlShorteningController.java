package com.kakaoPay.shortUrl.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kakaoPay.shortUrl.service.UrlShorteningService;
import com.kakaoPay.shortUrl.util.messageUtil;
import com.kakaoPay.shortUrl.vo.ShortUrlVO;

@Controller
public class UrlShorteningController {
	private static final Logger logger = LoggerFactory.getLogger(UrlShorteningController.class);
	@Autowired
	private UrlShorteningService urlShorteningService;
	
	/**
	 * com.kakaoPay.shortUrl.controller
	 * UrlShorteningController.java.main
	 * @title main
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	public String main(Model model) {
		return "index";
	}
	
	/**
	 * com.kakaoPay.shortUrl.controller
	 * UrlShorteningController.java.urlShortening
	 * @title url to urlShortening
	 */
	@ResponseBody
	@RequestMapping(value="/urlConvert")
	public Map<String, Object> urlShortening(@ModelAttribute ShortUrlVO  vo) {
		Map<String, Object> map = new HashMap<String, Object>();
    	/***********************************************************************
    	 * URL Parameter
    	 ***********************************************************************/
        String url = vo.getOrgUrl();
        
    	/***********************************************************************
    	 * URL 검증 1단계 : 동일한 URL이 존재하는지 확인
    	 * 1 ) 동일한 URL 존재시 저장된 URL 반환 
    	 ***********************************************************************/
		vo.setOrgUrl(url); 
		ShortUrlVO result = urlShorteningService.getUrl(vo);
		if(result!=null) {
			map.put("resultCd", "success"); 
			map.put("msg",messageUtil.getByCode("SUC_0001")); 
			map.put("resultUrl",result.getSrtUrl()); 
			return map; 
		}
        
    	/***********************************************************************
    	 * 간단한 String 해쉬코드 생성 (최대 10자리)
    	 ***********************************************************************/
        int urlToHash = Math.abs(url.hashCode());
        
    	/***********************************************************************
    	 * 1) 52진수(a-zA-Z)를 이용해 shortUrl을 생성 (알파벳 대,소문자만 이용하기 위해 52진수를 채택)
    	 * 2) result가 error면 errorCd를 반환한다
    	 ***********************************************************************/
        String shortUrl = urlShorteningService.changeUrlToShorteingUrl(urlToHash,vo);
        if("error".equals(shortUrl)) {
        	map.put("resultCd", shortUrl);
        	map.put("msg", messageUtil.getByCode("ERR_0001"));
        	map.put("resultUrl", "");
        	return map;
        }
        
        map.put("resultCd", "success");
        map.put("msg", messageUtil.getByCode("SUC_0001"));
        map.put("resultUrl", shortUrl);
		return map;
	}
	
	/**
	 * com.kakaoPay.shortUrl.controller
	 * UrlShorteningController.java.getOrgUrl
	 * @title urlShortening to url
	 */
	@ResponseBody
	@RequestMapping(value="/shorteningUrlConvert")
	public Map<String, Object> getOrgUrl(@ModelAttribute ShortUrlVO  vo) {
		Map<String, Object> map = new HashMap<String, Object>();
    	/********************************************
    	 * URL Parameter 및 전체 url입력시 short url로 변환
    	 ********************************************/
        String url = vo.getSrtUrl();
        if( url.lastIndexOf("/") > -1 ) {
        	url = url.substring(url.lastIndexOf("/")+1, url.length());
        }
        
    	/********************************************
    	 * URL 검증 후 결과코드 및 결과 반환
    	 ********************************************/
        vo.setSrtUrl(url);
        ShortUrlVO result = urlShorteningService.getUrl(vo);
        if(result!=null) {
            map.put("resultCd", "success");
            map.put("msg", messageUtil.getByCode("SUC_0001"));
            map.put("resultUrl", result.getOrgUrl());
    		return map;
        }
        map.put("resultCd", "error");
        map.put("msg", messageUtil.getByCode("ERR_0002"));
        map.put("resultUrl", "");
		return map;
	}
	
	
	/**
	 * com.kakaoPay.shortUrl.controller
	 * UrlShorteningController.java.redirectUrl
	 * @title url redirect
	 */
	@RequestMapping(value="/{shortUrl}")
	public String redirectUrl(@PathVariable("shortUrl") String urlParam) throws UnsupportedEncodingException {
		ShortUrlVO vo = new ShortUrlVO();
    	/********************************************
    	 * 1) PathVariable 변수값 VO에 할당 후 original URL 조회
    	 * 2) orgUrl가 존재하지 않을시 error page로 전달
    	 * 3) orgUrl이 존재시 OrgUrl로 redirect
    	 ********************************************/
		vo.setSrtUrl(urlParam);
		ShortUrlVO orgUrl = urlShorteningService.getUrl(vo);
		if(orgUrl==null) {
			return "error/error";
		}
		
		return "redirect:"+orgUrl.getOrgUrl();
	}
}
