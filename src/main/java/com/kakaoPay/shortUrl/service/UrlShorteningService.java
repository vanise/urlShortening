package com.kakaoPay.shortUrl.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.kakaoPay.shortUrl.vo.ShortUrlVO;

@Service
public interface UrlShorteningService {

	public int insertUrl(ShortUrlVO vo );
	
	public ShortUrlVO getUrl(ShortUrlVO vo);

	public String changeUrlToShorteingUrl(int urlToHash,ShortUrlVO vo);

}

