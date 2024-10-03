import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryProblemRepository implements ProblemRepository {

    private List<Problem> problemList;

    public InMemoryProblemRepository() {
        this.problemList = new ArrayList<>();
    }

    @Override
    public void addProblem(Problem problem) {
        problemList.add(problem);
    }

    @Override
    public Problem getProblem(String problemName) {
        for (Problem problem : problemList) {
            if (problem.getName().equals(problemName)) {
                return problem;
            }
        }
        return null;
    }

    @Override
    public List<Problem> getProblems(String tag, String difficulty, String sortBy) {
        List<Problem> filteredProblems = problemList.stream()
                .filter(problem -> (tag == null || problem.getTag().equalsIgnoreCase(tag)))
                .filter(problem -> (difficulty == null || problem.getDifficulty().equalsIgnoreCase(difficulty)))
                .collect(Collectors.toList());

        switch (sortBy.toLowerCase()) {
            case "score":
                filteredProblems.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
                break;
            case "userssolved":
                filteredProblems.sort((p1, p2) -> Integer.compare(p2.getUsersSolved(), p1.getUsersSolved()));
                break;
            case "averagetime":
                filteredProblems.sort((p1, p2)->Double.compare(p2.getAverageTime(), p1.getAverageTime()));
            default:
                System.out.println("Invalid sort criteria. Defaulting to score.");
                filteredProblems.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));
        }


        return filteredProblems;
    }
    @Override
    public List<Problem> getProblems()
    {
        return problemList;
    }
}
