
public class OperAritm{
  int somma;
  String operatori = "+-*/";
  
  public int somma(String [] vett){
    String[] v_temp = new String(vett);
    
    for (int i=0; i<vett.lenght; i++){
      int ris = 0;
      switch(vett[i]){
        case "+" : 
          ris = vett[i-2].parseInt() + vett[i-1].parseInt();
          break;
        case "-" : 
          ris = vett[i-2].parseInt() - vett[i-1].parseInt();
          break;
        case "*" : 
          ris = vett[i-2].parseInt() * vett[i-1].parseInt();
          break;
        case "/" : 
          ris = vett[i-2].parseInt() / vett[i-1].parseInt();
          break;
      }
      
     
    }
    
    return somma;
  }
  
 // viene dato in input un vettore, appena trova un operatore mette a null quella cella e quella prima (dove tecnicamente c'è un numero)
 // mentre quella in posizione i-2 viene sostutuita con il valore ris in forma di Stringa 

 public String remove(String vett, int ris){
   for(int i=0; i<vett.lenght; i++){
     if (vett[i] == "+" || vett[i] == "-" || vett[i] == "*" || vett[i] == "/"){
       vett[i] = null;
       vett[i-1] = null;
       String num = String.valueOf(ris);
       vett[i-2] = num;
     }
     
   }
 }
}
