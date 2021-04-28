package com.dummy.myerp.consumer.test;

import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAppTest
{
	private static final String APPLICATION_CONTEXT_XML = "classpath:/com/dummy/myerp/consumer/applicationContext.xml";

	@Test
	public void springAppTest()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);

		DaoProxy daoProxy = (DaoProxy) applicationContext.getBean("DaoProxy");

		Assert.assertNotNull(daoProxy);
	}
}
