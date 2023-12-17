package com.zas.JPA;


import jakarta.persistence.*;

@Entity
@Table(name = "readers")
@NamedQuery(name = "Reader.findReadersWithOverdueFees",
        query = "SELECT r FROM ReaderJPA r WHERE r.hasOverdueFees = true AND r.feesDays > 30")
public class ReaderJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int readerID;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "hasOverdueFees")
    private boolean hasOverdueFees;

    @Column(name = "feesDays")
    private int feesDays;

    public int getReaderID() {
        return readerID;
    }

    public void setReaderID(int readerID) {
        this.readerID = readerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isHasOverdueFees() {
        return hasOverdueFees;
    }

    public void setHasOverdueFees(boolean hasOverdueFees) {
        this.hasOverdueFees = hasOverdueFees;
    }

    public int getFeesDays() {
        return feesDays;
    }

    public void setFeesDays(int feesDays) {
        this.feesDays = feesDays;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "readerID=" + readerID +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", hasOverdueFees=" + hasOverdueFees +
                ", feesDays=" + feesDays +
                '}';
    }
}