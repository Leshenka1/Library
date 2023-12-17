package com.zas.JPA;

import com.zas.JPA.ReaderJPA;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ReaderJPA.class)
public class ReaderJPA_ {
    public static volatile SingularAttribute<ReaderJPA, Integer> readerID;
    public static volatile SingularAttribute<ReaderJPA, Boolean> hasOverdueFees;
    public static volatile SingularAttribute<ReaderJPA, Integer> feesDays;
}