package cn.micit.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MedicalInfo {
    private long medicalInfoId;
    private String bandMake;
    private String medicalInfoClassification;
    private String description;
    private String aidInstructions;
    public MedicalInfo() {}
}
