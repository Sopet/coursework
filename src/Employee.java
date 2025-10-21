import java.util.Objects;

public class Employee {

    /** 1. Создаю класс Employee с информацией о Ф. И. О., отделе и зарплате сотрудника. */
    private final String fullName; // ФИО
    private int department; // Отдел
    private double salary; // Зарплата


    /** 2. Добавляю статическую переменную-счетчик, которая будет отвечать за id. */
    private static int idCounter = 1;


    /** 3. Добавляю в класс Employee поле id. */
    private final int id;

    public Employee(String fullName, int department, double salary) {
        this.fullName = fullName;
        this.department = department;
        this.salary = salary;
        id = idCounter++;
    }


    /** 4. Скрываю прямой доступ к полям класса Employee, добавляю возможность получать значения полей (геттеры) и
     устанавливаю значения полей отдела и зарплаты (сеттеры). */
    public String getFullName() {
        return fullName;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    /** 5. Реализовываю в классе Employee контракт equals. */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    /** 5. Реализовываю hashCode. */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /** 6. Реализовываю в классе Employee методы вывода информации о сотруднике.
     * 6.1. Вывод всех данных — через переопределение метода toString. */
    @Override
    public String toString() {
        return "id: " + id +
                ", Имя сотрудника: " + fullName +
                ", Отдел: " + department +
                ", Зарплата: " + salary;
    }

    /** 6.2. Вывод только имени и зарплаты — через вызов System.out.println в отдельном методе public void printShortInfo(). */
    public void printShortInfo() {
        System.out.println("Имя сотрудника: " + fullName + ", Зарплата: " + salary);
    }
}