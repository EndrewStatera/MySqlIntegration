public class LocalidadeFaixa implements Comparable<LocalidadeFaixa>{

    /*
    loc_nu INT8 NOT NULL,
    loc_cep_ini VARCHAR(8) NOT NULL,
    loc_cep_fim VARCHAR(8) NULL,
    loc_tipo_cep VARCHAR(1) NOT NULL)
    */
    private int locNu; 
    private int locCepIni;
    private Integer locCepFim; //Tipado como Integer, pois, pode ser null
    private String locTipoCep;
    public LocalidadeFaixa(){

    }
    public int getLocNu() {
        return locNu;
    }
    public void setLocNu(int locNu) {
        this.locNu = locNu;
    }
    public int getLocCepIni() {
        return locCepIni;
    }
    public void setLocCepIni(int locCepIni) {
        this.locCepIni = locCepIni;
    }
    public int getLocCepFim() {
        return locCepFim;
    }
    public void setLocCepFim(int locCepFim) {
        this.locCepFim = locCepFim;
    }
    public String getLocTipoCep() {
        return locTipoCep;
    }
    public void setLocTipoCep(String locTipoCep) {
        this.locTipoCep = locTipoCep;
    }

    @Override
    public int compareTo(LocalidadeFaixa o) {
        return this.locCepIni - o.locCepIni;
    }

    @Override
    public String toString() {
        return "cidade: " + locNu + ", cepIni: " + locCepIni + ", cepFim: " + locCepFim + ", locTipoCep: " + locTipoCep;
    }
}