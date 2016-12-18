/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsClient;

/**
 *
 * @author Rudolf
 */
public class MedObj {

    String med1_name, med2_name, med1_rxcui,med2_rxcui, description, severity;

    public MedObj() {
        med1_name = "";
        med2_name = "";
        med1_rxcui = "";
        med2_rxcui = "";
        description = "";
        severity = "";
    }

    public String getMed1_rxcui() {
        return med1_rxcui;
    }

    public void setMed1_rxcui(String med1_rxcui) {
        this.med1_rxcui = med1_rxcui;
    }

    public String getMed2_rxcui() {
        return med2_rxcui;
    }

    public void setMed2_rxcui(String med2_rxcui) {
        this.med2_rxcui = med2_rxcui;
    }

    public String getMed1_name() {
        return med1_name;
    }

    public void setMed1_name(String med1_name) {
        this.med1_name = med1_name;
    }

    public String getMed2_name() {
        return med2_name;
    }

    public void setMed2_name(String med2_name) {
        this.med2_name = med2_name;
    }

 

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
    
    

}
