package cn.nest.converter.model;

import java.io.Serializable;

public class Vscode implements Serializable {

    private String visaCode;

    private String visaName;

    public String getVisaCode() {
        return visaCode;
    }

    public void setVisaCode(String visaCode) {
        this.visaCode = visaCode;
    }

    public String getVisaName() {
        return visaName;
    }

    public void setVisaName(String visaName) {
        this.visaName = visaName;
    }
}