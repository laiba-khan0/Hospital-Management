class Bill {
    int billId;
    Patient patient;
    Doctor doctor;
    double amount;
    Bill next; // Pointer to the next bill

    public Bill(int billId, Patient patient, Doctor doctor) {
        this.billId = billId;
        this.patient = patient;
        this.doctor = doctor;
        this.amount = doctor.fees;
        this.next = null; // Initially, there is no next bill
    }

    @Override
    public String toString() {
        return "Bill ID: " + billId + ", Patient: " + patient.name + ", Doctor: " + doctor.name + ", Amount: " + amount;
    }
}
