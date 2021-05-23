
public class OperAritm{
  
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
      
     //richiamare metodo remove 
      vett = this.remove(vett, ris);
    }
    int somma = vett[0].parseInt();
    return int somma;
  }
  
 /** viene dato in input un vettore, appena trova un operatore mette a null quella cella e quella prima (dove tecnicamente c'è un numero)
 mentre quella in posizione i-2 viene sostutuita con il valore ris in forma di Stringa 
 poi, dopo aver messo il risultato, le celle vengono spostate in modo che non ci siano celle=null 
 */
  
 public String remove(String vett, int ris){
   bolean exit=false; 
   int pos = 0;
   
   for(int i=0; i<vett.lenght || exit==false; i++){

     if (vett[i] == "+" || vett[i] == "-" || vett[i] == "*" || vett[i] == "/"){
       vett[i] = null;
       vett[i-1] = null;
       String num = String.valueOf(ris);
       vett[i-2] = num;
       exit = true;
       pos = i;
     }
   }
   
  for(int j=pos; j<vett.lenght - 1; j++){
    vett[j] = vett[j+1];
  } 
   
 return vett;
 }
}
