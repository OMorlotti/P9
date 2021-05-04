package com.dummy.myerp.consumer.dao.contrat;

import java.util.List;

import com.dummy.myerp.model.bean.CompteComptable;
import com.dummy.myerp.model.bean.EcritureComptable;
import com.dummy.myerp.model.bean.JournalComptable;
import com.dummy.myerp.model.bean.SequenceEcritureComptable;
import com.dummy.myerp.technical.exception.NotFoundException;


/**
 * Interface de DAO des objets du package Comptabilite
 */
public interface ComptabiliteDao {
    // ==================== LectureComptable ====================

    /**
     * Renvoie la liste des Comptes Comptables
     * @return {@link List}
     */
    List<CompteComptable> getListCompteComptable();


    /**
     * Renvoie la liste des Journaux Comptables
     * @return {@link List}
     */
    List<JournalComptable> getListJournalComptable();

    // ==================== EcritureComptable ====================

    /**
     * Renvoie la liste des Écritures Comptables
     * @return {@link List}
     */
    List<EcritureComptable> getListEcritureComptable();

    /**
     * Renvoie l'Écriture Comptable d'id {@code pId}.
     *
     * @param pId l'id de l'écriture comptable
     * @return {@link EcritureComptable}
     * @throws NotFoundException : Si l'écriture comptable n'est pas trouvée
     */
    EcritureComptable getEcritureComptable(Integer pId) throws NotFoundException;

    /**
     * Renvoie l'Écriture Comptable de référence {@code pRef}.
     *
     * @param pReference la référence de l'écriture comptable
     * @return {@link EcritureComptable}
     * @throws NotFoundException : Si l'écriture comptable n'est pas trouvée
     */
    EcritureComptable getEcritureComptableByRef(String pReference) throws NotFoundException;

    /**
     * Charge la liste des lignes d'écriture de l'écriture comptable {@code pEcritureComptable}
     *
     * @param pEcritureComptable -
     */
    void loadListLigneEcriture(EcritureComptable pEcritureComptable);

    /**
     * Insert une nouvelle écriture comptable.
     *
     * @param pEcritureComptable -
     */
    void insertEcritureComptable(EcritureComptable pEcritureComptable);

    /**
     * Met à jour l'écriture comptable.
     *
     * @param pEcritureComptable -
     */
    void updateEcritureComptable(EcritureComptable pEcritureComptable);

    /**
     * Supprime l'écriture comptable d'id {@code pId}.
     *
     * @param pId l'id de l'écriture
     */
    void deleteEcritureComptable(Integer pId);

    // ==================== SequenceEcritureComptable ====================

    /**
     * Renvoie la liste des Séquences d'écriture comptable
     * @return {@link List}
     */
    List<SequenceEcritureComptable> getListSequenceEcritureComptable();

    /**
     * Renvoi la Sequence d'écriture comptable correspondante au Journal comptable pour l'année donnée
     *
     * @param pJournalCode -
     * @param pAnnee -
     * @return {@link SequenceEcritureComptable}
     * @throws NotFoundException Si la séquence n'est pas trouvée
     */
    SequenceEcritureComptable getSequenceEcritureComptable(String pJournalCode, int pAnnee) throws NotFoundException;

    /**
     * Insert une nouvelle séquence d'écriture comptable.
     *
     * @param sequence -
     */
    void insertSequenceEcritureComptable(SequenceEcritureComptable sequence);

    /**
     * Met à jour la séquence de l'écriture comptable.
     *
     * @param sequence -
     */
    void updateSequenceEcritureComptable(SequenceEcritureComptable sequence);

    /**
     * Supprime la séquence d'écriture comptable de code journal {@code pJournalCode} et d'année {@code pAnnee}.
     *
     * @param pJournalCode le code journal de la séquence d'écriture
     * @param pAnnee l'année de la séquence d'écriture
     */
    public void deleteSequenceEcritureComptable(String pJournalCode, int pAnnee);
}
