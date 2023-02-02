import ForWeekly.DeletedList;
import ForWeekly.Exception.NoTasksException;
import ForWeekly.Exception.TaskNotFoundException;
import ForWeekly.Weekly;
import ForWeekly.WeeklyRepeatTypes.*;
import ForWeekly.WeeklyServerTypeCollection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

import static ForWeekly.WeeklyServerTypeCollection.*;

public class Main {

    private static final Pattern DATE_TIME_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}\\:\\d{2}");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            removeTask(scanner);
                            break;
                        case 3:
                            getTaskForTheDay(scanner);
                            break;
                        case 4:
                            getMapOfDeletedTasks();
                            break;
                        case 0:
                            break label;
                        default:
                            throw new IllegalArgumentException("Неправильно введены данные");
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        } catch (TaskNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void inputTask(Scanner scanner) {
        scanner.useDelimiter("\n");
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Введите название задачи: ");
        String taskName = scanner1.nextLine();

        System.out.println("Введите текст задачи: ");
        String taskText = scanner1.nextLine();

        System.out.println("Введите тип задачи: ");
        System.out.println("1. Личная");
        System.out.println("2. Рабочая");
        Integer taskType = scanner.nextInt();

        System.out.println("Введите признак повторяемости задачи!");
        System.out.println("1. Однократная");
        System.out.println("2. Ежедневная");
        System.out.println("3. Еженедельная");
        System.out.println("4. Ежемесячная");
        System.out.println("5. Ежегодная");
        Integer taskRepeatingType = scanner.nextInt();

        //
        System.out.println("Введите дату и время задачи в формате dd.MM.yyyy HH:mm");
        LocalDateTime taskTime = null;
        if (scanner.hasNext(DATE_TIME_PATTERN)) {
            String dateTime = scanner.next(DATE_TIME_PATTERN);
            taskTime = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
        } else {
            System.out.println("Введите дату и время задачи в формате dd.MM.yyyy HH:mm");
            scanner.close();
        }
        if (taskTime == null) {
            System.out.println("Введите дату и время задачи в формате dd.MM.yyyy HH:mm");
            scanner.close();
        }
        //

        ifsForWeeklyRepeatTypes(taskRepeatingType);
        creatingObjectsMethod(taskName, taskText, taskType, ifsForWeeklyRepeatTypes(taskRepeatingType), taskTime); // добавить LocalDateTime
    }

    public static void removeTask(Scanner scanner) throws TaskNotFoundException {
        try {
            WeeklyServerTypeCollection.ifThrowException();
        } catch (NoTasksException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Введите id задачи, которую вы хотите удалить: ");
        WeeklyServerTypeCollection.getAllTasksInMapToString();
        int scan = scanner.nextInt();
        System.out.println("Удаляю задачу с именем " + getNameForKey(scan));
        removeTaskInMap(scan);
        System.out.println("Задача успешно удалена!\n");
    }

    public static void getMapOfDeletedTasks() {
        System.out.println("Список удаленных задач: ");
        DeletedList.getListOfDeletedWeeklyMap();
    }

    public static void getTaskForTheDay(Scanner scanner) {
        try {
            WeeklyServerTypeCollection.ifThrowException();
        } catch (NoTasksException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Укажите дату для получения задачи!");
        LocalDateTime taskTime1 = null;
        if (scanner.hasNext(DATE_TIME_PATTERN)) {
            String dateTime = scanner.next(DATE_TIME_PATTERN);
            taskTime1 = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
        } else {
            System.out.println("Введите дату и время задачи в формате dd.MM.yyyy HH:mm");
            scanner.close();
        }
        if (taskTime1 == null) {
            System.out.println("Введите дату и время задачи в формате dd.MM.yyyy HH:mm");
            scanner.close();
        }
        returningInfoIfDateEquals(taskTime1);
        // он также доллжен выводить имена конкретных заданий под эту дату.
    }

    public static void returningInfoIfDateEquals(LocalDateTime taskTime1) {
        int maxCountingValue = 200;
        for (Weekly value : weeklyMap.values()) {
            if (taskTime1.isAfter(value.getTaskTime()) || taskTime1.equals(value.getTaskTime())) {
                if (value.getTaskRepeatingType().getClass().equals(Single.class)) {
                    value.getTaskTime().equals(taskTime1);
                    System.out.println(value);
                } else if (value.getTaskRepeatingType().getClass().equals(EveryDay.class)) {
                    if (taskTime1.isAfter(value.getTaskTime())) {
                        System.out.println(value);
                    }
                } else if (value.getTaskRepeatingType().getClass().equals(EveryWeek.class)) {
                    for (int i = 0; i < maxCountingValue; i++) {
                        if (value.getTaskTime().plusWeeks(i).equals(taskTime1)) {
                            System.out.println(value.toString());
                        }
                    }
                } else if (value.getTaskRepeatingType().getClass().equals(EveryMonth.class)) {
                    for (int i = 0; i < maxCountingValue; i++) {
                        if (value.getTaskTime().plusMonths(i).equals(taskTime1)) {
                            System.out.println(value.toString());
                        }
                    }
                } else if (value.getTaskRepeatingType().getClass().equals(EveryYear.class)) {
                    for (int i = 0; i < maxCountingValue; i++) {
                        if (value.getTaskTime().plusYears(i).equals(taskTime1)) {
                            System.out.println(value.toString());
                        }
                    }
                } else try {
                    throw new TaskNotFoundException("Задача с такой датой не найдена");
                } catch (TaskNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void creatingObjectsMethod(String taskName, String taskText, Integer taskType, Object taskRepeatingType, LocalDateTime taskTime) { // можно сделать несколько таких методов для работы
        Weekly newWeekly = new Weekly(taskName, taskText, taskType, taskRepeatingType, taskTime);
    }

    private static void printMenu() {
        System.out.println(
                "1. Добавить задачу\n" +
                "2. Удалить задачу\n" +
                "3. Получить задачу на указанный день\n" +
                "4. Получить удаленные задачи\n" +
                "0. Выход\n"
        );
    }

    //
    public static Object ifsForWeeklyRepeatTypes(int taskRepeatingTypes) {
        switch (taskRepeatingTypes) {
            case 1:
                Single type1 = new Single();
                return type1;
            case 2:
                EveryDay type2 = new EveryDay();
                return type2;
            case 3:
                EveryWeek type3 = new EveryWeek();
                return type3;
            case 4:
                EveryMonth type4 = new EveryMonth();
                return type4;
            case 5:
                EveryYear type5 = new EveryYear();
                return type5;
            default:
                throw new RuntimeException("Что - то пошло не так!");
        }
    }
}