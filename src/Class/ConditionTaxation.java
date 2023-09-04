package Class;

import java.math.BigDecimal;

public class ConditionTaxation {

        private int idClient;
        private boolean useTaxePortDuGenerale;
        private BigDecimal taxePortDu;
        private boolean useTaxePortPayeGenerale;
        private BigDecimal taxePortPaye;

        // Ajoutez les constructeurs, getters et setters ici

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public boolean isUseTaxePortDuGenerale() {
        return useTaxePortDuGenerale;
    }

    public void setUseTaxePortDuGenerale(boolean useTaxePortDuGenerale) {
        this.useTaxePortDuGenerale = useTaxePortDuGenerale;
    }

    public BigDecimal getTaxePortDu() {
        return taxePortDu;
    }

    public void setTaxePortDu(BigDecimal taxePortDu) {
        this.taxePortDu = taxePortDu;
    }

    public boolean isUseTaxePortPayeGenerale() {
        return useTaxePortPayeGenerale;
    }

    public void setUseTaxePortPayeGenerale(boolean useTaxePortPayeGenerale) {
        this.useTaxePortPayeGenerale = useTaxePortPayeGenerale;
    }

    public BigDecimal getTaxePortPaye() {
        return taxePortPaye;
    }

    public void setTaxePortPaye(BigDecimal taxePortPaye) {
        this.taxePortPaye = taxePortPaye;
    }
}




