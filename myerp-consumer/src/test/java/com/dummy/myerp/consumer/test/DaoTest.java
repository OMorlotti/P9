package com.dummy.myerp.consumer.test;

import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoTest
{
	private static final String APPLICATION_CONTEXT_XML = "classpath:/com/dummy/myerp/consumer/test/applicationContext.xml";

	private static DaoProxy daoProxy;

	@BeforeClass
	public static void iniTests()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);

		daoProxy = (DaoProxy) applicationContext.getBean("DaoProxy");
	}

	@Test
	public void journalTest()
	{
		daoProxy.getComptabiliteDao().getListCompteComptable();
	}
}
