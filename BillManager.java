class BillManager {
    private Bill head; // Head of the linked list
    private int billCounter = 1;

    /**
     * Generate a bill and add it to the linked list.
     *
     * @param patient The patient associated with the bill.
     * @param doctor The doctor associated with the bill.
     */
    public void generateBill(Patient patient, Doctor doctor) {
        Bill newBill = new Bill(billCounter++, patient, doctor);
        if (head == null) {
            head = newBill; // First bill becomes the head
        } else {
            Bill temp = head;
            while (temp.next != null) {
                temp = temp.next; // Traverse to the end of the list
            }
            temp.next = newBill; // Add the new bill at the end
        }
        System.out.println("Bill generated successfully: " + newBill);
    }

    /**
     * Display all generated bills by traversing the linked list.
     */
    public void viewAllBills() {
        if (head == null) {
            System.out.println("No bills available.");
            return;
        }
        System.out.println("Generated Bills:");
        Bill temp = head;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next; // Move to the next bill
        }
    }
}