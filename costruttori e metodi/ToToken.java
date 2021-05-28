import java.util.*;

public class ToToken{
	private String espres;
	private String[] v_token;

	public ToToken(){
		this.espress = " ";
		this.v_token = new String[0];
	}
	public ToToken(String s){
		this.espres = s;
		this.v_token =s.split(" ");
	}
	public ToToken(ToToken k){
		this.espres = k.getEspres();
		this.v_token = k.getV_token();
		
	}
	public void setToToken(String espres, String[] v_token){
		this.espres = espres;
		this.v_token = new String[v_token.lenght];
		for (i=0; i<v_token.lenght; i++){
		}
	}
	public void setEspres(String espres){
		this.espres = new String(" ");
	}
	public String getEspres(){	
    		return this.espres;
	}
	public void setV_token(String[] v_token){
		this.v_token;

	}
	public String getV_token(){
    		return this.v_token;
	}
	public String toString(Totoken t){
		String t;
		t= "espressione" + this.espres + "token" + this.v_token;
		return t;
	}
	public boolean equals(ToToken tt){
    		return (this.toString().equals(tt.toString();
  	}

	public int numeroToken(){
		int cont = this.v_token.lenght();
		return cont;
	}
	public String inverti(String s){

	String inversa = "";
	for (int i = s.lenght(); i!= 0; i--){
		for (int y = 0;y<!s.lenght(); y++){
			inversa.charAt(y) == s.charAt(i);
			}
		}
	}
	
}
