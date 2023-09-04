package Class;

import java.math.BigDecimal;

public class Tarif {
    private Integer idClient;

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Integer getIdClientHeritage() {
        return idClientHeritage;
    }

    public void setIdClientHeritage(Integer idClientHeritage) {
        this.idClientHeritage = idClientHeritage;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public Integer getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(Integer codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    private BigDecimal montant;
    private Integer idClientHeritage;
    private String zone;
    private Integer codeDepartement;

}







