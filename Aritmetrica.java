import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;

//Gruppo Aritmetico
//by Maximo Adrighetti, Elisa Veronesi, Zattoni Mattia, Riccardo Scardino.

    public class Aritmetrica {
        private String espres;
        private String[] infissa;
        private ArrayList<String> RPN;
        private ArrayList <String> risultato;

        public Aritmetrica(){
            this.espres = " ";
            this.infissa = new String[0];
            this.RPN = new ArrayList<String>();
            this.risultato= new ArrayList<>();
        }

        public Aritmetrica(String s){
            this.set(s);
        }

        public Aritmetrica(Aritmetrica k){
            this.espres = k.getEspres();
            this.infissa = k.getInfissa();
            this.RPN = k.getRPN();
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

            this.infissa =ris.split(" ");
            // this.infissa =this.aggiustaToken(infissa);
            /*
            for(int i = 0; i< infissa.length; i++){
                System.out.println(infissa[i]);
            }
            */
            

            if(this.dueOpe(this.espres) && this.parentesi(this.espres)){

                this.RPN= this.postFissa();
                /*for(int i=0;i<this.RPN.size();i++){
                    System.out.println(this.RPN.get(i));
                }
                */
                
                this.risultato= this.somma(this.RPN);

            }
        }

        public void setSpace(String espres){
            this.espres=espres;
            this.infissa =espres.split(" ");
            //this.infissa =this.aggiustaToken(infissa);

            if(this.dueOpe(this.espres) && this.parentesi(this.espres)){
                

                this.RPN= this.postFissa();
                this.risultato= this.somma(this.RPN);

            }

        }

        public void setAritmetrica(String espres, String[] v_token){
            this.espres = espres;
            this.infissa = new String[v_token.length];
            for (int i=0; i<v_token.length; i++){
                this.infissa[i]= v_token[i];
            }
        }

        public void setEspres(String espres){
            this.espres = espres;
        }

        public String getEspres(){
            return this.espres;
        }

        public void setInfissa(String[] infissa){
            String [] temp= new String[infissa.length];
            for(int i = 0; i< infissa.length; i++){
                temp[i]= infissa[i];
            }
            this.infissa =temp;
        }

        public String[] getInfissa(){
            return this.infissa;
        }

        public void setRPN(ArrayList<String> RPN){
            this.RPN= new ArrayList<String>();
            for(int i=0;i<RPN.size();i++){
                this.RPN.add(RPN.get(i));
            }
        }

        public ArrayList <String> getRPN(){
            return this.RPN;
        }

        public void setRisultato(ArrayList<String> risultato){
            this.risultato=risultato;
        }

        public ArrayList<String> getRisultato(){
            return this.risultato;
        }

        public String toString(){
            String t="L' espressione e': "+ this.espres+"\n";
            t+= "In token: ";
            for(int i = 0; i<this.infissa.length; i++){

                t+= this.infissa[i]+" ";
            }
            t+="\n";
            if(this.dueOpe(this.espres) && this.parentesi(this.espres)){

                t+="Risulta uguale a: "+this.risultato.get(0)+"\n";
                t+="L'espressione risulta al contrario: "+this.inverti()+"\n";
                t+="L'espressione e' formata da :"+this.numeroToken()+" token \n";
                t+= "In notazione Postfissa(RPN) : ";
                for(int i=0;i<this.RPN.size();i++){
                    if(this.RPN.get(i)!=null){
                        t+= this.RPN.get(i) +" ";
                    }
                }
                t+="\n";
                t+="L'espressione non riporta alcun errore"+"\n";

            }else{
                t+="Ha riportato alcuni errori \n";
                if(this.dueOpe(this.espres)==false){
                    t+="Operatori multipli ";
                }
                if(this.parentesi(this.espres)==false){
                    t+="Parentesi non chiuse  ";
                }
                t+="\n";
            }
            return t;
        }

        public boolean equals(Aritmetrica tt){
            return (this.toString().equals(tt.toString()));
        }

        public int numeroToken(){
            int cont = this.infissa.length;
            return cont;
        }

        public String inverti(){
            String inversa = "";
            for (int i = this.infissa.length-1; i> -1; i--){
                inversa+=this.infissa[i];
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

        public ArrayList <String> postFissa() {

            Map <String , Integer> operatori = new HashMap<>();

            operatori.put("/", 2);
            operatori.put("*", 2);
            operatori.put("+", 1);
            operatori.put("-", 1);
            operatori.put("(", 0);

            ArrayList <String> Q = new ArrayList<String>();

            Stack<String> S = new Stack<>();

            int pos=0;

            for (String token : infissa) {
                if ("(".equals(token)) {
                    S.push(token);
                    continue;
                }

                if (")".equals(token)) {
                    while (!"(".equals(S.peek())) {
                        Q.add(S.pop());
                        pos+=1;
                    }
                    S.pop();
                    continue;
                }

                if (operatori.containsKey(token)) {
                    while (S.empty()==false && operatori.get(token) <= operatori.get(S.peek())) {
                        Q.add(S.pop());
                        pos+=1;
                    }
                    S.push(token);
                    continue;
                }

                if (this.numero(token)) {
                    Q.add(token);
                    pos+=1;
                    continue;
                }

            }
            while (S.isEmpty()==false) {
                Q.add(S.pop());
                pos+=1;
            }

            return Q;

        }

        public ArrayList <String> somma(ArrayList <String> temp){
            
            ArrayList<String> Q = new ArrayList<String>(temp);
            int ris = 0;
            int i=2;

            while(Q.size()!=1){
                ris=0;
                
                switch(Q.get(i)){

                    case "+" :
                        ris = Integer.parseInt(Q.get(i-2) ) + ( Integer.parseInt(Q.get(i-1)));
                        Q.set(0,String.valueOf(ris));
                        break;

                    case "-" :
                        ris = Integer.parseInt(Q.get(i-2) ) - (Integer.parseInt(Q.get(i-1)));
                        Q.set(0,String.valueOf(ris));
                        break;

                    case "*" :
                        ris = Integer.parseInt(Q.get(i-2) ) * (Integer.parseInt(Q.get(i-1)));
                        Q.set(0,String.valueOf(ris));
                        
                        break;

                    case "/" :
                        if (Integer.parseInt(Q.get(i-1))== 0) {
                            ris = 0;
                            Q.set(0,"Error");
                        }else{
                            ris = Integer.parseInt(Q.get(i-2) )/ (Integer.parseInt(Q.get(i-1)));
                            Q.set(0,String.valueOf(ris));
                        }
                        break;

                }
                Q.remove(1);
                Q.remove(1);

            }
            //System.out.println(Q.get(0));
            return Q;
        }

        public boolean segno(char segno){
            boolean test = false;
            if (segno==('+') || segno==('-') || segno==('*') || segno==('/')){
                test = true;
            }
            return test;
        }

        public boolean dueOpe(String vett) {
            boolean err=false;
            for (int i= 1;i<vett.length(); i++){
                if (this.segno(vett.charAt(i)) && (this.segno(vett.charAt(i-1)))){
                    err = true;
                }
            }
            return !err;
        }

        public boolean parentesi(String vett){
            int parentesi_aperte = 0;
            int parentesi_chiuse = 0 ;
            boolean err= true;

            for (int i= 0;i<vett.length(); i++){
                    if (vett.charAt(i)==('(')){
                        parentesi_aperte++;
                    }
                    if(vett.charAt(i)==(')')) {
                        parentesi_chiuse++;
                    }
            }
            if (parentesi_aperte != parentesi_chiuse){
                err = false;

            }

            return err;
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
            Aritmetrica test_6 = new Aritmetrica("20/0");
            System.out.println(test_6);
            System.out.println("Fine");
        }
    }

