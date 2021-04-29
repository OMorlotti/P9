package com.dummy.myerp.consumer.test;

import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.model.bean.*;

import com.dummy.myerp.technical.exception.NotFoundException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.List;

public class DaoTest
{
	private static final String APPLICATION_CONTEXT_XML = "classpath:/com/dummy/myerp/consumer/test/applicationContext.xml";

	private static DaoProxy daoProxy;

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


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
	TESTS ENTITÉS LIÉES : SÉQUENCE ÉCRITURE COMPTABLE + LIGNE ÉCRITURE COMPTABLE
	 */

	@Test
	public void ligneEcritureComptableTest() throws NotFoundException
	{
		List<LigneEcritureComptable> list = daoProxy.getComptabiliteDao().getEcritureComptableByRef("AC-2016/00001").getListLigneEcriture();

		LigneEcritureComptable ligneEcritureComptable1 = list.get(0);
		Assert.assertTrue(ligneEcritureComptable1.getCompteComptable().getNumero() == 606);

		LigneEcritureComptable ligneEcritureComptable2 = list.get(1);
		Assert.assertTrue(ligneEcritureComptable2.getCompteComptable().getNumero() == 4456);

		LigneEcritureComptable ligneEcritureComptable3 = list.get(2);
		Assert.assertFalse(ligneEcritureComptable3.getCompteComptable().getNumero() == 403);
	}
}
