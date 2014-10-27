/*
 * 16.09.2014 Roman Hutkovich
 */
package com.epam.rentcar.entity;

import java.sql.Date;

public class Order extends Entity {
    private int idCar;
    private int idAccount;
    private Date term;
    private Date orderDate;
    private boolean free;
    private boolean crashed;

    public int getIdCar() {
        return idCar;
    }
    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }
    public int getIdAccount() {
        return idAccount;
    }
    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }
    public Date getTerm() {
        return term;
    }
    public void setTerm(Date term) {
        this.term = term;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public boolean isFree() {
        return free;
    }
    public void setFree(boolean free) {
        this.free = free;
    }
    public boolean isCrashed() {
        return crashed;
    }
    public void setCrashed(boolean crashed) {
        this.crashed = crashed;
    }
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + (crashed ? 1231 : 1237);
	result = prime * result + (free ? 1231 : 1237);
	result = prime * result + idAccount;
	result = prime * result + idCar;
	result = prime * result
		+ ((orderDate == null) ? 0 : orderDate.hashCode());
	result = prime * result + ((term == null) ? 0 : term.hashCode());
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
	Order other = (Order) obj;
	if (crashed != other.crashed)
	    return false;
	if (free != other.free)
	    return false;
	if (idAccount != other.idAccount)
	    return false;
	if (idCar != other.idCar)
	    return false;
	if (orderDate == null) {
	    if (other.orderDate != null)
		return false;
	} else if (!orderDate.equals(other.orderDate))
	    return false;
	if (term == null) {
	    if (other.term != null)
		return false;
	} else if (!term.equals(other.term))
	    return false;
	return true;
    }
    
    @Override
    public String toString() {
	return "Order [idCar=" + idCar + ", idAccount=" + idAccount + ", term="
		+ term + ", orderDate=" + orderDate + ", free=" + free
		+ ", crashed=" + crashed + "]";
    }
    
    
}
