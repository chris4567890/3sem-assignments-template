package org.app;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        LambdaCalculator calculator = new LambdaCalculator();

        System.out.println(calculator.multiplication.display(20,10));
        System.out.println(calculator.addition.display(10,2));
        System.out.println(calculator.modolus.display(5,4));
        System.out.println(calculator.subtraction.display(35,15));
        System.out.println(calculator.powerPower.display(10,2));

        LambdaCalculatorV2 lambdaCalculatorV2 = new LambdaCalculatorV2();

        System.out.println(lambdaCalculatorV2.addition.display(5,7645));
        System.out.println(lambdaCalculatorV2.multiplication.display(5,4));
        System.out.println(lambdaCalculatorV2.subTraction.display(50,45));
        System.out.println(lambdaCalculatorV2.division.display(25,5));
        System.out.println(lambdaCalculatorV2.modulus.display(4,4));

        int[] cakeNumbers ={
                47,
                43,
                69,
                100674,
                1337
        };

        List<Integer> numberNumbers = new ArrayList<>();
        numberNumbers.add(5);
        numberNumbers.add(4);
        numberNumbers.add(3);
        numberNumbers.add(7);
        numberNumbers.add(14);
        numberNumbers.add(21);

        List<Integer> stupidNumbers = new ArrayList<>();

        List<String> myEmployeesName = Arrays.asList("Patrick","NokIkNik","Rolin");

        List<LocalDate> birthdays = Arrays.asList(
                LocalDate.of(1993,5,4),
                LocalDate.of(1996,7,23),
                LocalDate.of(2000,11,10)
        );

        Supplier<Employee> employeeSupplier= () ->{
            Random random = new Random();
            int randomIndex = new Random().nextInt(myEmployeesName.size());
            int randomBirthDayIndex = new Random().nextInt(birthdays.size());
            String randomName = myEmployeesName.get(randomIndex);
            LocalDate randomBirthDay = birthdays.get(randomBirthDayIndex);
            return  new Employee(randomName,randomBirthDay);
        };

        List<Employee> employees = createEmployees(3,employeeSupplier);

        Consumer<Employee> employeeConsumer = employee -> {
            System.out.println(employee.getName());
        };

        employees.forEach(employeeConsumer);

        List<String> stringHolder = new ArrayList<>();

        Function<Employee,String> stringConverter = Employee::toString;

        for(Employee employee: employees){
            stringHolder.add(stringConverter.apply(employee));
        }

        for (String string: stringHolder){
            System.out.println(string);
        }
        List<Integer> years = new ArrayList<>();
        for (Employee employee: employees){
            LocalDate currentDate = LocalDate.now();
            int year = currentDate.getYear() - employee.getBirthday().getYear();
            years.add(year);
        }
        int averageSum = 0;
        for(int year : years){
            averageSum = averageSum+year;
        }
        System.out.println("the average age is :  " + averageSum/years.size() + " year(s)");


       final Map<Month,List<Employee>> employeesByBirthMonth = employees.stream().collect(Collectors.groupingBy(employee ->employee.getBirthMonth()));

       List<Employee> employeesInCurrentMonth = new ArrayList<>();

       for (Employee employee : employees){
           LocalDate localDate = LocalDate.now();
           if (employee.getBirthMonth() == localDate.getMonth()){
               employeesInCurrentMonth.add(employee);
           }

       }


        System.out.println(employeesByBirthMonth);

        Predicate<Integer> isDivisble = num -> num % 7 ==0;

        for(int a: numberNumbers){
            if(isDivisble.test(a)){
                stupidNumbers.add(a);
            }
        }
        for(int a : stupidNumbers){
            System.out.println(a);
        }

        Book dasKapital = new Book("Karl Max","Das Kapital",1867,356,3.89);
        Book animalFarm = new Book("George Owell","Animal Farm",1945,128,3.89);
        Book phenomenologyOfSpirits = new Book("Georg hegel","Phenomenology of Spirit",1807,640,3.96);

        List<Book> books = new ArrayList<>();
        books.add(dasKapital);
        books.add(animalFarm);
        books.add(phenomenologyOfSpirits);
        List<Book> descendingBooksByRating = books.stream().sorted(Comparator.comparing(Book::getRating).reversed()).collect(Collectors.toList());

        for(Book book: descendingBooksByRating){
            System.out.println(book.getTitle());
        }

        List<Book> specificYear = books.stream().filter(book -> book.getRelease_year() >  1868).collect(Collectors.toList());

        Book bestRated = books.stream().max(Comparator.comparing(Book::getRating)).get();


        System.out.println("best rated book: " +bestRated.getTitle());

        for (Book book: specificYear){
            System.out.println(book.getTitle());
        }

        double averageRating = 0;
        int totalPages = 0;
        for(Book book : books){
            averageRating = averageRating+book.getRating();
            totalPages = totalPages+book.getPages();
        }
        MemoryStorage dataMemSaver = new MemoryStorage();
        FileStorage fileStorage = new FileStorage();
        dataMemSaver.store(42);
        //note : got no clue where it saves the file could make it so it knows where but don't have the time.
        fileStorage.store(dasKapital.toString());
        System.out.println("have saved your thingy to file");
        System.out.println("here is your thingy from file" + fileStorage.retrive(dasKapital.toString()));
        System.out.println("Here is your retrieved item : " + dataMemSaver.retrive(null));



        System.out.println(averageRating/books.size());




        System.out.println(totalPages);

        List<Transaction> transactions = List.of(
                new Transaction(2,500,"usd"),
                new Transaction(1,340,"dkk"),
                new Transaction(3,600,"dkk"),
                new Transaction(5,1,"sek")
        );

        int total_sum = transactions.stream().mapToInt(Transaction::getAmount).sum();
        System.out.println("the total sum even though it doesn't add up considering convertion rate" + total_sum);

        Map<String,List<Transaction>> currencyGroup = transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency,Collectors.toList()));

        int averageTransSum = 0;
        for(Transaction transaction: transactions){
            averageTransSum = averageTransSum+transaction.getAmount();
        }
        int finalAverageTransSum = averageTransSum/transactions.size();
        System.out.println("here is the average transaction sum: "+finalAverageTransSum);
        System.out.println(currencyGroup);
        Transaction highestTrans = transactions.stream().max(Comparator.comparing(Transaction::getAmount)).get();
        System.out.println("here is your highest transaction: " +highestTrans.getAmount());

        CompletableFuture task1 = CompletableFuture.runAsync(() ->new Task());
        CompletableFuture task2 = CompletableFuture.runAsync(()-> new Task());

        CompletableFuture completeAllTasks = CompletableFuture.allOf(task1,task2);
        completeAllTasks.thenRun(()->System.out.println("done I think"));
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> new Task());
        executorService.submit(() -> new Task());
        executorService.shutdown();
        System.out.println("done worst documentation so far");




        MyTransformingType transformer = ( a) ->a*2;

        MyValidatingType validatorInator = ( a) ->{
          if(a%5 == 0){
              return 0;
          }else{
              return 1;
          }
        };

        int[] result1 = map(cakeNumbers,transformer);
        int[] result2 = filter(cakeNumbers,validatorInator);

        System.out.println(result1);

        for(int a : result1){
            System.out.println(a);
        }

        for(int a :result2){
            System.out.println(a);
        }

    }

    public static int[] map(int[] a, MyTransformingType typer){
        int[] bop = new int[a.length];
        for(int i = 0;  i < a.length; ++i){
            bop[i] = typer.transform(a[i]);
        }
        return bop;
    }

    public static List<Employee> createEmployees(int amountOfEmployees,Supplier<Employee> employeeSupplier){
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i <amountOfEmployees; ++i){
            employees.add(employeeSupplier.get());
        }
        return employees;
    }

    public static int[] filter(int[] a, MyValidatingType typer){
        int[] patrick = new int[a.length];
        for(int i= 0; i< a.length; ++i){
            patrick[i] = typer.validateInator(a[i]);
        }
        return patrick;
    }

}