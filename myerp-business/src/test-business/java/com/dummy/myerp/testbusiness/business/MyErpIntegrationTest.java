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

	@Test
	public void ecritureComptableCheckSuccess() throws FunctionalException
	{
		Date dateEnCours = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		String yearRef = simpleDateFormat.format(dateEnCours);

		EcritureComptable vEcritureComptable = new EcritureComptable();
		vEcritureComptable.setId(456646321);
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

		comptabiliteManager.insertEcritureComptable(vEcritureComptable);

		comptabiliteManager.updateEcritureComptable(vEcritureComptable);

		comptabiliteManager.deleteEcritureComptable(vEcritureComptable.getId());
	}

	@Test(expected = FunctionalException.class)
	public void ecritureComptableCheckError() throws FunctionalException
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

		comptabiliteManager.updateEcritureComptable(vEcritureComptable);
	}

	@Test
	public void ecritureComptableUpdate() throws FunctionalException
	{
		List<EcritureComptable> ecrituresComptable = comptabiliteManager.getListEcritureComptable();

		if(!ecrituresComptable.isEmpty())
		{
			comptabiliteManager.updateEcritureComptable(ecrituresComptable.get(0));
		}
	}
}