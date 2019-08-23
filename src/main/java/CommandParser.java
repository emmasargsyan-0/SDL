import java.util.Scanner;

public class CommandParser {

    private StudentDAO studentDAO;
    private FacultyDAO facultyDAO;
    private  Printer printer;

    public CommandParser(StudentDAO studentDAO, FacultyDAO facultyDAO, Printer printer){
        this.studentDAO = studentDAO;
        this.facultyDAO = facultyDAO;
        this.printer = printer;
    }

    public boolean readCommand(Scanner scan) throws Exception{
        String command = scan.next();
        String name;
        int id;
        switch (command){
            case "exit":
                return false;
            case "allStudentsList":
                printer.printTable(studentDAO.allStudentList());
                break;
            case "allStudents":
                printer.printTable(studentDAO.allStudents());
                break;
            case "addStudent":
                System.out.println("Enter student name");
                name = scan.next();
                System.out.println("Enter faculty id");
                id = scan.nextInt();
                System.out.println(studentDAO.add(new Student(name, id)));
                break;
            case "updateStudent":
                System.out.println("Enter student id");
                id = scan.nextInt();
                System.out.println("Enter student new name");
                name = scan.next();
                System.out.println(studentDAO.update(id, name));
                break;
            case "studentByID":
                System.out.println("Enter student id");
                id = scan.nextInt();
                printer.printTable(studentDAO.studentsInfo(id));
                printer.printTable(studentDAO.studentsGrade(id));
                break;
            case "faculties":
                printer.printTable(facultyDAO.faculties());
                break;
            case "studentsByFacilty":
                System.out.println("Enter faculty id");
                id = scan.nextInt();
                printer.printTable(studentDAO.studentsByFaculty(id));
                break;
            case "top1":
          //      printer.printTable(studentDAO.top1());
                break;
            case "all":
                facultyDAO.all();
                break;
            default:
                throw new java.lang.RuntimeException("Unsupported command");
        }
        return true;
    }

}
