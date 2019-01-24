package com.sumslack.web.simple.job;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.googlecode.aviator.AviatorEvaluator;
import com.sumslack.web.simple.function.DateGreaterThan;
import com.sumslack.web.simple.function.DateLessThan;

import cn.hutool.cron.CronUtil;

@Component
public class JobRunner implements ApplicationRunner{

	static {
		CronUtil.setMatchSecond(true);
		CronUtil.start();
	}
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		AviatorEvaluator.addFunction(new DateGreaterThan());
		AviatorEvaluator.addFunction(new DateLessThan());
	}

}