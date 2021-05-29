//Gruppo Aritmetico
//by Maximo Adrighetti, Elisa Veronesi, Zattoni Mattia, Riccardo Scardino.

import java.util.*;


public class Aritmetrica {
        private String espres;
	private String[] v_token;
        private String[] RPN;
        private int risultato;

	public Aritmetrica(){
		this.espres = " ";
		this.v_token = new String[0];
                this.RPN = new String[0];
                this.risultato=0;
	}
        
	public Aritmetrica(String s){
		this.set(s);
	}
	
	public Aritmetrica(Aritmetrica k){
		this.espres = k.getEspres();
		this.v_token = k.getV_token();
                this.RPN= k.getRPN();
                this.risultato= k.getRisultato();
	}
        
        public void set(String espres){
            this.espres=espres;
            char[] vet = espres.trim().strip().toCharArray();
            StringBuilder fine = new StringBuilder();
            
            for (int i=0; i<vet.length; i++) {
                if(vet[i] < '0' || vet[i] >'9' && vet[i]!=' '){
                    if(i == 0){
                        fine.append(vet[i]);
                        fine.append(' ');
                    }
                    else if(i == vet.length){
                        fine.append(' ');
                        fine.append(vet[i]);
                    }
                    else{
                        fine.append(' ');
                        fine.append(vet[i]);
                        fine.append(' ');
                    }

                }
                else {
                    fine.append(vet[i]);
                }
            }  
            String ris =fine.toString();
            this.v_token=ris.split(" ");
            this.v_token=this.aggiustaToken(v_token);
            
            for(int i=0;i<v_token.length;i++){
                System.out.println(v_token[i]);
            }
            
            if(this.dueOpe(this.v_token) && this.parentesi(this.v_token)){
                
                this.RPN= this.RPN(this.v_token);
                this.risultato= this.somma(this.RPN);
                
            }
        }
        
        public void setSpace(String espres){
            this.espres=espres;
            this.v_token=espres.split(" ");
            this.v_token=this.aggiustaToken(v_token);
            
            if(this.dueOpe(this.v_token) && this.parentesi(this.v_token)){
                
                this.RPN= this.RPN(this.v_token);
                this.risultato= this.somma(this.RPN);
                
            }
            
        }
        
	public void setAritmetrica(String espres, String[] v_token){
		this.espres = espres;
		this.v_token = new String[v_token.length];
		for (int i=0; i<v_token.length; i++){
                    this.v_token[i]= v_token[i];
		}
	}
        
	public void setEspres(String espres){
		this.espres = espres;
	}
        
	public String getEspres(){	
    		return this.espres;
	}
        
	public void setV_token(String[] v_token){
            String [] temp= new String[v_token.length];
            for(int i=0;i<v_token.length;i++){
                temp[i]= v_token[i];
            }
            this.v_token=temp;
	}
        
	public String[] getV_token(){
    		return this.v_token;
	}
        
        public void setRPN(String[] RPN){
            this.RPN= new String[RPN.length];
            for(int i=0;i<RPN.length;i++){
                this.RPN[i]=RPN[i];
            }
        }
        
        public String[] getRPN(){
            return this.RPN;
        }
        
        public void setRisultato(int risultato){
           this.risultato=risultato;
        }
        
        public int getRisultato(){
            return this.risultato;
        }
        
	public String toString(){
            String t="L' espressione e': "+ this.espres+"\n";
            t+= "In token: ";
            for(int i=0;i<this.v_token.length;i++){
                
                t+= this.v_token[i]+" ";
            }
            t+="\n";
            if(this.dueOpe(this.v_token) && this.parentesi(this.v_token)){
                
                t+="Risulta uguale a: "+this.risultato+"\n";
                t+="L'espressione risulta al contrario: "+this.inverti()+"\n";
                t+="L'espressione e' formata da :"+this.numeroToken()+" token \n";
                t+= "In notazione Postfissa(RPN) : ";
                for(int i=0;i<this.RPN.length;i++){
                    if(this.RPN[i]!=null){
                        t+= this.RPN[i]+" ";
                    }
                }
                t+="\n";
                t+="L'espressione non riporta alcun errore"+"\n";
                
            }else{
                t+="Ha riportato alcuni errori \n";
                if(this.dueOpe(this.v_token)==false){
                    t+="Operatori multipli ";
                }
                if(this.parentesi(this.v_token)==false){
                    t+="Parentesi non chiuse  ";
                }
                t+="\n";
                t+="L'espressione risulta al contrario: "+this.inverti()+"\n";
                t+="L'espressione e' formata da :"+this.numeroToken()+" token \n";
            }
            return t;
	}
        
	public boolean equals(Aritmetrica tt){
    		return (this.toString().equals(tt.toString()));
  	}

	public int numeroToken(){
		int cont = this.v_token.length;
		return cont;
	}
        
	public String inverti(){
            String inversa = "";
            for (int i = this.v_token.length-1; i> -1; i--){
                inversa+=this.v_token[i];
            }
            return inversa;
	}
        
        public boolean numero(String str) {
            try{
                Double.valueOf(str);
                return true;
            } catch(Exception e){
                return false;
            }
        }
 
        public String[] RPN(String[] infixNotation) {
            Map<String, Integer> operatori = new HashMap<>();
            operatori.put("/", 2);
            operatori.put("*", 2);
            operatori.put("+", 1);
            operatori.put("-", 1);
            operatori.put("(", 0);
 
            String[] Q = new String[infixNotation.length];
            Stack<String> S = new Stack<>();
            int pos=0;
 
            for (String token : infixNotation) {
                if ("(".equals(token)) {
                    S.push(token);
                    continue;
                }
 
                if (")".equals(token)) {
                    while (!"(".equals(S.peek())) {
                        Q[pos]=(S.pop());
                        pos+=1;
                    }
                    S.pop();
                    continue;
                }
            
                if (operatori.containsKey(token)) {
                    while (S.empty()==false && operatori.get(token) <= operatori.get(S.peek())) {
                        Q[pos]=(S.pop());
                        pos+=1;
                    }
                    S.push(token);
                    continue;
                }
 
                if (this.numero(token)) {
                    Q[pos]=(token);
                    pos+=1;
                    continue;
                }
 
            }
            while (S.isEmpty()==false) {
                Q[pos]=(S.pop());
                pos+=1;
            }
 
            return Q;
        }
        
        public int somma(String [] vett){
            String[] v_temp = new String[vett.length];
            for(int i=0;i<vett.length;i++){
                v_temp[i]=vett[i];
            }
            
            int ris = 0;
            
            for (int i=0; i<v_temp.length && v_temp[i]!=null; i++){
                ris=0;
                switch(v_temp[i]){
                    
                    case "+" : 
                        ris = Integer.parseInt(v_temp[i-2]) + Integer.parseInt(v_temp[i-1]);
                        break;
                        
                    case "-" : 
                        ris = Integer.parseInt(v_temp[i-2]) - Integer.parseInt(v_temp[i-1]);
                        break;
                        
                    case "*" : 
                        ris = Integer.parseInt(v_temp[i-2]) * Integer.parseInt(v_temp[i-1]);
                        break;
                    
                    case "/" : 
                        if (Integer.parseInt(v_temp[i-1])==0){
                            ris= 0;
                        }else{
                            ris = Integer.parseInt(v_temp[i-2]) / Integer.parseInt(v_temp[i-1]);
                        }
                        break;
                        
                }
                v_temp = this.remove(v_temp, ris);
            }
            
            int somma = Integer.parseInt(v_temp[0]);
            return somma;
        }
        
        public String[] remove(String[] v_temp, int ris){
            boolean exit=false;
            int pos = 0;
            String[] vett= new String[v_temp.length];
            
            for(int i=0;i< v_temp.length;i++){
                vett[i]=v_temp[i];
            }
            
            if(ris!=0){
   
            for(int i=0; i<vett.length && exit==false; i++){
                if(vett[i]!=null){
                    
                    if (this.segno(vett[i])){
                        vett[i] = null;
                        vett[i-1] = null;
                        String num = String.valueOf(ris);
                        vett[i-2] =num;
                        exit = true;
                        pos = i;
                    }
                }
            }
   
            for(int j=pos-1; j<vett.length-1 && pos!=0 && vett[j]==null; j++){
                vett[j] = vett[j+1];
            }
            }
            
            return vett;
        }
        
        public boolean segno(String segno){
            boolean test = false;
            if (segno.equals("+") || segno.equals("-") || segno.equals("*") || segno.equals("/")){
                test = true;
            }
            return test;
        }

        public boolean dueOpe(String [] vett) {
            boolean err=true;
            for (int i= 0;i<vett.length-1; i++){
                if(vett[i]!=null){
                    if (this.segno(vett[i]) && this.segno(vett[i+1])){
                        err = false;
                    }
                }
            }
            return err;
        }
        
        public boolean parentesi(String [] vett){
            int parentesi_aperte = 0;
            int parentesi_chiuse = 0 ;
            boolean err= true;

            for (int i= 0;i<vett.length; i++){
                if(vett[i]!=null){
                    if (vett[i].equals("(")){
                        parentesi_aperte++;
                    }
                    if(vett[i].equals(")")) {
                        parentesi_chiuse++;
                    }
                }
            }
            if (parentesi_aperte == parentesi_chiuse){
                err = true;
                
            }else{
                err=false;
            }

            return err;
        }

        public String[] aggiustaToken(String[]token){
            int num=0;
            
            for(int i=0;i<token.length;i++){
                if(token[i]!=null){
                    num+=1;
                }
            }
            
            String []vett = new String[num];
            int pos=0;
            for(int i=0;i<num;i++){
                if(token[i]!=null && !token[i].equals("")){
                    vett[pos]=token[i];
                    pos+=1;
                }
            }
            
            num=0;
            
            for(int i=0;i<vett.length;i++){
                if(vett[i]!=null){
                    num+=1;
                }
            }
            
            String []vett_temp = new String[num];
            pos=0;
            for(int i=0;i<num;i++){
                if(vett[i]!=null && !vett[i].equals("")){
                    vett_temp[pos]=vett[i];
                    pos+=1;
                }
            }
            
            return vett_temp;
        }
        
        public static void main(String[]args){
            Aritmetrica test = new Aritmetrica("50+20");
            System.out.println(test);
            Aritmetrica test_2 = new Aritmetrica("50-20");
            System.out.println(test_2);
            Aritmetrica test_3 = new Aritmetrica("50+-20");
            System.out.println(test_3);
            Aritmetrica test_4 = new Aritmetrica("((50-20)");
            System.out.println(test_4);
            Aritmetrica test_5 = new Aritmetrica("50*20+30");
            System.out.println(test_5);
        }
        
}