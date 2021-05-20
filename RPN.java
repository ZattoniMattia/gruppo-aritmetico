import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.*;

public class RPN {
    
    private String espress;
    private String[] v_token;

    public RPN(){
        this.espress = null;
        this.v_token = new String[0];
    }
    
    public RPN(String s){
	this.espress = s;
	this.v_token =s.split(" ");
    }
	
    public int numeroToken(){
	
        int cont = this.v_token.length;
	return cont;
    }

    public String inverti(String s){

	String inversa = "";
	for (int i = s.length(); i!= 0; i--){
            inversa += s.charAt(i);
	}
        return inversa;
    }
 
    static boolean numero(String str) {
        try{
            Double.valueOf(str);
            return true;
        } catch(Exception e){
            return false;
        }
    }
 
    public Queue<String> RPN(String[] infixNotation) {
        Map<String, Integer> operatori = new HashMap<>();
        operatori.put("/", 2);
        operatori.put("*", 2);
        operatori.put("+", 1);
        operatori.put("-", 1);
        operatori.put("(", 0);
 
        Queue<String> Q = new LinkedList<>();
        Stack<String> S = new Stack<>();
 
        for (String token : infixNotation) {
            if ("(".equals(token)) {
                S.push(token);
                continue;
            }
 
            if (")".equals(token)) {
                while (!"(".equals(S.peek())) {
                    Q.add(S.pop());
                }
                S.pop();
                continue;
            }
            
            if (operatori.containsKey(token)) {
                while (S.empty()==false && operatori.get(token) <= operatori.get(S.peek())) {
                    Q.add(S.pop());
                }
                S.push(token);
                continue;
            }
 
            if (numero(token)) {
                Q.add(token);
                continue;
            }
 
        }
        while (S.isEmpty()==false) {
            Q.add(S.pop());
        }
 
        return Q;
    }
}
