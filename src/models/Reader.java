package models;

public class Reader {
    private String name;
    private int age;
    private int id;
    private boolean hasOverdueFees;
    private int feesDays;

    public Reader(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.hasOverdueFees = false;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public int getFeesDays() {
        return feesDays;
    }

    public void setFeesDays(int feesDays) {
        this.feesDays = feesDays;
    }

    public boolean hasOverdueFees() {
        return hasOverdueFees;
    }

    public void setOverdueFees(boolean hasOverdueFees) {
        this.hasOverdueFees = hasOverdueFees;
    }

    // Переопределение метода toString() для вывода информации о читателе
    @Override
    public String toString() {
        return "Name: " + name + ", age: " + age + ", Id: " + id + ", feesDays: " + feesDays;
    }
}
