package Store;

// creating class for review. Type Review is responsible for comments and grades

public class Review {

    private int reviewGrade;
    String reviewComment;

// constructor for Review

    public Review(String reviewComment, int reviewGrade) {
        this.reviewGrade = reviewGrade;
    }

// setters

    public void setReviewGrade(int reviewGrade) {
        this.reviewGrade = reviewGrade;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

// getters

    public int getReviewGrade(){
        return reviewGrade;
    }

    public String getReviewComment() {
        return reviewComment;
    }
}

