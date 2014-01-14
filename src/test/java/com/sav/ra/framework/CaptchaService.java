package com.sav.ra.framework;

import java.util.Random;

public class CaptchaService {
	
	public static String getCaptchaValue(){
		
		Random rand=new Random(100000);
		return String.valueOf(rand.nextLong());
	}

}
