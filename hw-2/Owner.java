package hw_2;

import java.util.Objects;

public class Owner {

    public Owner(long ownerId,
                 String name,
                 String lastName,
                 int age) {
        this.ownerId = ownerId;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return ownerId == owner.ownerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId);
    }

    private final long ownerId;
    private final String name;
    private final String lastName;
    private final int age;

}
