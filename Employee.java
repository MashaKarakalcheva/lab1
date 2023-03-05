import java.io.BufferedReader;
import java.io.IOException;
import java.util.Formatter;

public class Employee implements Comparable<Employee> {
    private String name;
    private String surname;
    private String position;
    private String category;
    private int experience;
    private int IDNP;

    public Employee() {
        name = "None";
        surname = "None";
        position = "None";
        category = "None";
        experience = 0;
        IDNP =0;
    }

    public Employee(Employee other) {
        name = other.name;
        surname = other.surname;
        position = other.position;
        category = other.category;
        experience = other.experience;
        IDNP = other.IDNP;
    }

    public Employee clone() {
        return new Employee(this);
    }

    public boolean equal(Employee other) {
        return (name.equals(other.name) &&
                surname == other.surname &&
                position.equals(other.position) &&
                category.equals(other.category) &&
                experience == other.experience &&
                IDNP == other.IDNP );
    }

    public void readingFile(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        String[] fields = line.split(", ");

        name = fields[0];
        surname = fields[1];
        position = fields[2];
        category = fields[3];
        experience = Integer.parseInt(fields[4]);
        IDNP = Integer.parseInt(fields[4]);
    }

    public void writeElement() {
        System.out.println(name + ", " +
                surname + ", " +
                position + ", " +
                category + ", " +
                experience + ", " +
                IDNP);
    }

    public void writeTable(Formatter formatter) {
        formatter.format("Имя: %-20s Фамилия: %-20s Должность: %-50s оклад: %-10s Стаж:%-15d IDNP: %-20d\n", name, surname, position, category, experience, IDNP);
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Employee other) {
        return this.name.compareTo(other.getName());
    }
}