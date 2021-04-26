package com.dummy.myerp.model.bean.comptabilite;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class CompteComptableTest
{
    private List<CompteComptable> listCompteComptable = new ArrayList<>();

    @Before
    public void init()
    {
        listCompteComptable.add(new CompteComptable(1357, "Compte 1"));
        listCompteComptable.add(new CompteComptable(2468, "Compte 2"));
        listCompteComptable.add(new CompteComptable(null, "Compte 3"));
    }

    @Test
    public void compteComptableTest()
    {
        Assert.assertEquals(listCompteComptable.get(0).toString(), "CompteComptable{numero=1357, libelle='Compte 1'}");

        Assert.assertEquals(listCompteComptable.get(1).toString(), "CompteComptable{numero=2468, libelle='Compte 2'}");

        Assert.assertEquals(listCompteComptable.get(2).toString(), "CompteComptable{numero=null, libelle='Compte 3'}");
    }

    @Test
    public void getByNumeroTest()
    {
        CompteComptable compteComptable = CompteComptable.getByNumero(listCompteComptable,2468);

        Assert.assertSame(compteComptable, listCompteComptable.get(1));
    }

    @Test
    public void getByNumeroNullTest()
    {
        CompteComptable compteComptable = CompteComptable.getByNumero(listCompteComptable,null);

        Assert.assertSame(compteComptable, listCompteComptable.get(2));
    }
}