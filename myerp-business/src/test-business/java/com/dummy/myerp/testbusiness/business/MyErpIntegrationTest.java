package com.dummy.myerp.testbusiness.business;

import com.dummy.myerp.business.impl.manager.ComptabiliteManagerImpl;
import com.dummy.myerp.model.bean.CompteComptable;
import com.dummy.myerp.model.bean.EcritureComptable;
import com.dummy.myerp.model.bean.JournalComptable;
import com.dummy.myerp.model.bean.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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

	@Test
	public void getListEcritureComptableTest()
	{
		List<EcritureComptable> ecritureComptableList = comptabiliteManager.getListEcritureComptable();
		Assert.assertFalse(ecritureComptableList.isEmpty());
	}

	@Test
	public void getListJournalComptable()
	{
		List<JournalComptable> journalComptableList = comptabiliteManager.getListJournalComptable();
		Assert.assertFalse(journalComptableList.isEmpty());
	}
	/*
	@Test
	public void ecritureComptable() throws FunctionalException
	{
		EcritureComptable vEcritureComptable = new EcritureComptable();

		vEcritureComptable.setJournal(new JournalComptable("AC", "Achats"));
		vEcritureComptable.setDate((new Date()));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		String yearRef = simpleDateFormat.format(vEcritureComptable.getDate());

		vEcritureComptable.setReference("AC-" + yearRef + "/0001");
		vEcritureComptable.setLibelle("Libellé");
		vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
			null, new BigDecimal(123), null));
		vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
			null, null, new BigDecimal(123)));

		comptabiliteManager.checkEcritureComptable(vEcritureComptable);
	}
/*
	@Test
	public void insertEcritureComptableReturnNoError() throws FunctionalException
	{
		EcritureComptable vEcrirureComptable = new EcritureComptable();
		vEcrirureComptable.setJournal(new JournalComptable("VE", "Vente"));
		vEcrirureComptable.setReference("VE-2021/00007");
		vEcrirureComptable.setDate(new Date());
		vEcrirureComptable.setLibelle("Test intégration vente");

		vEcrirureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(411),
			null, new BigDecimal(123), null));
		vEcrirureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(706),
			null, null, new BigDecimal(123)));

		comptabiliteManager.insertEcritureComptable(vEcrirureComptable);

		List<CompteComptable> ecritureComptableList = comptabiliteManager.getListCompteComptable();

		BigDecimal vRetour = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
		vRetour = vRetour.add(new BigDecimal(123));

		for (CompteComptable ecritureComptable: ecritureComptableList)
		{
			if (ecritureComptable.getReference().equals("VE-2021/00007"))
			{
				Assert.assertEquals(ecritureComptable.getLibelle(),"Test intégration vente");

				for (LigneEcritureComptable ligneEcritureComptable = ecritureComptable.getListLigneEcriture())
				{
					if (ligneEcritureComptable.getCredit() != null)
					{
						Assert.assertEquals(ligneEcritureComptable.getCredit(), vRetour);
					}
					if(ligneEcritureComptable.getDebit() != null)
					{
						Assert.assertEquals(ligneEcritureComptable.getDebit(), vRetour);
					}
				}
			}
		}
	}
	*/
}