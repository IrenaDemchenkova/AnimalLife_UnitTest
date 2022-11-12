package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Animal implements Serializable {
    private final int lostWeightAfterSleeping = 1;
    public String name;
    public int age;
    public int weight;
    public Activity activity = Activity.DOING_NOTHING;

    public Animal(String name, int age, int weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public static void saveAnimal(Animal animal) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(animal.getName() + ".dat")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(animal);
        }
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public boolean ifSleeping() {
        if (getActivity().equals(Activity.SLEEPING)) {
            System.out.format("%s is sleeping %n", getName());
            return true;
        } else {
            System.out.format("%s is not sleeping %n", getName());
            return false;
        }
    }

    public boolean ifEating() {
        if (getActivity().equals(Activity.EATING)) {
            System.out.format("%s is eating %n", getName());
            return true;
        } else {
            System.out.format("%s is not eating %n", getName());
            return false;
        }
    }

    public boolean ifDoingNothing() {
        if (getActivity().equals(Activity.DOING_NOTHING)) {
            System.out.format("%s is doing nothing %n", getName());
            return true;
        } else {
            System.out.format("%s is busy %n", getName());
            return false;
        }
    }

    public boolean ifHunting() {
        if (getActivity().equals(Activity.EATING)) {
            System.out.format("%s is hunting %n", getName());
            return true;
        } else {
            System.out.format("%s is not hunting %n", getName());
            return false;
        }
    }

    public int massAfterEat(int massFood) throws InvalidStateException {
        if (this.activity == Activity.SLEEPING) {
            throw new InvalidStateException("Unable to eat: I'm sleeping");
        }

        weight = getWeight() + massFood;
        setActivity(Activity.EATING);
        System.out.format("After eating %d kg of food weight of %s is %d kg. %n", massFood, getName(), getWeight());
        return weight;
    }

    public int massAfterSleep(int lostWeightWhileSleeping) {
        weight = getWeight() - lostWeightWhileSleeping;
        setActivity(Activity.SLEEPING);
        System.out.format("After sleeping weight of %s is %d %n", getName(), getWeight());
        return weight;
    }

    public boolean ifReadyToHunting() {
        if (getWeight() <= 5 && getActivity() != Activity.HUNTING) {
            System.out.format("%s is hungry!!! It's time to go hunting! %n", getName());
            setActivity(Activity.HUNTING);
            return true;
        } else if (getActivity() == Activity.HUNTING) {
            System.out.format("%s is hunting process! %n", getName());
            return true;
        } else {
            return false;
        }
    }
}
