import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Contract {

    private UUID id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private BigDecimal tarifSpecial;
    private String conditionsAccord;
    private boolean renouvelable;
    private ContractStatus statutContrat;


    public Contract(UUID id, LocalDate dateDebut, LocalDate dateFin,
                    BigDecimal tarifSpecial, String conditionsAccord,
                    boolean renouvelable, ContractStatus statutContrat) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.tarifSpecial = tarifSpecial;
        this.conditionsAccord = conditionsAccord;
        this.renouvelable = renouvelable;
        this.statutContrat = statutContrat;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public BigDecimal getTarifSpecial() {
        return tarifSpecial;
    }

    public void setTarifSpecial(BigDecimal tarifSpecial) {
        this.tarifSpecial = tarifSpecial;
    }

    public String getConditionsAccord() {
        return conditionsAccord;
    }

    public void setConditionsAccord(String conditionsAccord) {
        this.conditionsAccord = conditionsAccord;
    }

    public boolean isRenouvelable() {
        return renouvelable;
    }

    public void setRenouvelable(boolean renouvelable) {
        this.renouvelable = renouvelable;
    }

    public ContractStatus getStatutContrat() {
        return statutContrat;
    }

    public void setStatutContrat(ContractStatus statutContrat) {
        this.statutContrat = statutContrat;
    }


}

