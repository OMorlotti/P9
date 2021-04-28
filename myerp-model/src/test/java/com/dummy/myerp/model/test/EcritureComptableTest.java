package com.dummy.myerp.model.test;

import java.math.BigDecimal;

import com.dummy.myerp.model.bean.CompteComptable;
import com.dummy.myerp.model.bean.EcritureComptable;
import com.dummy.myerp.model.bean.LigneEcritureComptable;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Assert;
import org.junit.Test;


public class EcritureComptableTest {

    private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);

        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO).subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO))
                                                                            .toPlainString()
        ;

        LigneEcritureComptable vRetour = new LigneEcritureComptable(
            new CompteComptable(pCompteComptableNumero),
            vLibelle,
            vDebit,
            vCredit
        );

        return vRetour;
    }

    @Test
    public void referenceFormatTest()
    {
        String pattern = EcritureComptable.REFERENCE_PATTERN;

        EcritureComptable vEcriture = new EcritureComptable();

        vEcriture.setReference("BQ-2016/00001");
        Assert.assertTrue(vEcriture.getReference().matches(pattern));

        vEcriture.setReference("B9-2016/00001");
        Assert.assertFalse(vEcriture.getReference().matches(pattern));
    }

    @Test
    public void getTotalDebitTest()
    {
        EcritureComptable vEcriture = new EcritureComptable();

        vEcriture.setLibelle("Totaux");

        vEcriture.getListLigneEcriture().add(this.createLigne(1, "300", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "202.50", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, null));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "300", null));

        BigDecimal totalDebit = vEcriture.getTotalDebit();

        Assert.assertTrue(totalDebit.compareTo( new BigDecimal(802.50)) == 0);
    }

    @Test
    public void getTotalCreditTest()
    {
        EcritureComptable vEcriture = new EcritureComptable();

        vEcriture.setLibelle("Totaux");

        vEcriture.getListLigneEcriture().add(this.createLigne(1, null, "120"));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, null, "10"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "1"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, null));

        BigDecimal totalCredit = vEcriture.getTotalCredit();

        Assert.assertTrue(totalCredit.compareTo( new BigDecimal(131)) == 0);
    }

    @Test
    public void isEquilibree() {
        EcritureComptable vEcriture = new EcritureComptable();

        vEcriture.setLibelle("Equilibrée");

        vEcriture.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "301"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "40", "7"));
        Assert.assertTrue(vEcriture.toString(), vEcriture.isEquilibree());

        vEcriture.getListLigneEcriture().clear();

        vEcriture.setLibelle("Non équilibrée");

        vEcriture.getListLigneEcriture().add(this.createLigne(1, "10", null));
        vEcriture.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, null, "30"));
        vEcriture.getListLigneEcriture().add(this.createLigne(2, "1", "2"));
        Assert.assertFalse(vEcriture.toString(), vEcriture.isEquilibree());
    }
}