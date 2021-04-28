package com.dummy.myerp.model.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;


public class LigneEcritureComptableTest
{
    private LigneEcritureComptable ligneEcritureComptable;

    @Before
    public void init()
    {
        ligneEcritureComptable = new LigneEcritureComptable();

        ligneEcritureComptable.setCompteComptable(new CompteComptable(666, "Consommables"));
        ligneEcritureComptable.setLibelle("Rame papier");
        ligneEcritureComptable.setDebit(new BigDecimal(100));
        ligneEcritureComptable.setCredit(new BigDecimal(50));
    }

    @Test
    public void ligneEcritureComptableTest()
    {
        Assert.assertEquals(ligneEcritureComptable.toString(), "LigneEcritureComptable{compteComptable=CompteComptable{numero=666, libelle='Consommables'}, libelle='Rame papier', debit=100, credit=50}");
    }
}