package com.jsfcourse.calc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private String amount;
	private String years;
	private String interestRate;
	private Double result;

	@Inject
	FacesContext ctx;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}
	
	
	
	public boolean calculateMonthlyRate() {
		try {
			double amount = Double.parseDouble(this.amount);
			double years = Double.parseDouble(this.years);
			double interestRate = Double.parseDouble(this.interestRate);
			
			double interest = amount * years * interestRate * 0.01;
			double months = years * 12;
			
			result = (double) Math.round(((amount + interest) / months) * 100) / 100;
			
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		}catch(Exception e) {
			ctx.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}
	
	public String calc() {
		if(calculateMonthlyRate()) {
			return "resultpage";
		} else {
			return null;
		}
	}
}
