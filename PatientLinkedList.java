class PatientLinkedList {
    private Node head;

    private static class Node {
        Patient data;
        Node next;

        Node(Patient data) {
            this.data = data;
            this.next = null;
        }
    }

    public void addPatient(Patient patient) {
        Node newNode = new Node(patient);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println("Patient added successfully.");
    }

    public void displayPatients() {
        if (head == null) {
            System.out.println("No patients available.");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public Patient searchPatient(int id) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.id == id) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public boolean deletePatient(int id) {
        if (head == null) return false;

        if (head.data.id == id) {
            head = head.next;
            return true;
        }

        Node temp = head;
        while (temp.next != null && temp.next.data.id != id) {
            temp = temp.next;
        }

        if (temp.next != null) {
            temp.next = temp.next.next;
            return true;
        }
        return false;
    }

    public boolean updatePatient(int id, String newName, int newAge, String newDisease) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.id == id) {
                temp.data.name = newName;
                temp.data.age = newAge;
                temp.data.disease = newDisease;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
}