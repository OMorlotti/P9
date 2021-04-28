package com.dummy.myerp.model.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;


public class JournalComptableTest
{
    private List<JournalComptable> listJournalComptable = new ArrayList<>();

    @Before
    public void init()
    {
        listJournalComptable.add(new JournalComptable("AA", "Consommables divers"));
        listJournalComptable.add(new JournalComptable("BB", "Matériels divers"));
    }

    @Test
    public void journalComptableTest()
    {
        Assert.assertEquals(listJournalComptable.get(0).toString(), "JournalComptable{code='AA', libelle='Consommables divers'}");

        Assert.assertEquals(listJournalComptable.get(1).toString(), "JournalComptable{code='BB', libelle='Matériels divers'}");
    }

    @Test
    public void getByCodeTest()
    {
        JournalComptable journalComptable = JournalComptable.getByCode(listJournalComptable, "BB");

        Assert.assertSame(journalComptable, listJournalComptable.get(1));
    }
}