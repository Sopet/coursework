import java.sql.SQLOutput;

public class EmployeeBook {
    /** 7.1. Создаю класс EmployeeBook c полем типа Employee[10], которое будет выполнять роль хранилища для записей о сотрудниках. */
    private final Employee[] employees = new Employee[10];
    private int size;

    /** 7.2. Создаю метод для хранилища. */
    public EmployeeBook() {
        employees[0] = new Employee("Иванов Иван Иванович", 1, 50.0);
        employees[1] = new Employee("Петров Пётр Петрович", 2, 95.0);
        employees[2] = new Employee("Андреев Андрей Андреевич", 3, 140.0);
        employees[3] = new Employee("Дмитриев Дмитрий Дмитриевич", 4, 185.0);
        employees[4] = new Employee("Александров Александр Александрович", 5, 230.0);
        employees[5] = new Employee("Алексеев Алексей Алексеевич", 1, 275.0);
        employees[6] = new Employee("Сидоров Сидор Сидорович", 2, 320.0);
        employees[7] = new Employee("Антонов Антон Антонович", 3, 365.0);
        employees[8] = new Employee("Михайлов Михаил Михаилович", 4, 410.0);
        employees[9] = new Employee("Сергеев Сергей Сергеевич", 5, 450.0);
        size = 10;
    }


    /** 8. Создаю методы класса EmployeeBook, которые будут взаимодействовать с массивом из пункта 7 и возвращать результат:
     * 8.1. Получить список всех сотрудников со всеми данными о них, вывести в консоль значения всех полей, кроме Null */
    public void listAllEmployees() {
        for (Employee emp : employees) {
            if (emp != null) {
                System.out.println(emp.toString());
            }
        }
    }

    /** 8.2. Подсчитываю среднее значение зарплат. */
    public double avg() {
        int count = 0;
        double sum = 0;

        for (Employee emp : employees) {
            if (emp != null) {
                count++;
                sum += emp.getSalary();
            }
        }

        System.out.println(sum / count);
        return sum / count;
    }

    /** 8.3. Вывожу значения налогов с применением switch для выбора формулы расчета зарплаты. */
    public void tax(String taxType) {
        System.out.println("Расчет налогов и конечной зарплаты (Тип налога: " + taxType + "):");

        if (!"PROPORTIONAL".equals(taxType) && !"PROGRESSIVE".equals(taxType)) {
            System.out.println("Ошибка: Неизвестный тип налога: " + taxType);
            return;
        }

        for (Employee emp : employees) {
            if (emp == null) {
                continue;
            }

            double currentSalary = emp.getSalary();
            double taxAmount = 0; // Переменная для хранения суммы налога


            switch (taxType) {
                case "PROPORTIONAL":
                    taxAmount = currentSalary * 0.13;
                    break;

                case "PROGRESSIVE":
                    if (currentSalary < 150) {
                        taxAmount = currentSalary * 0.13;
                    } else if (currentSalary >= 150 && currentSalary < 350) {
                        taxAmount = currentSalary * 0.17;
                    } else { // Свыше 350
                        taxAmount = currentSalary * 0.21;
                    }
                    break;

                default:
                    System.out.println("Неизвестный тип налога: " + taxType);
                    break;
            }

            double netSalary = currentSalary - taxAmount;

            System.out.println("Сотрудник: " + emp.getFullName() +
                    ", Зарплата до вычета налога: " + String.format("%.2f", currentSalary) +
                    ", Сумма прогрессивного налога: " + String.format("%.2f", taxAmount) +
                    ", Конечная зарплата сотрудника: " + String.format("%.2f", netSalary));
            }
        }

    /** 8.4. Получаю в качестве параметра номер отдела (1–5) и проиндексирую зарплату всех сотрудников отдела на процент,
     который приходит в качестве параметра
     (т.е. вызываю изменение зарплаты у всех сотрудников на величину аргумента в процентах). */
    public void indexSalary(int targetDepartment, double percentage) {
        // Проверяю на корректность номера отдела (1-5)
        if (targetDepartment < 1 || targetDepartment > 5) {
            System.out.println("Ошибка: номер отдела должен быть от 1 до 5.");
            return;
        }

        System.out.println("Индексация зарплат для отдела " + targetDepartment + " на " + percentage + "%:");

        for (Employee emp : employees) {
            // Пропускаю сотрудников, которые не принадлежат к целевому отделу
            if (emp.getDepartment() != targetDepartment) {
                continue;
            }

            // Рассчитываю новую зарплату
            double currentSalary = emp.getSalary();
            double newSalary = currentSalary * (1 + percentage / 100);

            // Обновляю зарплату сотрудника
            emp.setSalary(newSalary);

            System.out.println("Зарплата сотрудника " + emp.getSalary() + " обновлена: с " + currentSalary + " до " + newSalary);
        }
    }

    /** 8.5. Получаю в качестве параметра номер отдела (1–5) и цифру зарплаты,
     и вывожу первого сотрудника этого отдела с зарплатой больше указанной вместе с порядковым номером в списке.*/
    public void param(int targetDepartment, double higherSalary) {

        boolean found = false; // Флаг для отслеживания, был ли найден сотрудник

        for (int i = 0; i < employees.length; i++) {
            Employee emp = employees[i];
            if (emp != null && emp.getDepartment() == targetDepartment && emp.getSalary() > higherSalary) {
                System.out.print("Порядковый номер в списке: " + (i + 1) + ", ");
                emp.printShortInfo();
                found = true; // Сотрудник найден
                break;
            }
        }
        if (!found) { // Если флаг остался false
            System.out.println("Подходящий сотрудник не найден.");
        }
    }

    /** 8.6. Получаю в качестве параметра цифру зарплаты wage и число сотрудников employeeNumber,
     и вывожу первые employeeNumber сотрудников с зарплатой меньше wage. */
    public void figure(double wage, int employeeNumber) {
        int counterForEmployees = 0;
        int counterForArray = 0;

        while (counterForArray < employees.length) {
            Employee emp = employees[counterForArray];
            counterForArray++;

            if (emp != null && emp.getSalary() < wage) {
                counterForEmployees++;
                System.out.println("Найден сотрудник №" + counterForEmployees + ": " + emp.getFullName() + ", Зарплата: " + emp.getSalary());

                if (counterForEmployees == employeeNumber) {
                    break;
                }
            }
        }
    }

    /** 8.7. Получаю в качестве параметра объект сотрудника и вернуть boolean,
     есть ли такой сотрудник в массиве с точки зрения бухгалтерского учета (по зарплате) или нет. */
    public boolean checkBySalary(Employee employeeToCheck) {
        double epsilon = 0.0001; // Для более точного поиска зарплаты

        for (int i = 0; i < employees.length; i++) {
            Employee emp = employees[i];
            if (emp != null) {
                if (Math.abs(emp.getSalary() - employeeToCheck.getSalary()) < epsilon) {
                    return true;
                }
            }
        }
        return false;
    }


    /** 9. Создаю метод для проверки пустых ячеек о сотрудниках. */
    public boolean addEmployee(Employee employee) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = employee;
                return true;
            }
        }
        System.out.println("Невозможно добавить сотрудника: хранилище заполнено.");
        return false;
    }


    /** 10. Добавляю метод для получения сотрудника по id. */
    public Employee findEmployeeById(int id) {
        for (Employee emp : employees) {
            if (emp != null && emp.getId() == id) {
                return emp; // Нашли сотрудника и возвращаем его
            }
        }
        return null; // Если цикл завершился и сотрудник не найден
    }
}