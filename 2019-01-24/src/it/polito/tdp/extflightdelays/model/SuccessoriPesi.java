package it.polito.tdp.extflightdelays.model;

public class SuccessoriPesi implements Comparable<SuccessoriPesi> {
	
	private String successore;
	private int peso;
	public SuccessoriPesi(String successore, int peso) {
		super();
		this.successore = successore;
		this.peso = peso;
	}
	public String getSuccessore() {
		return successore;
	}
	public void setSuccessore(String successore) {
		this.successore = successore;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int compareTo(SuccessoriPesi altro) {
		
		return -(this.peso-altro.getPeso());
	}
	
	

}
