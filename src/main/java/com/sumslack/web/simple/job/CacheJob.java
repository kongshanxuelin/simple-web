package com.sumslack.web.simple.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.hutool.cron.task.Task;
public class CacheJob implements Task{
	
	private static final Logger logger = LoggerFactory.getLogger(CacheJob.class);
	
	@Override
	public void execute() {
		logger.debug("Cache start...");
	}

}
