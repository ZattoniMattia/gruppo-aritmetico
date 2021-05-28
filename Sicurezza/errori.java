package info;

public class errori {

    private boolean err;
    private String token;
    public errori() {
        this.err = false;
    }
    public String [] Spazi((String [] vett) {


        for (int i = 1; i < vett.length; i++) {

        }
        return
    }

    public boolean DUeOpe(String [] vett, String operatori) {
        while (this.err == false){
            for (int i= 1;i<vett.length; i++){
                if (vett[i].contains(operatori)){
                    err = true;
                }
            }
        }
        return this.err;
    }
    public boolean Parentesi(String [] vett){
        int parentesi_aperte =  0;
        int parentesi_chiuse = 0 ;

        for (int i= 1;i<vett.length; i++){
                if (vett[i] == "("){
                    parentesi_aperte ++;
                }else if(vett[i]== ")") {
                    parentesi_chiuse++;
                }
            }
                if (parentesi_aperte != parentesi_chiuse){
                    this.err = true;
                }

        return this.err;
    }