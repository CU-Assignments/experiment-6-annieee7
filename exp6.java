//EASY

import java.util.*;

class Employee {
    String name;
    int age;
    double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " - Age: " + age + ", Salary: " + salary;
    }
}

public class EmployeeSorting {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Alice", 30, 50000),
            new Employee("Bob", 25, 60000),
            new Employee("Charlie", 28, 60000),
            new Employee("David", 35, 45000)
        );

        // Sorting employees by salary, then by age
        employees.sort(Comparator.comparingDouble(Employee::salary).thenComparingInt(Employee::age));

        // Display sorted employees
        employees.forEach(System.out::println);
    }
}


//MEDIUM
import java.util.*;
import java.util.stream.Collectors;

class Student {
    String name;
    double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }
}

public class StudentFiltering {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 82),
            new Student("Bob", 65),
            new Student("Charlie", 90),
            new Student("David", 74),
            new Student("Emma", 88)
        );

        // Filter students scoring above 75%, sort by marks
        List<String> topStudents = students.stream()
            .filter(s -> s.getMarks() > 75)
            .sorted(Comparator.comparingDouble(Student::getMarks).reversed())
            .map(Student::getName)
            .collect(Collectors.toList());

        // Display top students
        topStudents.forEach(System.out::println);
    }
}


//HARD

import java.util.*;
import java.util.stream.Collectors;

class Product {
    String name, category;
    double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}

public class ProductProcessing {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 1200),
            new Product("Phone", "Electronics", 800),
            new Product("Tablet", "Electronics", 500),
            new Product("TV", "Home Appliance", 1500),
            new Product("Refrigerator", "Home Appliance", 2000),
            new Product("Microwave", "Home Appliance", 300)
        );

        // Grouping products by category
        Map<String, List<Product>> groupedProducts = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory));

        // Finding the most expensive product in each category
        Map<String, Product> mostExpensiveProducts = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.collectingAndThen(
                    Collectors.maxBy(Comparator.comparingDouble(Product::getPrice)),
                    Optional::get
                )
            ));

        // Calculating the average price of all products
        double avgPrice = products.stream()
            .mapToDouble(Product::getPrice)
            .average()
            .orElse(0.0);

        // Display grouped products
        System.out.println("\nGrouped Products:");
        groupedProducts.forEach((category, productList) -> {
            System.out.println(category + ": " + productList);
        });

        // Display most expensive products
        System.out.println("\nMost Expensive Products in Each Category:");
        mostExpensiveProducts.forEach((category, product) ->
            System.out.println(category + ": " + product)
        );

        // Display average price
        System.out.println("\nAverage Price of All Products: $" + avgPrice);
    }
}

