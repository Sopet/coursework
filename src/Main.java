public class Main {
    public static void main(String[] args) {
        EmployeeBook book = new EmployeeBook();
        Employee check = new Employee("Григорьев Григорий Григорьевич", 1,50.5);


        System.out.println("№6.2 - Сотрудник и зарплата");
        check.printShortInfo();


        System.out.println("\n№7.2 - Хранилище");
        book.listAllEmployees();


        System.out.println("\n№8.2 - Среднее значение зарплат");
        book.avg();


        System.out.println("\n№8.3 - Значения налогов и конечный результат зарплат");
        book.tax("PROGRESSIVE"); // Либо PROPORTIONAL, либо PROGRESSIVE, иное выведет ошибку.


        System.out.println("\n№8.4 - Индексация зарплат по отделам");
        book.indexSalary(1,1); // Отдел 1-5, остальные с ошибкой.


        System.out.println("\n№8.5 - Отдел и зарплата");
        book.param(3,320);


        System.out.println("\n№8.6 - Зарплата и число сотрудников");
        book.figure(100,20);


        System.out.println("\n№8.7 - Поиск сотрудника по зарплате");
        boolean isFound = book.checkBySalary(check);

        if (isFound) {
            System.out.println("Результат: Сотрудник с такой зарплатой существует."); // Вызывается при проиндексированной зарплате, как из предыдущих методов типа 50,5.
        } else {
            System.out.println("Результат: Сотрудник с такой зарплатой не найден."); // Вызовется, если, например, задать изначальна заданную зарплату в 50.0
        }


        System.out.println("\n№9 - Вызываю нового сотрудника");
        book.addEmployee(new Employee("Новый сотрудник", 3, 500));


        System.out.println("\n№10 - Получаю сотрудника по id");
        Employee foundEmployee = book.findEmployeeById(3); // Ищем сотрудника с id = 3

        if (foundEmployee != null) {
            System.out.println("Сотрудник найден: " + foundEmployee.getFullName());
        } else {
            System.out.println("Сотрудник с таким ID не найден.");
        }


        /** 11. Проинициализирую в main объект класса EmployeeBook и наполню его через метод добавления нового сотрудника —
         * вызову метод 11 раз, выведу результат исполнения метода на экран. */
        System.out.println("\n№11 - Наполнение хранилища 11 раз");

        for (int i = 0; i < 11; i++) {
            Employee newEmployee = new Employee("Тестовый Сотрудник " + (i + 1), (i % 5) + 1, 1000 + i);
            boolean success = book.addEmployee(newEmployee);
            String resultMessage = success ? "Успешно" : "Неуспешно (хранилище заполнено)";
            System.out.println("Попытка " + (i + 1) + ": Добавление сотрудника " + newEmployee.getFullName() + ", результат: " + resultMessage);
        }
    }
}