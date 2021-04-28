package com.dummy.myerp.model.test;

import com.dummy.myerp.model.bean.SequenceEcritureComptable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


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