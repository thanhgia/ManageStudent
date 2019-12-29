/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gia.student;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class StudentError implements Serializable{
    private String idLengthError;
    private String firstNameLengthError;
    private String middleNameLengthError;
    private String lastNameLengthError;
    private String address1LengthError;
    private String address2LengthError;
    private String phoneLengthError;
    private String phoneIsNotNumber;
    private String phoneWrongFormat;
    private String classLengthError;
    private String idIsExist;

    public StudentError() {
    }

    public StudentError(String idLengthError, String firstNameLengthError,String middleNameLengthError ,String lastNameLengthError, String address1LengthError, String address2LengthError, String phoneLengthError, String phoneIsNotNumber, String phoneWrongFormat, String classLengthError, String idIsExist) {
        this.idLengthError = idLengthError;
        this.firstNameLengthError = firstNameLengthError;
        this.middleNameLengthError = middleNameLengthError;
        this.lastNameLengthError = lastNameLengthError;
        this.address1LengthError = address1LengthError;
        this.address2LengthError = address2LengthError;
        this.phoneLengthError = phoneLengthError;
        this.phoneIsNotNumber = phoneIsNotNumber;
        this.phoneWrongFormat = phoneWrongFormat;
        this.classLengthError = classLengthError;
        this.idIsExist = idIsExist;
    }

    

    

    /**
     * @return the idLengthError
     */
    public String getIdLengthError() {
        return idLengthError;
    }

    /**
     * @param idLengthError the idLengthError to set
     */
    public void setIdLengthError(String idLengthError) {
        this.idLengthError = idLengthError;
    }

    /**
     * @return the lastNameLengthError
     */
    public String getLastNameLengthError() {
        return lastNameLengthError;
    }

    /**
     * @param lastNameLengthError the lastNameLengthError to set
     */
    public void setLastNameLengthError(String lastNameLengthError) {
        this.lastNameLengthError = lastNameLengthError;
    }

    
    /**
     * @return the firstNameLengthError
     */
    public String getFirstNameLengthError() {
        return firstNameLengthError;
    }

    /**
     * @param firstNameLengthError the firstNameLengthError to set
     */
    public void setFirstNameLengthError(String firstNameLengthError) {
        this.firstNameLengthError = firstNameLengthError;
    }

    /**
     * @return the address1LengthError
     */
    public String getAddress1LengthError() {
        return address1LengthError;
    }

    /**
     * @param address1LengthError the address1LengthError to set
     */
    public void setAddress1LengthError(String address1LengthError) {
        this.address1LengthError = address1LengthError;
    }

    /**
     * @return the address2LengthError
     */
    public String getAddress2LengthError() {
        return address2LengthError;
    }

    /**
     * @param address2LengthError the address2LengthError to set
     */
    public void setAddress2LengthError(String address2LengthError) {
        this.address2LengthError = address2LengthError;
    }

    /**
     * @return the phoneLengthError
     */
    public String getPhoneLengthError() {
        return phoneLengthError;
    }

    /**
     * @param phoneLengthError the phoneLengthError to set
     */
    public void setPhoneLengthError(String phoneLengthError) {
        this.phoneLengthError = phoneLengthError;
    }

    /**
     * @return the classLengthError
     */
    public String getClassLengthError() {
        return classLengthError;
    }

    /**
     * @param classLengthError the classLengthError to set
     */
    public void setClassLengthError(String classLengthError) {
        this.classLengthError = classLengthError;
    }

    /**
     * @return the idIsExist
     */
    public String getIdIsExist() {
        return idIsExist;
    }

    /**
     * @param idIsExist the idIsExist to set
     */
    public void setIdIsExist(String idIsExist) {
        this.idIsExist = idIsExist;
    }

    /**
     * @return the phoneIsNotNumber
     */
    public String getPhoneIsNotNumber() {
        return phoneIsNotNumber;
    }

    /**
     * @param phoneIsNotNumber the phoneIsNotNumber to set
     */
    public void setPhoneIsNotNumber(String phoneIsNotNumber) {
        this.phoneIsNotNumber = phoneIsNotNumber;
    }

    /**
     * @return the phoneWrongFormat
     */
    public String getPhoneWrongFormat() {
        return phoneWrongFormat;
    }

    /**
     * @param phoneWrongFormat the phoneWrongFormat to set
     */
    public void setPhoneWrongFormat(String phoneWrongFormat) {
        this.phoneWrongFormat = phoneWrongFormat;
    }

    /**
     * @return the middleNameLengthError
     */
    public String getMiddleNameLengthError() {
        return middleNameLengthError;
    }

    /**
     * @param middleNameLengthError the middleNameLengthError to set
     */
    public void setMiddleNameLengthError(String middleNameLengthError) {
        this.middleNameLengthError = middleNameLengthError;
    }
    
    
}
