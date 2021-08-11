package com.dummy.myerp.model.test;

import com.dummy.myerp.model.bean.SequenceEcritureComptable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class SequenceEcritureComptableTest
{
    private SequenceEcritureComptable sequenceEcritureComptable1;
    private SequenceEcritureComptable sequenceEcritureComptable2;

    @Before
    public void init()
    {
        sequenceEcritureComptable1 = new SequenceEcritureComptable();

        sequenceEcritureComptable1.setAnnee(2020);
        sequenceEcritureComptable1.setDerniereValeur(36500);

        sequenceEcritureComptable2 = new SequenceEcritureComptable(2021, 47800);
    }

    @Test
    public void sequenceEcritureComptable1()
    {
        Assert.assertEquals(sequenceEcritureComptable1.toString(), "SequenceEcritureComptable{annee=2020, derniereValeur=36500}");
    }

    @Test
    public void sequenceEcritureComptable2()
    {
        Assert.assertEquals(sequenceEcritureComptable2.toString(), "SequenceEcritureComptable{annee=2021, derniereValeur=47800}");
    }
}