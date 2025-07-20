class Doctor {
    int id;
    String name;
    String specialization;
    double fees;

    public Doctor(int id, String name, String specialization, double fees) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "Doctor ID: " + id + ", Name: " + name + ", Specialization: " + specialization + ", Fees: " + fees;
    }
}