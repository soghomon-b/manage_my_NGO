package ui;

import model.Student;
import model.Volunteer;

// code not used in the organisation
public class NotUsedFunctions  {
    /*

    void initiate() {
        System.out.println("Welcome to " + name);
        do {
            System.out.println("What is your role? (write 1 for volunteer, 2 for student, 3 leadership, -1 to exit)");
            int answer = scanner.nextInt();
            scanner.nextLine();

            switch (answer) {
                case 1:
                    volunteerInteraction();
                    break;
                case 2:
                    studentInteraction();
                    break;
                case 3:
                    organisationInteraction();
                    break;
                case -1:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid entry, try again");
                    break;
            }
        } while (running);

        System.out.println("Thanks for your work, see you soon!");
    }
    void volunteerInteraction() {
        while (true) {
            System.out.println("What is your id number? (-1 to exit)");
            int volunteerId = scanner.nextInt();

            if (volunteerId == (-1)) {
                break; // Exit the loop
            }

            int code;
            int volunteerFound = 0;

            for (Volunteer volunteer : volunteers) {
                if (volunteer.getId() == volunteerId) {
                    volunteerFound = 1;
                    System.out.println("Welcome " + volunteer.getName() + "!");
                    System.out.println("What can we do for you? Choose from the options below: ");
                    System.out.println("1: add/remove students 2: add tasks 3: get total hours");
                    System.out.println("4: get tasks 5: list students 6: work 7: assign homework");
                    code = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    switch (code) {
                        case 1:
                            addRemoveStudent(volunteer);
                            break;
                        case 2:
                            addTask(volunteer);
                            break;
                        case 3:
                            System.out.println("Your total hours are: " + volunteer.getTotalHours());
                            break;
                        case 4:
                            tasksGetter(volunteer);
                            break;
                        case 5:
                            studentsGetter(volunteer);
                            break;
                        case 6:
                            implementationWork(volunteer);
                            break;
                        case 7:
                            implementationAssignHomework(volunteer);
                            break;
                        default:
                            System.out.println("Invalid option.");
                            break;
                    }
                    // Continue the loop after the interaction is complete.
                }
            }
            if (volunteerFound == 0) {
                System.out.println("sorry we could not find you on the system");

            }

        }
    }


    void studentInteraction() {
        while (true) {
            System.out.println("What is your id number? (-1 to exit)");
            String volunteerId = scanner.nextLine();

            if (volunteerId.equals("-1")) {
                break;
            }

            int code = 0;
            int studentFound = 0;

            for (Student student : students) {
                if (Integer.toString(student.getId()).equals(volunteerId)) {
                    studentFound = 1;
                    System.out.println("Welcome " + student.getName() + "!");
                    System.out.println("What can we do for you? Choose from the options below: ");
                    System.out.println("1: do homework 2: set volunteer 3: get volunteer's contact info");
                    System.out.println("4: homework number");
                    code = scanner.nextInt();
                    scanner.nextLine();

                    switch (code) {
                        case 1:
                            System.out.println("We are glass you are doing your homework!");
                            student.doHomework();
                            System.out.println("Now you have " + student.getHomework() + " to do.");
                            break;
                        case 2:
                            setVolunteerImplementation(student);
                            break;
                        case 3:
                            System.out.println("Here is your volunteer's contact information: ");
                            System.out.println(student.getVolunteer().getInformation());
                            break;
                        case 4:
                            System.out.println("You have " + student.getHomework() + " to do.");
                            break;
                    }
                }
            }

            if (studentFound == 0) {
                System.out.println("Sorry, we could not find you on the System!");
            }
        }
    }

    void addRemoveStudent(Volunteer volunteer) {
        System.out.println("enter the name of the student: ");
        String studentName = scanner.nextLine();
        System.out.println("Enter an Id for the student: ");
        int id = scanner.nextInt();
        System.out.println("would you like to remove 1 them or add 2? ");
        int decision = scanner.nextInt();
        if (decision == 1) {
            for (Student student : students) {
                if (student.getId() == id && volunteer.getListOfStudent().contains(student)) {
                    volunteer.removeStudent(student);
                }
            }
            System.out.println("student removed successfully!");
        } else if (decision == 2) {
            Student student = new Student(studentName, id);
            volunteer.addStudent(student);
            students.add(student);
            student.setVolunteer(volunteer);
            System.out.println("Student Added Successfully!");
        }

    }

    void addTask(Volunteer volunteer) {
        System.out.println("enter the name of the task: ");
        String taskName = scanner.nextLine();
        System.out.println("Enter the hours required for the task: ");
        int hours = scanner.nextInt();
        Task task = new Task(taskName, hours);
        Boolean add = volunteer.addTask(task);
        tasks.add(task);
        if (add == true) {
            System.out.println("Task added successfully!");
        } else {
            System.out.println("Task adding is unsuccessful, make sure your task's hours are appropriate!");
        }

    }


    void implementationWork(Volunteer volunteer) {
        System.out.println("Yay! We are glad you are working! For how many hours would you like to work? ");
        int hours = scanner.nextInt();
        volunteer.work(hours);
        System.out.println("work finished, thanks for working!");

    }

    void implementationAssignHomework(Volunteer volunteer) {
        System.out.println("Enter name of student: ");
        int studentId = scanner.nextInt();
        for (Student student : volunteer.getListOfStudent()) {
            if (student.getId() == studentId) {
                volunteer.assignHomework(student);
                System.out.println("homework assigned successfully to " + student.getName());
            }
        }

    }

    void tasksGetter(Volunteer volunteer) {
        System.out.println("Here are your tasks: ");
        for (Task task : volunteer.getListOfTask()) {
            System.out.println(task.getTitle());
        }
    }

    void studentsGetter(Volunteer volunteer) {
        System.out.println("Here are your students: ");
        for (Student student : volunteer.getListOfStudent()) {
            System.out.println(student.getInformation());
        }
    }

    void setVolunteerImplementation(Student student) {
        System.out.println("Enter volunteer's id: ");
        int id = scanner.nextInt();
        for (Volunteer volunteer : volunteers) {
            if (volunteer.getId() == id) {
                student.setVolunteer(volunteer);
            }
        }
        System.out.println("act successful");
    }

     */
}

