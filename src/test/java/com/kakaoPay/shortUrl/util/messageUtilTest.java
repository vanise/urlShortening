package com.kakaoPay.shortUrl.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum messageUtilTest {
	SUC_0001("SUC_0001","Success! 정상적으로 Short URL을 변환 했습니다."),
	SUC_0002("SUC_0002","Success! 정상적으로  URL을 변환 했습니다."),
	
	ERR_0001("ERR_0001","Failed! 더이상 Shortening URL을 생성할 수 없습니다."),
	ERR_0002("ERR_0002","Failed! 존재하지 않는 Short URL 입니다."),
	ERR_0003("ERR_0002","Failed! 알수없는 오류가 발생 하였습니다."),
	;
	
	private final String code;
	private final String message;

	public static String getByCode(final String code) {
		for (final messageUtilTest e : values()) {
			if (e.code.equals(code))
				return e.message;
		}
		return messageUtilTest.ERR_0003.message;
	}
}
