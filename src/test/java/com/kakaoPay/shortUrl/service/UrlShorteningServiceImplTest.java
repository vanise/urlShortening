package com.kakaoPay.shortUrl.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.stereotype.Service;

import com.kakaoPay.shortUrl.util.UrlShorteningUtil;
import com.kakaoPay.shortUrl.util.messageUtilTest;

@Service
public class UrlShorteningServiceImplTest implements UrlShorteningServiceTest {
	
	@Override
	@Test
	public void changeUrlToShorteingUrlTest() {
		String result = "error";
    	/**********************************************************************************
    	 * 1) urlToHash = > String hashCode 10자리(임의로 설정) TEST를위해 2147483647-10한 수
    	 **********************************************************************************/
		int paramUrl = 2147483637;
		int orgHash = paramUrl;
		int orgSubHash = (int) (paramUrl/Math.pow(10, (((int)Math.log10(paramUrl)+1)-3)));
		
    	/**********************************************************************************
    	 * 1) 해쉬충돌을 방지하기위해 선형조사 기법을 사용한다.
    	 * 2) 52진수로 8자리 문자열의 버킷 사이즈는 너무크다. > String hashcode의 값만큼의 버킷사이즈로 선형조사를 실행한다
    	 * 3)  임의의 버킷사이즈가 모두 꽉찬다면 error를 반환한다.
    	 **********************************************************************************/
		boolean rstBraek = false;
		int subHashId = 0;
		int hashId = 0;
		for(int i = 0; i < orgSubHash; i++) {
			if(rstBraek) break;
			subHashId = i==0 ? orgSubHash : orgSubHash++;
			for(int j = 0; j < orgHash; j++) {
				hashId = j==0 ? orgHash : hashId+1 ;
				if(0 > hashId) {
					hashId = 1;
				}
		    	/****************************************************************************
		    	 * 1) 52진수(a-zA-Z)로 변환해 8자리 문자열을 생성한다
		    	 ****************************************************************************/
				String urlResult = UrlShorteningUtil.UrlToKey(hashId,subHashId);
				assertNotNull(urlResult);
				System.out.println("short url 생성 :" +urlResult);
				String urlVali = urlResult;
				
		    	/****************************************************************************
		    	 * 1) 임의로 100까지의 제한을 주고 테스트
		    	 ****************************************************************************/
				if(j > 100) {
					urlVali = null;
				}
				if(urlVali==null) {
					rstBraek = true;
					result = "success";
					break;
				}
			}
		}
	}
	
	@Test
	public void messageUtilTest() {
		String meg1 = messageUtilTest.getByCode("ERR_0001");
		assertNotNull(meg1);
		assertEquals(meg1,"Failed! 더이상 Shortening URL을 생성할 수 없습니다.");
	}
	
	
}

