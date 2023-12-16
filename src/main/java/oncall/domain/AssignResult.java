package oncall.domain;

import java.time.DayOfWeek;

public record AssignResult(int day, DayOfWeek dayOfWeek, Employee employee){
}
