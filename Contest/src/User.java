import java.util.ArrayList;
import java.util.List;

class User {
    private String name;
    private String id;
    private String department;
    private int totalScore;
    private List<Problem> solvedProblems;

    public User(String id, String name, String department) {
        this.id=id;
        this.name = name;
        this.department = department;
        this.totalScore = 0;
        this.solvedProblems = new ArrayList<>();
    }

    public void addSolvedProblem(Problem problem, int score) {
        totalScore += score;
        solvedProblems.add(problem);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Problem> getSolvedProblems() {
        return solvedProblems;
    }

    public int getTotalScore() {
        return totalScore;
    }

    @Override
    public String toString() {
        return String.format("User[name=%s, department=%s, totalScore=%d]", name, department, totalScore);
    }
}
