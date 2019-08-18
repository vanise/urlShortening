package com.kakaoPay.shortUrl.repository;


import org.springframework.stereotype.Component;

import com.kakaoPay.shortUrl.vo.ShortUrlVO;

@Component
public interface UrlshorteningDAO {
	
	/**
	 * com.kakaoPay.shortUrl.repository
	 * UrlshorteningDAO.java.insertUrl
	 * @title 변경된 url 저장
	 */
	public int insertUrl(ShortUrlVO vo);
	
	/**
	 * com.kakaoPay.shortUrl.repository
	 * UrlshorteningDAO.java.selectUrl
	 * @title url 조회
	 */
	public ShortUrlVO selectUrl(ShortUrlVO vo);

}