package com.jetbrain;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;


//creating the interface for the gym management system
interface GymManger {
  void menu(Stage stage1) throws IOException;
  void addMember() throws IOException;
  void deleteMember();
  void printDetails() throws FileNotFoundException;
  void saveMember() throws IOException;
  void sortTheList();
  void openGui(Stage guiStage) throws IOException;

}

public class MyGymManager extends Application implements GymManger {

    private static final ArrayList<DefaultMember>  storingDetails=new ArrayList<>();
    private static final File file =new File("Member Details.txt");
    private static final Scanner detail = new Scanner(System.in);



    public static void main(String[] args){
        System.out.println("\n------------------>     WELCOME!!      <-----------------------\n" +
                            "\t\t\t\tTo My Gym Management System..");
        launch(args);
    }

            //begins the stage of the gym system
            public void start(Stage stage) throws IOException {
                menu(stage);
            }


            //menu of the system
            @Override
            public void menu(Stage stage1) throws IOException {

                System.out.print("\n\t 1.Add a new member\n " +
                        "\t 2.Delete a member \n" +
                        "\t 3.Print the list of members \n" +
                        "\t 4.Save into a file\n"+
                        "\t 5.Sort the list according to their names\n" +
                        "\t 6.Open GUI\n\n\n");


                //switch case for the main menu
                int choice;
                System.out.print(" Enter the respective number to continue :- ");
                choice = detail.nextInt();
                switch (choice) {
                    case 1:
                        //loop which show the available and filled up spaces of the gym
                        for (int i = 0; i < storingDetails.size(); i++) {
                            System.out.println("\n\tThere are " + (100 - storingDetails.size()) +" remaining spaces available in the gym");
                            System.out.println("\tThere are " + (storingDetails.size()) + " members in the gym.");
                            break;
                        }
                        addMember();
                        break;
                    case 2:
                        deleteMember();
                        break;
                    case 3:
                        printDetails();
                        break;
                    case 4:
                        saveMember();
                        break;
                    case 5:
                        sortTheList();
                        break;
                    case 6:
                        openGui(stage1);
                        break;
                    default:
                        System.out.println("\n\tInvalid input..Re-enter -->");
                        menu(stage1);
                }
                //printed after each methods are completed in the cases above
                System.out.print("\n Enter y to continue..or any other to stop : ");
                String repeat = detail.next();
                if (repeat.equals("y")) {
                    menu(stage1);
                } else {
                    System.out.println("\n\t\t----- Have a Good Day!! -----");
                }
            }

            //adding member to the gym ---------------------------------------------------------------------------------------------
            @Override
            public void addMember() throws IOException {

                PrintWriter  printWriter;
                int ageChoice;
                int age;
                String school;
                int day;
                int month;
                int year;

                //print writer to print details in the member details text
                printWriter = new PrintWriter(new FileWriter(file, true));

                    //checks whether there a re available spaces in the space
                    if (storingDetails.size() == 100) {
                        System.out.print(" !!\t\nNo space available\nRemove a member to enter a new member..\t!!");
                    } else {
                        System.out.print("\n\tChoose age range...\n" +
                                            "  \t1. Below 20  ( STUDENT ) \n " +
                                            "  \t2. Above 60 (  OVER AGE ) \n" +
                                            "  \t3. From 20-60 ( DEFAULT ) \n" +
                                            "\n Enter Range: ");
                        ageChoice = detail.nextInt();

                        //sub menu for options of student member,60+ member and default member
                        switch (ageChoice) {
                            case 1:
                                //name of student member
                                Scanner nameScanner = new Scanner(System.in);
                                System.out.print("\tEnter Member name : ");
                                String name = nameScanner.nextLine();

                                //validates the name of student member
                                while (true) {
                                    if (!name.matches("[a-zA-Z\\s]+")) {
                                        System.out.print(" -----  Invalid name  -----\n");
                                        System.out.print("\tRe-enter Member Name : ");
                                        name = nameScanner.nextLine();
                                    } else {
                                        break;
                                    }
                                }

                                //id of the student member
                                Scanner IdScanner = new Scanner(System.in);
                                System.out.print("\tEnter Member ID :");
                                String id = IdScanner.next();
                                //validating the student id
                                while (true) {
                                        if (!id.matches("^[0-9]*$")) {
                                            System.out.print(" -----  Invalid ID  -----\n");
                                            System.out.print("\tRe-enter Member ID : ");
                                            id=IdScanner.next();
                                        } else {
                                            break;
                                        }
                                    }


                                //enrollment date of the student member
                                Scanner dateScanner=new Scanner(System.in);
                                try {
                                    System.out.print("\tEnrollment Date\n");

                                    System.out.print("\t\tYear:  ");
                                    year = dateScanner.nextInt();

                                    System.out.print("\t\tMonth:  ");
                                    month = dateScanner.nextInt();
                                    //validating month
                                    while (true) {
                                        if (checkMonth(month) == 0) {
                                            System.out.print("\n\t\tMonth:  ");
                                            month = dateScanner.nextInt();
                                        } else {
                                            break;
                                        }
                                    }

                                    System.out.print("\t\tDay:  ");
                                    day = dateScanner.nextInt();
                                    //validating day
                                    while (true) {
                                        if (checkDay(day, month, year) == 0) {
                                            System.out.print("\n\t\tDay:  ");
                                            day = dateScanner.nextInt();
                                        } else {
                                            break;
                                        }
                                    }

                                    //school of the student member
                                    Scanner schoolScanner = new Scanner(System.in);
                                    System.out.print("\tSchool Name: ");
                                    school = schoolScanner.nextLine();
                                    //validating school name
                                    while (true) {
                                        if (!school.matches("[a-zA-Z\\s]+")) {
                                            System.out.print(" -----  Invalid school name -----\n");
                                            System.out.print("\tRe-enter School Name :");
                                            school = schoolScanner.nextLine();
                                        } else {
                                            break;
                                        }
                                    }

                                    //object to call date class
                                    Date startDate = new Date(day, month, year);
                                    //object to call student member class
                                    StudentMember studentDetail = new StudentMember(name, id, school, startDate);
                                    studentDetail.setStartMembershipDate(startDate);
                                    System.out.println("\n\t\t----New Member Added----");
                                    System.out.println(studentDetail);

                                    //insert to array list
                                    storingDetails.add(studentDetail);
                                    //print into the member details file
                                    printWriter.println(studentDetail);
                                } catch (Exception e) {
                                    System.out.println("\t --- Invalid input.");
                                }
                                break;

                            case 2:
                                //name of the 60+ member
                                Scanner nameScanner2 = new Scanner(System.in);
                                System.out.print("\tEnter Member name :");
                                String name2 = nameScanner2.nextLine();
                                //validating name
                                while (true) {
                                    if (!name2.matches("[a-zA-Z\\s]+")) {
                                        System.out.print("----  Invalid name  -----\n");
                                        System.out.print("\tRe-enter Member Name : ");
                                        name2 = nameScanner2.nextLine();
                                    } else {
                                        break;
                                    }
                                }

                                //id of the 60+ member
                                Scanner IdScanner2 = new Scanner(System.in);
                                System.out.print("\tEnter Member ID :");
                                String id2 = IdScanner2.next();
                                //validating id
                                while (true) {

                                        if (!String.valueOf(id2).matches("^[0-9]*$")) {
                                            System.out.print(" -----  Invalid ID  -----\n");
                                            System.out.print("\tRe-enter Member ID : ");
                                            id2=IdScanner2.next();
                                        } else {
                                            break;
                                        }
                                    }


                                //enrolment date of 60+ member
                                Scanner dateScanner2=new Scanner(System.in);
                                try {
                                    System.out.print("\tEnrollment Date\n");
                                    System.out.print("\t\tYear:  ");
                                    year = dateScanner2.nextInt();

                                    //validating month
                                    System.out.print("\t\tMonth:  ");
                                    month = dateScanner2.nextInt();
                                    while (true) {
                                        if (checkMonth(month) == 0) {
                                            System.out.print("\n\t\tMonth:  ");
                                            month = dateScanner2.nextInt();
                                        } else {
                                            break;
                                        }
                                    }
                                    //validating day
                                    System.out.print("\t\tDay:  ");
                                    day = dateScanner2.nextInt();

                                    while (true) {
                                        if (checkDay(day, month, year) == 0) {
                                            System.out.print("\n\t\tDay:  ");
                                            day = dateScanner2.nextInt();
                                        } else {
                                            break;
                                        }
                                    }

                                    //age of 60+ member
                                    Scanner ageScanner = new Scanner(System.in);
                                    System.out.print("\tMember Age: ");
                                    age = ageScanner.nextInt();


                                    Date startDate = new Date(day, month, year);

                                    //object to call 60+ member class
                                    Over60Member above60Detail = new Over60Member(name2, id2, age, startDate);
                                    System.out.println("\n\t\t----New Member Added----");
                                    System.out.print(above60Detail);

                                    //insert to array list
                                    storingDetails.add(above60Detail);
                                    //print into member details file
                                    printWriter.println(above60Detail);

                                } catch (Exception e) {
                                    System.out.println("\t --- Invalid input.");
                                }
                                break;

                            case 3:
                                //name of default member
                                Scanner nameScanner3 = new Scanner(System.in);
                                System.out.print("\tEnter Member name :");
                                String name3 = nameScanner3.nextLine();
                                //validating name
                                while (true) {
                                    if (!name3.matches("[a-zA-Z\\s]+")) {
                                        System.out.print("----  Invalid name  -----\n");
                                        System.out.print("\tRe-enter Member Name : ");
                                        name3 = nameScanner3.nextLine();
                                    } else {
                                        break;
                                    }
                                }

                                //id of default member
                                Scanner IdScanner3 = new Scanner(System.in);
                                System.out.print("\tEnter Member ID :");
                                String id3 = IdScanner3.next();
                                //validating id
                                while (true) {
                                        if (!id3.matches("^[0-9]*$")) {
                                            System.out.print(" -----  Invalid ID  -----\n");
                                            System.out.print("\tRe-enter Member ID : ");
                                            id3=IdScanner3.next();
                                        } else {
                                            break;
                                        }
                                    }

                                //enrollment date of default member
                                Scanner dateScanner3=new Scanner(System.in);
                                try {
                                    System.out.print("\tEnrollment Date\n");
                                    System.out.print("\t\tYear:  ");
                                    year = dateScanner3.nextInt();


                                    System.out.print("\t\tMonth:  ");
                                    month = dateScanner3.nextInt();
                                    //validating month
                                    while (true) {
                                        if (checkMonth(month) == 0) {
                                            System.out.print("\n\t\tMonth: ");
                                            month = dateScanner3.nextInt();
                                        } else {
                                            break;
                                        }
                                    }

                                    System.out.print("\t\tDay:  ");
                                    day = dateScanner3.nextInt();
                                    //validating day
                                    while (true) {
                                        if (checkDay(day, month, year) == 0) {
                                            System.out.print("\n\t\tDay:  ");
                                            day = dateScanner3.nextInt();
                                        } else {
                                            break;
                                        }
                                    }


                                    Date startDate3 = new Date(day, month, year);
                                    //object calling class default member
                                    DefaultMember memberDetail = new DefaultMember(name3, id3, startDate3);
                                    System.out.println("\n\t\t----New Member Added----");
                                    System.out.print(memberDetail);

                                    //insert to array list
                                    storingDetails.add(memberDetail);
                                    //print into the member detail file
                                    printWriter.println(memberDetail);

                                } catch (Exception e) {
                                    System.out.println("\t --- Invalid input.");
                                }
                                break;

                            default:
                                System.out.println("Invalid input... ");
                                addMember();
                        }

                        //closing print writer of member detail text
                        printWriter.close();


            }
            }

            //validating month
            private int checkMonth(int month) {
                if ( month >= 0 && month <= 12)
                    return month;
                else{
                    System.out.printf("\t ---- Invalid month.. ",month);
                    return 0;
                }
            }

            //validating day
            private int checkDay(int day,int month ,int year) {
                int[] daysPerMonth = {31,28,31,30,31,30,31,31,30,31,30,31};

                if(day >= 0 && day <= daysPerMonth[month]) {
                    return day;
                }else if(month == 2 && day == 29) {
                    return day;
                }else {
                    System.out.printf("\t ---- Invalid day... ", day);
                    return 0;
                }

            }



            //deleting a member from the gym --------------------------------------------------------------------------------------------
            @Override
            public void deleteMember() {
                int counter = 0;
                Scanner removeScanner=new Scanner(System.in);
                System.out.print("\n Enter id to delete entry : ");
                String removeNumber = removeScanner.nextLine();
                while (true) {
                    if (removeNumber.matches("[a-zA-Z\\s]+")){
                        System.out.print("\tWrong input..  Enter a valid integer : ");
                        removeNumber=removeScanner.next();
                    }else {
                        //loop to remove member
                    while (true) {
                        if (storingDetails.size() == 0) {
                            System.out.println("\n\tEmpty, no-one to delete..");
                            break;
                        } else if (storingDetails.get(counter).getMembershipNumber().equals(removeNumber)) {
                            System.out.println("\n\tDeleted: ");
                            System.out.println(storingDetails.get(counter));
                            storingDetails.remove(counter);

                            break;
                        } else {
                            counter++;
                        }
                    }
                    break;
                }
                }
            }


            //printing the details in the console ----------------------------------------------------------------------------------------------
            @Override
            public void printDetails() throws FileNotFoundException {
                try {
                int history;
                System.out.print("\n Enter 1 for all past details / Any other key to display current list entered : ");
                history = detail.nextInt();

                    if (history == 1) {
                        //reads member detail file
                        Scanner scan = new Scanner(file);
                        while (scan.hasNextLine()) {
                            System.out.println(scan.nextLine());
                        }
                    } else {
                        //reads the members in the entered array list
                        for (int i = 0; i < storingDetails.size(); i++) {
                            System.out.print("\n" + storingDetails.get(i));
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input");
                }
            }



            //save member in the gym -----------------------------------------------------------------------------------------------------------
            @Override
            public void saveMember() throws IOException {
                System.out.println("\t\tEntered details have been successfully saved" );

                //this option enables to enter the details of the list on click
                File file2=new File("Save List.txt");
                PrintWriter printWriter2;
                printWriter2 = new PrintWriter(new FileWriter(file2));
                for (int i=0;i<storingDetails.size();i++) {
                    printWriter2.println(storingDetails.get(i));
                }
                printWriter2.close();
            }


            //sorting the member according to their names ----------------------------------------------------------------------------------------
            @Override
            public void sortTheList() {
                System.out.println("\t------ Sorted List ------\n");

                //bubble sort method of sorting
                for(int i = 0;  i< storingDetails.size(); i++){
                    for( int j = i+1; j < storingDetails.size(); j++){
                        if(storingDetails.get(j).getMemberName().compareTo(storingDetails.get(i).getMemberName())<0){
                              Collections.swap(storingDetails,j,i);
                        }
                    }
                }
                //display info in list
                for (int i = 0; i < storingDetails.size(); i++) {
                    System.out.println(storingDetails.get(i));
                }

            }


            //opens the gui ---------------------------------------------------------------------------------------------------------------------
            @Override
            public void openGui(Stage guiStage) throws IOException {
                System.out.println("\t------ Opening GUI.. ------");
                guiStage = new Stage();
                guiStage.setTitle("Gym Management System");
                TabPane tabPane=new TabPane();
                Tab historyDetails=new Tab("Past Entries");
                historyDetails.setClosable(false);
                historyDetails.setStyle("-fx-background-color:gray;-fx-font-weight:bold;-fx-padding:5px 150px");
                Tab arrayDetails=new Tab("Entered Details");
                arrayDetails.setClosable(false);
                arrayDetails.setStyle("-fx-background-color:gray;-fx-font-weight:bold;-fx-padding:5px 150px");



                //pane 1 to display in current list
                Pane arrayPane=new Pane();

                Label title=new Label("Gym Management System");
                title.setLayoutY(50);
                title.setLayoutX(500);
                title.setStyle("-fx-font-weight:bold;-fx-font-size:30px;-fx-height:1000px;-fx-background-color:gray;-fx-padding:5px 25px;-fx-text-fill:white");

                TextField searchBar=new TextField();
                searchBar.setPromptText("ID Number");
                searchBar.setLayoutY(140);
                searchBar.setLayoutX(630);

                Button searchBtn =new Button("Search");
                searchBtn.setLayoutY(140);
                searchBtn.setLayoutX(830);
                searchBtn.setStyle("-fx-background-color:#006dd3;-fx-text-fill:white;-fx-padding:5px 35px");


                //crete the table of the pane 1
                TableView detailsTable = new TableView();
                detailsTable.setLayoutX(50);
                detailsTable.setLayoutY(190);


                TableColumn memberId = new TableColumn("Member ID No.");
                memberId.setCellValueFactory(new PropertyValueFactory<DefaultMember, String >("membershipNumber"));
                memberId.setMinWidth(180);


                TableColumn memberName = new TableColumn("Member Name");
                memberName.setCellValueFactory(new PropertyValueFactory<DefaultMember, String>("memberName"));
                memberName.setMinWidth(180);

                TableColumn membershipDate = new TableColumn("Enrollment Date");
                membershipDate.setCellValueFactory(new PropertyValueFactory<DefaultMember, String>("startMembershipDate"));
                membershipDate.setMinWidth(180);

                TableColumn memberAge = new TableColumn("Member Age");
                memberAge.setCellValueFactory(new PropertyValueFactory<Over60Member, String>("Age"));
                memberAge.setMinWidth(180);

                TableColumn memberSchool = new TableColumn("Member School");
                memberSchool.setCellValueFactory(new PropertyValueFactory<StudentMember, String>("schoolName"));
                memberSchool.setMinWidth(180);

                //adding data to the table
                for (int i = 0; i < storingDetails.size(); i++) {
                    DefaultMember tempMember = storingDetails.get(i);
                    detailsTable.getItems().addAll(tempMember);

                }

                searchBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String searchData = searchBar.getText();
                        if (searchData.equals("")){
                            detailsTable.getItems().clear();
                            for (int i = 0; i < storingDetails.size(); i++) {
                                DefaultMember temp = storingDetails.get(i);
                                detailsTable.getItems().addAll(temp);

                            }

                        }else if (searchData.matches("^[0-9]*$")){
                            detailsTable.getItems().clear();
                            for (int i = 0; i < storingDetails.size(); i++) {
                                String searchMember = storingDetails.get(i).getMembershipNumber();
                                if (searchMember.equals(searchData)) {
                                    DefaultMember idSearch = storingDetails.get(i);
                                    detailsTable.getItems().addAll(idSearch);
                                }
                            }

                        }
                    }
                });

                detailsTable.getColumns().addAll(memberId,memberName,membershipDate,memberAge,memberSchool);

                //Setting the Background image for pane 1
                Image gImage = new Image("file:gym2.jpg");
                ImageView gymImg = new ImageView(gImage);
                gymImg.setFitHeight(660);
                gymImg.setFitWidth(1000);
                gymImg.setStyle("-fx-opacity:0.8");

                ScrollPane scrollPane1 = new ScrollPane();
                arrayPane.getChildren().addAll(gymImg,searchBar,searchBtn,detailsTable,title);
                arrayPane.setMinWidth(1000);
                scrollPane1.setContent(arrayPane);


                //pane 2 to display all entries
                Pane historyPane=new Pane();
                historyPane.setStyle("-fx-background-color:#3d4035");
                Label historyTitle=new Label(" <-- All Past Entries");
                historyTitle.setLayoutX(500);
                historyTitle.setLayoutY(280);
                historyTitle.setStyle("-fx-text-fill:gold;-fx-font-size:35px;-fx-font-weight:bold;");

                Label lblDetails = new Label();
                lblDetails.setStyle("-fx-background-color:#001222;-fx-text-fill:white;-fx-font-size:18px");
                String lines;

                //reads the member details file
                FileReader frMemberDetail=new FileReader("Member Details.txt");
                BufferedReader brMemberDetail =new BufferedReader(frMemberDetail);
                while ((lines=brMemberDetail.readLine()) != null){
                    lblDetails.setText(lines+"\n"+lblDetails.getText());
                }


                ScrollPane scrollPane2 = new ScrollPane();
                historyPane.getChildren().addAll(historyTitle,lblDetails);
                historyPane.setMinWidth(1000);
                scrollPane2.setContent(historyPane);


                arrayDetails.setContent(scrollPane1);
                historyDetails.setContent(scrollPane2);
                tabPane.getTabs().addAll(arrayDetails,historyDetails);
                Scene sc = new Scene(tabPane,1020,700);
                guiStage.setScene(sc);
                guiStage.showAndWait();
            }

        }












