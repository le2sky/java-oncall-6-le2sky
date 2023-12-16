package oncall.domain;

import java.util.Objects;

public class Employee {

    private final Name name;

    private Employee(Name name) {
        this.name = name;
    }

    public static Employee from(Name name) {
        return new Employee(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
