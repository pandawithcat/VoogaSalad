package ExternalAPIs;

public class LeaderBoardEntry {

    private int myRank;
    private String myUserName;
    private int myScore;

    public LeaderBoardEntry(int rank, String userName, int score){
        myRank = rank;
        myUserName = userName;
        myScore = score;
    }

    public int getRank() {
        return myRank;
    }

    public String getUserName() {
        return myUserName;
    }

    public int getScore() {
        return myScore;
    }
}
