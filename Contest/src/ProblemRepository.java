import java.util.Arrays;
import java.util.List;

public interface ProblemRepository {
    void addProblem(Problem problem);
    Problem getProblem(String problemName);
    List<Problem> getProblems(String tag, String difficulty, String sortBy);


    List<Problem> getProblems();
}
