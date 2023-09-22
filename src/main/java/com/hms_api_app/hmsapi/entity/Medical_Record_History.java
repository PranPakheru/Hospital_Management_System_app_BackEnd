package com.hms_api_app.hmsapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="medical_records_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medical_Record_History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "illness_history")
    private String illnessHistory;

    @Column(name = "illness_diagnosis")
    private String illnessDiagnosis;

    @Column(name = "illness_treatment")
    private String illnessTreatment;

    @Column(name = "current_illness")
    private String currentIllness;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "doctor_name")
    private String doctorName;
}
