package com.dummy.myerp.consumer.test;

import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAppTest
{
	private static final String APPLICATION_CONTEXT_XML_1 = "classpath:/com/dummy/myerp/consumer/applicationContext.xml";
	private static final String APPLICATION_CONTEXT_XML_2 = "classpath:/com/dummy/myerp/consumer/test/applicationContext.xml";

	@Test
	public void springAppTest1()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML_1);

		DaoProxy daoProxy = (DaoProxy) applicationContext.getBean("DaoProxy");

		Assert.assertNotNull(daoProxy);
	}

	@Test
	public void springAppTest2()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML_2);

		DaoProxy daoProxy = (DaoProxy) applicationContext.getBean("DaoProxy");

		Assert.assertNotNull(daoProxy);
	}
}
