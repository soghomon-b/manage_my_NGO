package ui;

import model.*;
import model.Event;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;


// The class that produces the user interface
public class MyFrame extends JFrame {
    private JButton buttonSetVolunteer;
    private JButton buttonSetStudent;
    private JButton buttonSetTask;
    private JButton buttonGetVolInfo;
    private JButton buttonGetStuInfo;
    private JButton buttonSave;
    private JButton buttonRetrieve;
    private JButton buttonAssignStudent;
    private JButton viewVolunteersStudents;
    private JButton viewVolunteersTasks;
    private JLabel label = new JLabel();
    private OrganisationInteraction org;
    private LogWriter logWriter;


    // MODIFIES: this, org
    // EFFECTS: Initializes the frame with the specified name, sets up the organization, and initiates buttons.
    MyFrame(String name) {
        super(name); // Set the frame's title
        org = new OrganisationInteraction("SYE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 420);
        setResizable(false);
        setVisible(true);
        initiateButtons();
        getLabel();
        logWriter = new LogWriter();
        org.getOrganisation().addObserver(logWriter);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                displayLog();
            }
        });
    }


    //EFFECTS: produces the label essential for the UI.
    public void getLabel() {
        label.setText("Hello SYE! Welcome to the portal");
        label.setHorizontalTextPosition(JLabel.CENTER);
        this.add(label);

        Border border = BorderFactory.createLineBorder(Color.black);
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.CENTER);

        this.setLayout(null);
        label.setBounds(0,0,200,30);

        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setBounds(0,0,200,30);
        redPanel.add(label);
        this.add(redPanel);
    }

    // MODIFIES: this
    // EFFECTS: Initiates buttons with specific labels and positions.
    private void initiateButtons() {
        startButtons();
        setButtom(buttonSetVolunteer, "Add a volunteer", new Position(110, 50, 150, 20));
        setButtom(buttonSetStudent, "Add a student", new Position(110, 72, 150, 20));
        setButtom(buttonSetTask, "Add a task", new Position(110, 94, 150, 20));
        setButtom(buttonGetVolInfo, "Volunteer info", new Position(110, 116, 150, 20));
        setButtom(buttonGetStuInfo, "Student info", new Position(110, 138, 150, 20));
        setButtom(buttonSave, "save", new Position(10, 300, 150, 20));
        setButtom(buttonRetrieve, "retrieve", new Position(10, 322, 150, 20));
        setButtom(buttonAssignStudent, "assign students", new Position(110, 160, 150, 20));
        setButtom(viewVolunteersStudents, "view students", new Position(110, 182, 150, 20));
        setButtom(viewVolunteersTasks, "view tasks", new Position(110, 204, 150, 20));
        buttonSetStudent.addActionListener(e -> addStudentToOrg());
        buttonSetVolunteer.addActionListener(e -> addVolunteerToOrg());
        buttonSetTask.addActionListener(e -> addTaskToOrg());
        buttonGetVolInfo.addActionListener(e -> displayVolunteerInformation());
        buttonGetStuInfo.addActionListener(e -> displayStudentInformation());
        buttonSave.addActionListener(e -> org.saveOrg());
        buttonRetrieve.addActionListener(e -> getOrg());
        buttonAssignStudent.addActionListener(e -> assignStudent());
        viewVolunteersStudents.addActionListener(e -> viewStudents());
        viewVolunteersTasks.addActionListener(e -> viewTasks());
    }

    //EFFECTS: initiates buttons
    private void startButtons() {
        buttonSetVolunteer = new JButton();
        buttonSetStudent = new JButton();
        buttonSetTask = new JButton();
        buttonGetVolInfo = new JButton();
        buttonGetStuInfo = new JButton();
        buttonSave = new JButton();
        buttonRetrieve = new JButton();
        buttonAssignStudent = new JButton();
        viewVolunteersStudents = new JButton();
        viewVolunteersTasks = new JButton();
    }

    //EFFECTS: displays the tasks assigned to the given volunteer
    private void viewTasks() {
        String idInput = JOptionPane.showInputDialog(this, "Enter volunteer ID:");
        int volunteerId = Integer.parseInt(idInput);
        StringBuilder info = new StringBuilder("Here are the students for ");
        for (Volunteer vol : org.getVolunteers()) {
            if (vol.getId() == volunteerId) {
                info.append(vol.getName() + "\n");
                for (Task s : vol.getListOfTask()) {
                    info.append(s.getTitle() + "\n");
                }

            }
        }
        JOptionPane.showMessageDialog(this, info.toString());
    }

    //EFFECTS: displays the students assigned to the given volunteer
    private void viewStudents() {
        String idInput = JOptionPane.showInputDialog(this, "Enter volunteer ID:");
        int volunteerId = Integer.parseInt(idInput);
        StringBuilder info = new StringBuilder("Here are the students for ");
        for (Volunteer vol : org.getVolunteers()) {
            if (vol.getId() == volunteerId) {
                info.append(vol.getName() + "\n");
                for (Student s : vol.getListOfStudent()) {
                    info.append(s.getInformation() + "\n");
                }

            }
        }
        JOptionPane.showMessageDialog(this, info.toString());
    }

    //MODIFIES: org
    //EFFECTS: assigns the student to the given volunteer
    private void assignStudent() {
        String idInput = JOptionPane.showInputDialog(this, "Enter Student ID:");
        int stId = Integer.parseInt(idInput);
        String volInput = JOptionPane.showInputDialog(this, "Enter volunteer ID:");
        int volId = Integer.parseInt(volInput);
        Student student = new Student("", 0);
        for (Student s : org.getStudents()) {
            if (s.getId() == stId) {
                student = s;
            }
        }
        for (Volunteer volunteer : org.getVolunteers()) {
            if (volunteer.getId() == volId) {
                volunteer.addStudent(student);
                JOptionPane.showMessageDialog(this, "student added to volunteer successfully!");
            }
        }

    }

    //EFFECTS: retrieves the saved information
    private void getOrg() {
        try {
            org.readOrg();
            showMessageDialogWithImage("Info Retrieved successfully!", "correct.jpg");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Sorry, problem in info retrieval");
        }
    }

    //EFFECTS: displays the image when info is retrieved successfully
    private void showMessageDialogWithImage(String message, String imagePath) {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        Icon icon = new ImageIcon(getClass().getResource(imagePath));
        optionPane.setIcon(icon);

        JDialog dialog = optionPane.createDialog(this, "Information");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    //MODIFIES: button
    //EFFECTS: sets the title and the position of the given button
    void setButtom(JButton button, String title, Position pos) {
        button.setText(title);
        button.setBounds(pos.getX(), pos.getY(), pos.getWidth(), pos.getHeight());
        this.add(button);
    }

    //MODIFIES: org
    //EFFECTS: adds a student to the organisation
    private void addStudentToOrg() {
        String studentName = JOptionPane.showInputDialog(this, "Enter student name:");
        int studentId;
        String idInput = JOptionPane.showInputDialog(this, "Enter student ID:");
        studentId = Integer.parseInt(idInput);
        Student newStudent = new Student(studentName, studentId);
        org.addStudent(newStudent);
        JOptionPane.showMessageDialog(this, "Student added successfully!");
    }

    //MODIFIES: org
    //EFFECTS: adds a task to the organisation
    private void addTaskToOrg() {
        String taskName = JOptionPane.showInputDialog(this, "Enter task name:");
        int taskHours;
        String hoursInput = JOptionPane.showInputDialog(this, "Enter hours for the task:");
        taskHours = Integer.parseInt(hoursInput);

        Task newTask = new Task(taskName, taskHours);
        org.addTask(newTask);

        int volId;
        String volIdInput = JOptionPane.showInputDialog(this, "Enter volunteer id:");
        volId = Integer.parseInt(volIdInput);

        for (Volunteer vol : org.getVolunteers()) {
            if (vol.getId() == volId) {
                vol.addTask(newTask);
                JOptionPane.showMessageDialog(this, "Task added to " + vol.getName()
                        + " successfully!");
            }
        }


    }

    //MODIFIES: org
    //EFFECTS: adds a volunteer to the organisation
    private void addVolunteerToOrg() {
        String volunteerName = JOptionPane.showInputDialog(this, "Enter volunteer name:");
        int volunteerId;
        String idInput = JOptionPane.showInputDialog(this, "Enter volunteer ID:");
        volunteerId = Integer.parseInt(idInput);
        String volunteerPhone = JOptionPane.showInputDialog(this, "Enter volunteer phone:");
        int maximumHours;
        String maxHoursInput = JOptionPane.showInputDialog(this, "Enter maximum hours:");
        maximumHours = Integer.parseInt(maxHoursInput);

        Volunteer newVolunteer = new Volunteer(volunteerName, volunteerId, volunteerPhone, maximumHours);
        org.addVolunteer(newVolunteer);

        JOptionPane.showMessageDialog(this, "Volunteer added successfully!");
    }


    //EFFECTS: shows the information of the students in the organisation
    private void displayStudentInformation() {
        JOptionPane.showMessageDialog(this, org.getOrganisation().getStudentInformation());
    }

    //EFFECTS: shows the information of the volunteers in the organisation
    private void displayVolunteerInformation() {

        JOptionPane.showMessageDialog(this, org.getOrganisation().getVolunteerInformation());


    }

    //EFFECTS: Displays the actions happened during the UI use.
    public void displayLog() {
        for (Event event : EventLog.getInstance()) {
            System.out.println(event.toString());
        }
    }

}

