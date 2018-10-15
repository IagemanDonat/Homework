package pedigree.entitys;

import java.util.List;

public class Human{
    private Human father;
    private Human mother;
    private List<Human> children;
    private long id;
    private String name;
    private String surname;
    private String sex;
    private boolean alive;
    private int age;

    public Human(long id, String name, String surname, String sex, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.age = age;
        alive= true;
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public List<Human> getChildren() {
        return children;
    }

    public void setChildren(List<Human> children) {
        this.children = children;
    }

    public String getSex(){
        return sex;
    }

    public long getId() {
        return id;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }


    public Boolean getAlive(){
        return alive;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public void printInfo(){
        System.out.println(name + " " + surname + " age: " + age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Human human = (Human) o;

        if (id != human.id) return false;
        if (age != human.age) return false;
        if (!name.equals(human.name)) return false;
        return surname.equals(human.surname);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + age;
        return result;
    }
}
