package com.kakaoPay.shortUrl.vo;

import java.util.Date;
import lombok.Data;

@Data
public class ShortUrlVO {
	private String srtUrl;
	private String orgUrl;
	private Date regDt;
	
}