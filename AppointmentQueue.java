class AppointmentQueue {
    private Node front;
    private Node rear;

    private static class Node {
        Appointment data;
        Node next;

        Node(Appointment data) {
            this.data = data;
            this.next = null;
        }
    }

    public void enqueue(Appointment appointment) {
        Node newNode = new Node(appointment);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        System.out.println("Appointment added to the queue.");
    }

    public Appointment dequeue() {
        if (front == null) {
            System.out.println("No appointments in the queue.");
            return null;
        }
        Appointment appointment = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return appointment;
    }

    public void displayQueue() {
        if (front == null) {
            System.out.println("No appointments in the queue.");
            return;
        }
        Node temp = front;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}