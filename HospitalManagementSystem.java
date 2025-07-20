import java.util.Scanner;
import java.util.InputMismatchException;

public class HospitalManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientLinkedList patientList = new PatientLinkedList();
        DoctorLinkedList doctorList = new DoctorLinkedList();
        AppointmentQueue appointmentQueue = new AppointmentQueue();
        BillManager billManager = new BillManager();

        int choice;
        do {
            System.out.println("\nHospital Management System");
            System.out.println("1. Patient Management");
            System.out.println("2. Doctor Management");
            System.out.println("3. View All Bills");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = getValidIntegerInput(scanner);

            switch (choice) {
                case 1:
                    patientMenu(scanner, patientList, doctorList, appointmentQueue, billManager);
                    break;
                case 2:
                    doctorMenu(scanner, doctorList);
                    break;
                case 3:
                    System.out.println("\nBills:");
                    billManager.viewAllBills();
                    break;
                case 4:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    public static int getValidIntegerInput(Scanner scanner) {
        int input = -1;
        while (true) {
            try {
                input = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the buffer
            }
        }
    }

    public static void patientMenu(Scanner scanner, PatientLinkedList patientList, DoctorLinkedList doctorList, AppointmentQueue appointmentQueue, BillManager billManager) {
        int choice;
        do {
            System.out.println("\nPatient Menu");
            System.out.println("1. Add Patient");
            System.out.println("2. Display All Patients");
            System.out.println("3. Search Patient");
            System.out.println("4. Update Patient");
            System.out.println("5. Delete Patient");
            System.out.println("6. Book Appointment");
            System.out.println("7. View Appointments");
            System.out.println("8. Exit to Main Menu");
            System.out.print("Enter your choice: ");
            choice = getValidIntegerInput(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Enter Patient ID: ");
                    int id = getValidIntegerInput(scanner);
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Patient Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Patient Age: ");
                    int age = getValidIntegerInput(scanner);
                    System.out.print("Enter Patient Disease: ");
                    String disease = scanner.nextLine();
                    patientList.addPatient(new Patient(id, name, age, disease));
                    break;
                case 2:
                    System.out.println("Patient List:");
                    patientList.displayPatients();
                    break;
                case 3:
                    System.out.print("Enter Patient ID to search: ");
                    int searchId = getValidIntegerInput(scanner);
                    Patient patient = patientList.searchPatient(searchId);
                    if (patient != null) {
                        System.out.println("Patient Found: " + patient);
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Patient ID to update: ");
                    int updateId = getValidIntegerInput(scanner);
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Age: ");
                    int newAge = getValidIntegerInput(scanner);
                    System.out.print("Enter New Disease: ");
                    String newDisease = scanner.nextLine();
                    boolean updated = patientList.updatePatient(updateId, newName, newAge, newDisease);
                    if (updated) {
                        System.out.println("Patient updated successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter Patient ID to delete: ");
                    int deleteId = getValidIntegerInput(scanner);
                    boolean deleted = patientList.deletePatient(deleteId);
                    if (deleted) {
                        System.out.println("Patient deleted successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter Patient ID to book appointment: ");
                    int patientId = getValidIntegerInput(scanner);
                    Patient appointmentPatient = patientList.searchPatient(patientId);
                    if (appointmentPatient != null) {
                        System.out.println("Available Doctors:");
                        doctorList.displayDoctors();
                        System.out.print("Enter Doctor ID for appointment: ");
                        int doctorId = getValidIntegerInput(scanner);
                        Doctor selectedDoctor = doctorList.searchDoctor(doctorId);
                        if (selectedDoctor != null) {
                            System.out.print("Enter Appointment Time: ");
                            scanner.nextLine(); // Consume newline
                            String time = scanner.nextLine();
                            Appointment appointment = new Appointment(patientId, appointmentPatient, selectedDoctor, time);
                            appointmentQueue.enqueue(appointment);
                            System.out.println("Appointment booked successfully.");
                            billManager.generateBill(appointmentPatient, selectedDoctor);
                        } else {
                            System.out.println("Doctor not found.");
                        }
                    } else {
                        System.out.println("Patient not found. Please add the patient first.");
                    }
                    break;
                case 7:
                    System.out.println("Appointment Queue:");
                    appointmentQueue.displayQueue();
                    break;
                case 8:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
    }

    public static void doctorMenu(Scanner scanner, DoctorLinkedList doctorList) {
        int choice;
        do {
            System.out.println("\nDoctor Menu");
            System.out.println("1. Add Doctor");
            System.out.println("2. Display All Doctors");
            System.out.println("3. Search Doctor");
            System.out.println("4. Update Doctor");
            System.out.println("5. Delete Doctor");
            System.out.println("6. Exit to Main Menu");
            System.out.print("Enter your choice: ");
            choice = getValidIntegerInput(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Enter Doctor ID: ");
                    int id = getValidIntegerInput(scanner);
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Doctor Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Doctor Specialization: ");
                    String specialization = scanner.nextLine();
                    System.out.print("Enter Doctor Fees: ");
                    double fees = scanner.nextDouble();
                    doctorList.addDoctor(new Doctor(id, name, specialization, fees));
                    break;
                case 2:
                    System.out.println("Doctor List:");
                    doctorList.displayDoctors();
                    break;
                case 3:
                    System.out.print("Enter Doctor ID to search: ");
                    int searchId = getValidIntegerInput(scanner);
                    Doctor doctor = doctorList.searchDoctor(searchId);
                    if (doctor != null) {
                        System.out.println("Doctor Found: " + doctor);
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Doctor ID to update: ");
                    int updateId = getValidIntegerInput(scanner);
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Specialization: ");
                    String newSpecialization = scanner.nextLine();
                    System.out.print("Enter New Fees: ");
                    double newFees = scanner.nextDouble();
                    boolean updated = doctorList.updateDoctor(updateId, newName, newSpecialization, newFees);
                    if (updated) {
                        System.out.println("Doctor updated successfully.");
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter Doctor ID to delete: ");
                    int deleteId = getValidIntegerInput(scanner);
                    boolean deleted = doctorList.deleteDoctor(deleteId);
                    if (deleted) {
                        System.out.println("Doctor deleted successfully.");
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                case 6:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }
}
