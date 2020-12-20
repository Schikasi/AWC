package sample;

public class ScheduleAnalyzer {
    private Schedule schedule;

    public ScheduleAnalyzer(Schedule schedule) {
        this.schedule = schedule;
    }

    public double getWorkloadPercent() {
        int countDays = schedule.getEndDate().compareTo(schedule.getStartDate()) + 1;
        int countFullWeeks = (countDays + (schedule.getStartDate().getDayOfWeek().getValue() - 1)) / 7;
        int countWorkDays = countDays - countFullWeeks * 2 - (schedule.getEndDate().getDayOfWeek().getValue() == 6 ? 1 : 0);
        int totalCountCouple = countWorkDays > 0 ? countWorkDays * 5 : 0;
        Float workloadPercent = Float.valueOf(schedule.getCountCouples()) / totalCountCouple * 100;
        return workloadPercent.isNaN() ? 0 : workloadPercent;
    }

    public Schedule getSchedule() {
        return schedule;
    }
}
