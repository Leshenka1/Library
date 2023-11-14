package models;

public class Reader {
    private String name;
    private int age;
    private int id;
    private boolean hasOverdueFees;
    private int feesDays;

    public Reader(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.id = builder.id;
        this.hasOverdueFees = false;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int getId() {
        return this.id;
    }

    public int getFeesDays() {
        return this.feesDays;
    }

    public void setFeesDays(int feesDays) {
        this.feesDays = feesDays;
    }

    public boolean getHasOverdueFees() {
        return this.hasOverdueFees;
    }

    public void setHasOverdueFees(boolean hasOverdueFees) {
        this.hasOverdueFees = hasOverdueFees;
    }

    public String toString() {
        return "Name: " + this.name + ", age: " + this.age + ", Id: " + this.id + ", feesDays: " + this.feesDays;
    }

    public static class Builder {
        private String name;
        private int age;
        private int id;

        public Builder(String name, int age, int id) {
            this.name = name;
            this.age = age;
            this.id = id;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Reader build() {
            return new Reader(this);
        }
    }
}
