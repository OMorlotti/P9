package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;


public class SequenceEcritureComptableTest
{
    private SequenceEcritureComptable sequenceEcritureComptable;

    @Before
    public void init()
    {
        sequenceEcritureComptable = new SequenceEcritureComptable();

        sequenceEcritureComptable.setAnnee(2021);
        sequenceEcritureComptable.setDerniereValeur(36500);
    }

    @Test
    public void sequenceEcritureComptable()
    {
        Assert.assertEquals(sequenceEcritureComptable.toString(), "SequenceEcritureComptable{annee=2021, derniereValeur=36500}");
    }
}