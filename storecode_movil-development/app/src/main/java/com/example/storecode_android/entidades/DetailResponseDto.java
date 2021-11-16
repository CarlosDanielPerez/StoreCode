package com.example.storecode_android.entidades;

import androidx.annotation.NonNull;

/**
 * Description:
 * Created by EX383473 on 04/01/2019.
 */
@SuppressWarnings("unused")
public class DetailResponseDto {
    private String code;
    private Integer severityLevel;
    private String description;
    private String actor;
    private String businessMeaning;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(Integer severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getBusinessMeaning() {
        return businessMeaning;
    }

    public void setBusinessMeaning(String businessMeaning) {
        this.businessMeaning = businessMeaning;
    }

    @NonNull
    @Override
    public String toString() {
        return "DetailResponseDto{" +
                "code='" + code + '\'' +
                ", severityLevel=" + severityLevel +
                ", description='" + description + '\'' +
                ", actor='" + actor + '\'' +
                ", businessMeaning='" + businessMeaning + '\'' +
                '}';
    }
}
