class Appointment {
    int appointmentId;
    Patient patient;
    Doctor doctor;
    String time;

    public Appointment(int appointmentId, Patient patient, Doctor doctor, String time) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId + ", Patient: " + patient.name + ", Doctor: " +
                doctor.name + ", Time: " + time;
    }
}