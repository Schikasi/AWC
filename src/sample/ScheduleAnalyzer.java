package sample;

import static java.time.temporal.ChronoUnit.DAYS;

public class ScheduleAnalyzer {

    private Schedule schedule;

    public ScheduleAnalyzer(Schedule schedule) {
        this.schedule = schedule;
    }

    public double getWorkloadPercent() {
        int countDays = (int) (DAYS.between(schedule.getStartDate(),schedule.getEndDate()) + 1);
        //Добавляем количество дней до полной недели относительно начала периода и делим на семь
        int countFullWeeks = (countDays + (schedule.getStartDate().getDayOfWeek().getValue() - 1)) / 7;
        //Количество рабочих дней = изначальное количество дней - два выходных * количество полных недель - 1, если последний день периода - сб
        int countWorkDays = countDays - countFullWeeks * 2 - (schedule.getEndDate().getDayOfWeek().getValue() == 6 ? 1 : 0);
        int totalCountCouple = countWorkDays > 0 ? countWorkDays * 5 : 0;
        Float workloadPercent = Float.valueOf(schedule.getCountCouples()) / totalCountCouple * 100;
        return workloadPercent.isNaN() ? 0 : workloadPercent;
    }

    public Schedule getSchedule() {
        return schedule;
    }
}
