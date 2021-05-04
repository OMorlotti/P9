package com.dummy.myerp.consumer.test;

import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.model.bean.*;

import com.dummy.myerp.technical.exception.NotFoundException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DaoTest
{
	private static final String APPLICATION_CONTEXT_XML = "classpath:/com/dummy/myerp/consumer/test/applicationContext.xml";

	private static DaoProxy daoProxy;

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private static SimpleDateFormat simpleYearFormat = new SimpleDateFormat("yyyy");

	@BeforeClass
	public static void iniTests()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);

		daoProxy = (DaoProxy) applicationContext.getBean("DaoProxy");
	}

	/*
	TESTS DES OPÉRATIONS GET ALL POUR CHAQUE ENTITÉ
	 */

	@Test
	public void compteComptableNotEmpyTest()
	{
		List<CompteComptable> list = daoProxy.getComptabiliteDao().getListCompteComptable();

		Assert.assertFalse(list.isEmpty());
	}

	@Test
	public void ecritureComptableNotEmptyTest()
	{
		List<EcritureComptable> list = daoProxy.getComptabiliteDao().getListEcritureComptable();

		Assert.assertFalse(list.isEmpty());
	}

	@Test
	public void journalComptableNotEmptyTest()
	{
		List<JournalComptable> list = daoProxy.getComptabiliteDao().getListJournalComptable();

		Assert.assertFalse(list.isEmpty());
	}

	@Test
	public void sequenceEcritureComptableNotEmptyTest()
	{
		List<SequenceEcritureComptable> list = daoProxy.getComptabiliteDao().getListSequenceEcritureComptable();

		Assert.assertFalse(list.isEmpty());
	}

	/*
	TESTS D'OPÉRATIONS DE LECTURE DES ENTITÉS
	 */

	@Test
	public void compteComptableReadableTest()
	{
		List<CompteComptable> list = daoProxy.getComptabiliteDao().getListCompteComptable();

		CompteComptable compteComptable = CompteComptable.getByNumero(list, 512);

		Assert.assertEquals("Banque", compteComptable.getLibelle());
		Assert.assertTrue(512 == compteComptable.getNumero());
	}

	@Test
	public void ecritureComtableReadableTest() throws NotFoundException
	{
		EcritureComptable ecritureComptable = daoProxy.getComptabiliteDao().getEcritureComptableByRef("BQ-2016/00003");

		Assert.assertEquals("BQ", ecritureComptable.getJournal().getCode());
		Assert.assertEquals("BQ-2016/00003", ecritureComptable.getReference());
		Assert.assertEquals("2016-12-29", simpleDateFormat.format(ecritureComptable.getDate()));
		Assert.assertEquals("Paiement Facture F110001", ecritureComptable.getLibelle());
	}

	@Test
	public void journalComptableReadingTest()
	{
		List<JournalComptable> list = daoProxy.getComptabiliteDao().getListJournalComptable();

		JournalComptable journalComptable = JournalComptable.getByCode(list, "VE");

		Assert.assertEquals("VE", journalComptable.getCode());
		Assert.assertEquals("Vente", journalComptable.getLibelle());
	}

	@Test
	public void sequenceEcritureComptableTest() throws NotFoundException
	{
		SequenceEcritureComptable sequenceEcritureComptable = daoProxy.getComptabiliteDao().getSequenceEcritureComptable("VE", 2016);

		Assert.assertEquals("VE", sequenceEcritureComptable.getJournalCode());
		Assert.assertTrue(sequenceEcritureComptable.getAnnee() == 2016);
		Assert.assertTrue(sequenceEcritureComptable.getDerniereValeur() == 41);
	}

	/*
	TESTS ENTITÉS LIÉES : LIGNE ÉCRITURE COMPTABLE + LIGNE ÉCRITURE COMPTABLE
	 */

	@Test
	public void ligneEcritureComptableToEcritureComptableTest() throws NotFoundException
	{
		List<LigneEcritureComptable> list = daoProxy.getComptabiliteDao().getEcritureComptableByRef("AC-2016/00001").getListLigneEcriture();

		LigneEcritureComptable ligneEcritureComptable1 = list.get(0);
		Assert.assertTrue(ligneEcritureComptable1.getCompteComptable().getNumero() == 606);

		LigneEcritureComptable ligneEcritureComptable2 = list.get(1);
		Assert.assertTrue(ligneEcritureComptable2.getCompteComptable().getNumero() == 4456);

		LigneEcritureComptable ligneEcritureComptable3 = list.get(2);
		Assert.assertFalse(ligneEcritureComptable3.getCompteComptable().getNumero() == 403);
	}

	@Test
	public void ecritureComptableToJournalComptableTest() throws NotFoundException
	{
		JournalComptable journal1 = daoProxy.getComptabiliteDao().getEcritureComptableByRef("AC-2016/00001").getJournal();

		Assert.assertEquals("AC", journal1.getCode());
		Assert.assertEquals("Achat", journal1.getLibelle());

		JournalComptable journal2 = daoProxy.getComptabiliteDao().getEcritureComptableByRef("VE-2016/00004").getJournal();

		Assert.assertEquals("VE", journal2.getCode());
		Assert.assertEquals("Vente", journal2.getLibelle());
	}

	@Test
	public void ligneEcritureComptableToCompteComptableTest() throws NotFoundException
	{
		List<LigneEcritureComptable> list = daoProxy.getComptabiliteDao().getEcritureComptableByRef("AC-2016/00001").getListLigneEcriture();

		LigneEcritureComptable ligneEcritureComptable = list.get(0);

		Assert.assertTrue(ligneEcritureComptable.getCompteComptable().getNumero() == 606);

		CompteComptable compteComptable = ligneEcritureComptable.getCompteComptable();

		Assert.assertTrue(compteComptable.getNumero() == 606);
		Assert.assertEquals("Achats non stockés de matières et fournitures", compteComptable.getLibelle());
	}

	/*
	TESTS EN CRÉATION/ÉDITION ET SUPPRESSION
	 */

	private EcritureComptable createEcritureComptable()
	{
		List<JournalComptable> list = daoProxy.getComptabiliteDao().getListJournalComptable();

		JournalComptable journalComptable = JournalComptable.getByCode(list, "VE");

		/**/

		EcritureComptable ecritureComptable = new EcritureComptable();

		ecritureComptable.setJournal(journalComptable);
		ecritureComptable.setLibelle("Test");
		ecritureComptable.setDate(new Date());
		ecritureComptable.setReference(ecritureComptable.getJournal().getCode() + "-" + simpleYearFormat.format(new Date()) + "/99999");

		ecritureComptable.getListLigneEcriture().add(
			new LigneEcritureComptable(
				new CompteComptable(401),
				"Test 1",
				new BigDecimal(135),
				null
			)
		);

		ecritureComptable.getListLigneEcriture().add(
			new LigneEcritureComptable(
				new CompteComptable(411),
				"Test 2",
				null,
				new BigDecimal(246)
			)
		);

		return ecritureComptable;
	}

	@Test
	public void insertEcritureComptableTest()
	{
		EcritureComptable ecritureComptable = createEcritureComptable();

		daoProxy.getComptabiliteDao().insertEcritureComptable(ecritureComptable);

		/**/

		Assert.assertNotNull(ecritureComptable.getId());

		daoProxy.getComptabiliteDao().deleteEcritureComptable(ecritureComptable.getId());
	}

	@Test
	public void updateEcritureComptable() throws NotFoundException
	{
		EcritureComptable ecritureComptable = createEcritureComptable();

		daoProxy.getComptabiliteDao().insertEcritureComptable(ecritureComptable);

		int id = ecritureComptable.getId();
		String reference = ecritureComptable.getReference();

		/**/

		ecritureComptable = daoProxy.getComptabiliteDao().getEcritureComptableByRef(reference);

		ecritureComptable.setLibelle("Test BIS");

		daoProxy.getComptabiliteDao().updateEcritureComptable(ecritureComptable);

		/**/

		ecritureComptable = daoProxy.getComptabiliteDao().getEcritureComptableByRef(reference);

		Assert.assertEquals("Test BIS", ecritureComptable.getLibelle());

		daoProxy.getComptabiliteDao().deleteEcritureComptable(id);
	}

	@Test
	public void deleteEcritureComptable() throws NotFoundException
	{
		EcritureComptable ecritureComptable = createEcritureComptable();

		daoProxy.getComptabiliteDao().insertEcritureComptable(ecritureComptable);

		int id = ecritureComptable.getId();
		String reference = ecritureComptable.getReference();

		/**/

		daoProxy.getComptabiliteDao().deleteEcritureComptable(id);

		/**/

		try
		{
			daoProxy.getComptabiliteDao().getEcritureComptableByRef(reference);

			Assert.fail();
		}
		catch(NotFoundException e)
		{
			/* success */
		}
	}

	private SequenceEcritureComptable createSequenceEcritureComptable()
	{
		SequenceEcritureComptable sequenceEcritureComptable = new SequenceEcritureComptable();

		sequenceEcritureComptable.setJournalCode("AC");
		sequenceEcritureComptable.setAnnee(2021);
		sequenceEcritureComptable.setDerniereValeur(99);

		return sequenceEcritureComptable;
	}

	@Test
	public void insertSequenceEcritureComptableTest() throws NotFoundException
	{
		SequenceEcritureComptable sequenceEcritureComptable = createSequenceEcritureComptable();

		daoProxy.getComptabiliteDao().insertSequenceEcritureComptable(sequenceEcritureComptable);

		/**/

		SequenceEcritureComptable lastSequenceEcritureComptable = daoProxy.getComptabiliteDao().getSequenceEcritureComptable("AC", 2021);

		Assert.assertEquals("AC", sequenceEcritureComptable.getJournalCode());
		Assert.assertTrue(lastSequenceEcritureComptable.getAnnee() == 2021);
		Assert.assertTrue(lastSequenceEcritureComptable.getDerniereValeur() == 99);

		daoProxy.getComptabiliteDao().deleteSequenceEcritureComptable("AC", 2021);
	}

	@Test
	public void updateSequenceEcritureComptableTest() throws NotFoundException {

		SequenceEcritureComptable sequenceEcritureComptable = createSequenceEcritureComptable();

		daoProxy.getComptabiliteDao().insertSequenceEcritureComptable(sequenceEcritureComptable);

		/**/

		SequenceEcritureComptable lastSequenceEcritureComptable1 = daoProxy.getComptabiliteDao().getSequenceEcritureComptable("AC", 2021);

		lastSequenceEcritureComptable1.setDerniereValeur(100);

		daoProxy.getComptabiliteDao().updateSequenceEcritureComptable(lastSequenceEcritureComptable1);

		/**/

		SequenceEcritureComptable lastSequenceEcritureComptable2 = daoProxy.getComptabiliteDao().getSequenceEcritureComptable("AC", 2021);

		Assert.assertEquals("AC", sequenceEcritureComptable.getJournalCode());
		Assert.assertTrue(lastSequenceEcritureComptable2.getAnnee() == 2021);
		Assert.assertTrue(lastSequenceEcritureComptable2.getDerniereValeur() == 100);

		daoProxy.getComptabiliteDao().deleteSequenceEcritureComptable("AC", 2021);
	}
}
