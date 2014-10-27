/*
 * 16.09.2014 Roman Hutkovich
 */
package com.epam.rentcar.entity;

import java.sql.*;

public class Client extends Entity {
    private String name;
    private String passportNumber;
    private Date passportDate;
    private String passportDepart;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassportNumber() {
        return passportNumber;
    }
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
    public Date getPassportDate() {
        return passportDate;
    }
    public void setPassportDate(Date passportDate) {
        this.passportDate = passportDate;
    }
    public String getPassportDepart() {
        return passportDepart;
    }
    public void setPassportDepart(String passportDepart) {
        this.passportDepart = passportDepart;
    }
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result
		+ ((passportDate == null) ? 0 : passportDate.hashCode());
	result = prime * result
		+ ((passportDepart == null) ? 0 : passportDepart.hashCode());
	result = prime * result
		+ ((passportNumber == null) ? 0 : passportNumber.hashCode());
	return result;
    }
    
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Client other = (Client) obj;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (passportDate == null) {
	    if (other.passportDate != null)
		return false;
	} else if (!passportDate.equals(other.passportDate))
	    return false;
	if (passportDepart == null) {
	    if (other.passportDepart != null)
		return false;
	} else if (!passportDepart.equals(other.passportDepart))
	    return false;
	if (passportNumber == null) {
	    if (other.passportNumber != null)
		return false;
	} else if (!passportNumber.equals(other.passportNumber))
	    return false;
	return true;
    }
    
    @Override
    public String toString() {
	return "Client [name=" + name + ", passportNumber=" + passportNumber
		+ ", passportDate=" + passportDate + ", passportDepart="
		+ passportDepart + "]";
    }
    
    
}
