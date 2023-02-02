package ForWeekly.Exception;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(Integer taskId) {
        throw new RuntimeException("Задания номер " + taskId + " не существует.");
    }
    public TaskNotFoundException(String task) {
        throw new RuntimeException(task);
    }

}
