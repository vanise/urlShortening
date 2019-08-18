package com.kakaoPay.shortUrl.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakaoPay.shortUrl.repository.UrlshorteningDAO;
import com.kakaoPay.shortUrl.util.UrlShorteningUtil;
import com.kakaoPay.shortUrl.util.messageUtil;
import com.kakaoPay.shortUrl.vo.ShortUrlVO;

@Service
public class UrlShorteningServiceImpl implements UrlShorteningService {
	@Autowired
	private UrlshorteningDAO urlshorteningDAO;
	
	@Transactional
	public int insertUrl(ShortUrlVO vo ) {
		return urlshorteningDAO.insertUrl(vo);
	}
	
	public ShortUrlVO getUrl(ShortUrlVO vo) {
		ShortUrlVO result = urlshorteningDAO.selectUrl(vo);
		return result;
	}
	
	public String changeUrlToShorteingUrl(int urlToHash,ShortUrlVO vo) {
		String result = "error";
		int orgHash = urlToHash;
		int orgSubHash = (int) (urlToHash/Math.pow(10, (((int)Math.log10(urlToHash)+1)-3)));
		
    	/**********************************************************************************
    	 * 1) 해쉬충돌을 방지하기위해 선형조사 기법을 사용한다.
    	 * 2) 52진수로 8자리 문자열의 버킷 사이즈는 너무크다. > String hashcode의 값만큼의 버킷사이즈로 선형조사를 실행한다
    	 * 3) 임의의 버킷사이즈가 모두 꽉찬다면 error를 반환한다.
    	 **********************************************************************************/
		boolean rstBraek = false;
		int subHashId = 0;
		int hashId = 0;
		for(int i = 0; i < orgSubHash; i++) {
			if(rstBraek) break;
			subHashId = i==0 ? orgSubHash : subHashId+1;
			for(int j = 0; j < orgHash; j++) {
				hashId = j==0 ? orgHash : hashId+1 ;
				if(0 > hashId) {
					hashId = 1;
				}
		    	/****************************************************************************
		    	 * 1) 52진수(a-zA-Z)로 변환해 8자리 문자열을 생성한다
		    	 ****************************************************************************/
				String urlResult = UrlShorteningUtil.UrlToKey(hashId,subHashId);
				
				vo.setSrtUrl(urlResult);
				ShortUrlVO urlVali = urlshorteningDAO.selectUrl(vo);				
				if(urlVali==null) {
					rstBraek = true;
					result = urlResult;
					break;
				}
			}
		}
    	/**********************************************************************************
    	 * 1) 생성된 short url을 DB에 저장
    	 **********************************************************************************/
		if(rstBraek) {
			int dbResult = urlshorteningDAO.insertUrl(vo);
		}
		return result;
	}
	
}

