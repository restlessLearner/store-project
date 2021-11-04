package Interaction;

import facade.Facade;

public class ReviewsMenu {

    public static void reviewsMenu() {

        final String REVIEWS_MENU = "Reviews options menu:" + Launcher.EOL +
                "0. Return to Main Menu." + Launcher.EOL +
                "1. Create a review for an Item." + Launcher.EOL +
                "2. Print a specific review of an Item." + Launcher.EOL +
                "3. Print all reviews of an Item." + Launcher.EOL +
                "4. Print mean grade of an Item." + Launcher.EOL +
                "5. Print all comments of an Item." + Launcher.EOL +
                "6. Print all registered reviews." + Launcher.EOL +
                "7. Print item(s) with most reviews." + Launcher.EOL +
                "8. Print item(s) with least reviews." + Launcher.EOL +
                "9. Print item(s) with best mean review grade." + Launcher.EOL +
                "10. Print item(s) with worst mean review grade." + Launcher.EOL;

        int userAnswer;

        do {
            InputOutput.printInfo(REVIEWS_MENU);
            userAnswer = InputOutput.readInt("Input option Number: ");

            switch (userAnswer) {

                case 0:
                    Launcher.mainMenu();
                    break;
                case 1:
                    String itemIdToReview = InputOutput.readString("Type id of an item to give a feedback: ");
                    String reviewComment = InputOutput.readString("Write your comment: ");
                    int grade = InputOutput.readInt("Please, give your grade to an item: ");
                    String creationReviewResult = Launcher.facade.reviewItem(itemIdToReview, reviewComment, grade);
                    InputOutput.printInfo(creationReviewResult);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                default:
                    InputOutput.printInfo("Invalid menu option. Please type another option: ");
                    reviewsMenu();
                    break;

            }


        } while (userAnswer != 0);
    }
}
