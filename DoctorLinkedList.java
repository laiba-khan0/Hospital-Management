class DoctorLinkedList {
    private Node head;

    private static class Node {
        Doctor data;
        Node next;

        Node(Doctor data) {
            this.data = data;
            this.next = null;
        }
    }

    public void addDoctor(Doctor doctor) {
        Node newNode = new Node(doctor);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println("Doctor added successfully.");
    }

    public void displayDoctors() {
        if (head == null) {
            System.out.println("No doctors available.");
            return;
        }
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public Doctor searchDoctor(int id) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.id == id) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    public boolean deleteDoctor(int id) {
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

    public boolean updateDoctor(int id, String newName, String newSpecialization, double newFees) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.id == id) {
                temp.data.name = newName;
                temp.data.specialization = newSpecialization;
                temp.data.fees = newFees;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
}