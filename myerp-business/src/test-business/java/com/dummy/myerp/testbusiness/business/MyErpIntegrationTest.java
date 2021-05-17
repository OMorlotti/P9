package com.dummy.myerp.testbusiness.business;

import com.dummy.myerp.business.impl.manager.ComptabiliteManagerImpl;
import com.dummy.myerp.model.bean.CompteComptable;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MyErpIntegrationTest extends BusinessTestCase
{
	private ComptabiliteManagerImpl comptabiliteManager = new ComptabiliteManagerImpl();

	@Test
	public void getListCompteComptableTest()
	{
		List<CompteComptable> compteComptableList = comptabiliteManager.getListCompteComptable();
		Assert.assertFalse(compteComptableList.isEmpty());
	}
}
