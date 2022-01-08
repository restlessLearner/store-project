package businessLogic;

public class Review {

    private int reviewGrade;
    private String reviewComment;

    public Review(int reviewGrade, String reviewComment) {
        this.reviewGrade = reviewGrade;
        this.reviewComment = reviewComment;
    }

    public Review(int reviewGrade) {
        this.reviewGrade = reviewGrade;
    }

    public int getReviewGrade() {
        return reviewGrade;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    @Override
    public String toString() {
        return "Grade: " + getReviewGrade() + "." + getReviewComment();
    }
    //---------------------------------
    public void setReviewGrade(int grade){
        this.reviewGrade = grade;
    }
    public void setReviewComment(String comment){
        this.reviewComment = comment;
    }
}
