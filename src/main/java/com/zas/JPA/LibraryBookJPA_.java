package com.zas.JPA;


import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(LibraryBookJPA.class)
public class LibraryBookJPA_ {
    public static volatile SingularAttribute<LibraryBookJPA, Integer> bookID;
    public static volatile SingularAttribute<LibraryBookJPA, String> title;
    public static volatile SingularAttribute<LibraryBookJPA, String> author;
    public static volatile SingularAttribute<LibraryBookJPA, Integer> availableCopies;
    public static volatile SingularAttribute<LibraryBookJPA, Integer> readerID;
}