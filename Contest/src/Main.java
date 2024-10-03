public class Main {
    public static void main(String[] args) {
        ScoringStrategy scoringStrategy = new TimeAndDifficultyBasedScoring();
        ContestDriver driver = new ContestDriver(scoringStrategy);

        driver.setup();
        driver.printLeader();
        driver.printSolvedProblemsByUser("p4");


//        driver.printFetchProblems("arrays", "easy", "score");
//        driver.printFetchProblems(null, null, "userssolved");
        driver.printTopNProblems("arrays", 2);
        driver.printLeader();

        driver.testRecommendations();

//        ProblemRepository problemRepository = new InMemoryProblemRepository();
//        UserRepository userRepository = new InMemoryUserRepository();
////        ScoringStrategy scoringStrategy = new TimeAndDifficultyBasedScoring();
//        ContestDriverSOLID driverS = new ContestDriverSOLID(scoringStrategy, problemRepository, userRepository);
//
//        driverS.setup();
//        driverS.printAllProblems();
//        driverS.printLeader();
//        driverS.printSolvedProblemsByUser("p1");
//        driverS.printFetchProblems("arrays", null, "score");
//        driverS.printTopNProblems("arrays", 2);
//        driverS.testRecommendations();
    }
}
