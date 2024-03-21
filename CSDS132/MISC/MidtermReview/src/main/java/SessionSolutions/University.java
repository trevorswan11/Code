package SessionSolutions;

public class University extends School {
    private String president;
    private int reputation = 0;

    public University(String president) {
        super();
        this.president = president;
    }

    public University(String name, double tuition, double teacherSalary, int numTeachers, int numStudents, String president) {
        super(name, tuition, teacherSalary, numTeachers, numStudents);
        this.president = president;
    }

    public int getReputation() {
        return reputation;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public String getPresident() {
        return this.president;
    }

    @Override
    public void hireTeachers(int number) {
        if (getTotalTuition() > getTeacherExpenses() + number * getTeacherSalary()) {
            super.hireTeachers(number);
        }
    }
}
