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
import java.math.RoundingMode;
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

	@Test
	public void ecritureComptableCheckSuccess()
	{
		Date dateEnCours = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		String yearRef = simpleDateFormat.format(dateEnCours);

		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setId(123456789);
		vEcritureComptable.setJournal(new JournalComptable("VE", "Vente"));
		vEcritureComptable.setReference("VE-" + yearRef + "/00006");
		vEcritureComptable.setDate(dateEnCours);
		vEcritureComptable.setLibelle("Libellé de test");

		vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(
			new CompteComptable(411), "Libellé de test", new BigDecimal(456), null)
		);
		vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(
			new CompteComptable(706), "Libellé de test", null, new BigDecimal(456))
		);

		try
		{
			comptabiliteManager.checkEcritureComptable(vEcritureComptable);

			Assert.assertTrue(true);
		}
		catch(FunctionalException e)
		{
			Assert.fail();
		}
	}

	@Test(expected = FunctionalException.class)
	public void ecritureComptableCheckFail() throws FunctionalException
	{
		Date dateEnCours = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		String yearRef = simpleDateFormat.format(dateEnCours);

		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setJournal(new JournalComptable("VE", "Vente"));
		//vEcritureComptable.setReference("VE-" + yearRef + "/00006"); // On commente par exemple la référence
		vEcritureComptable.setDate(dateEnCours);
		vEcritureComptable.setLibelle("Libellé de test");

		vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(
			new CompteComptable(411), null, new BigDecimal(456), null)
		);
		vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(
			new CompteComptable(706), null, null, new BigDecimal(456))
		);

		comptabiliteManager.checkEcritureComptable(vEcritureComptable);

		Assert.fail();
	}

	@Test
	public void ecritureComptableAdd()
	{
		Date dateEnCours = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		String yearRef = simpleDateFormat.format(dateEnCours);

		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setId(123456789);
		vEcritureComptable.setJournal(new JournalComptable("VE", "Vente"));
		vEcritureComptable.setReference("VE-" + yearRef + "/00006");
		vEcritureComptable.setDate(dateEnCours);
		vEcritureComptable.setLibelle("Libellé de test");

		vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(
			new CompteComptable(411), "Libellé de test", new BigDecimal(456), null)
		);
		vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(
			new CompteComptable(706), "Libellé de test", null, new BigDecimal(456))
		);

		try
		{
			comptabiliteManager.insertEcritureComptable(vEcritureComptable);

			Assert.assertTrue(true);

			/**/

			List<EcritureComptable> ecritureComptableList = comptabiliteManager.getListEcritureComptable();

			BigDecimal vRetour = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
			vRetour = vRetour.add(new BigDecimal(250));

			for (EcritureComptable ecritureComptable: ecritureComptableList)
			{
				if(ecritureComptable.getId() == 123456789)
				{
					Assert.assertEquals("Libellé de test", ecritureComptable.getLibelle());
					Assert.assertEquals("VE-" + yearRef + "/00006", ecritureComptable.getReference());

					for (LigneEcritureComptable ligneEcritureComptable : ecritureComptable.getListLigneEcriture()) {
						if (ligneEcritureComptable.getCredit() != null){
							Assert.assertEquals(vRetour, ligneEcritureComptable.getCredit());
						}
						if (ligneEcritureComptable.getDebit() != null){
							Assert.assertEquals(vRetour, ligneEcritureComptable.getDebit());
						}
					}
				}
			}

			comptabiliteManager.deleteEcritureComptable(vEcritureComptable.getId());
		}
		catch(FunctionalException e)
		{
			Assert.fail();
		}
	}

	@Test
	public void ecritureComptableUpdate()
	{
		Date dateEnCours = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		String yearRef = simpleDateFormat.format(dateEnCours);

		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setId(123456789);
		vEcritureComptable.setJournal(new JournalComptable("VE", "Vente"));
		vEcritureComptable.setReference("VE-" + yearRef + "/00006");
		vEcritureComptable.setDate(dateEnCours);
		vEcritureComptable.setLibelle("Libellé de test");

		vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(
			new CompteComptable(411), "Libellé de test", new BigDecimal(456), null)
		);
		vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(
			new CompteComptable(706), "Libellé de test", null, new BigDecimal(456))
		);

		try
		{
			comptabiliteManager.insertEcritureComptable(vEcritureComptable);

			Assert.assertTrue(true);

			List<EcritureComptable> ecritureComptableList1 = comptabiliteManager.getListEcritureComptable();

			for (EcritureComptable ecritureComptable1 : ecritureComptableList1)
			{
				if(ecritureComptable1.getId() == 123456789)
				{
					ecritureComptable1.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(411),
						null, new BigDecimal(250),
						null));
					ecritureComptable1.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(706),
						null, null,
						new BigDecimal(250)));

					comptabiliteManager.updateEcritureComptable(ecritureComptable1);

					Assert.assertTrue(true);

					/**/

					List<EcritureComptable> ecritureComptableList2 = comptabiliteManager.getListEcritureComptable();

					BigDecimal vRetour = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
					vRetour = vRetour.add(new BigDecimal(250));

					for(EcritureComptable ecritureComptable2: ecritureComptableList2)
					{
						if(ecritureComptable2.getId() == 123456789)
						{
							Assert.assertEquals("Libellé de test", ecritureComptable2.getLibelle());
							Assert.assertEquals("VE-" + yearRef + "/00006", ecritureComptable2.getReference());

							for(LigneEcritureComptable ligneEcritureComptable : ecritureComptable2.getListLigneEcriture())
							{
								if(ligneEcritureComptable.getCredit() != null)
								{
									Assert.assertEquals(vRetour, ligneEcritureComptable.getCredit());
								}
								if(ligneEcritureComptable.getDebit() != null)
								{
									Assert.assertEquals(vRetour, ligneEcritureComptable.getDebit());
								}
							}
						}
					}
				}
			}

			comptabiliteManager.deleteEcritureComptable(vEcritureComptable.getId());

			Assert.assertTrue(true);
		}
		catch(FunctionalException e)
		{
			Assert.fail();
		}
	}

	@Test
	public void checkDeleteEcritureComptable()
	{
		Date dateEnCours = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		String yearRef = simpleDateFormat.format(dateEnCours);

		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setId(123456789);
		vEcritureComptable.setJournal(new JournalComptable("VE", "Vente"));
		vEcritureComptable.setReference("VE-" + yearRef + "/00006");
		vEcritureComptable.setDate(dateEnCours);
		vEcritureComptable.setLibelle("Libellé de test");

		vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(
			new CompteComptable(411), "Libellé de test", new BigDecimal(456), null)
		);
		vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(
			new CompteComptable(706), "Libellé de test", null, new BigDecimal(456))
		);

		try
		{
			comptabiliteManager.insertEcritureComptable(vEcritureComptable);

			comptabiliteManager.deleteEcritureComptable(vEcritureComptable.getId());

			List<EcritureComptable> ecritureComptableList = comptabiliteManager.getListEcritureComptable();

			for(EcritureComptable ecritureComptable: ecritureComptableList)
			{
				if(ecritureComptable.getId() == 123456789)
				{
					Assert.fail();
				}
			}
		}
		catch(FunctionalException e)
		{
			Assert.fail();
		}
	}
}
