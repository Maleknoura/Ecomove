import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
public class PromotionalOffer {

        private UUID id;
        private String nomOffre;
        private String description;
        private LocalDate dateDebut;
        private LocalDate dateFin;
        private ReductionType typeReduction;
        private BigDecimal valeurReduction;
        private String conditions;
        private OfferStatus statutOffre;


        public PromotionalOffer(UUID id, String nomOffre, String description,
                                LocalDate dateDebut, LocalDate dateFin,
                                ReductionType typeReduction, BigDecimal valeurReduction,
                                String conditions, OfferStatus statutOffre) {
            this.id = id;
            this.nomOffre = nomOffre;
            this.description = description;
            this.dateDebut = dateDebut;
            this.dateFin = dateFin;
            this.typeReduction = typeReduction;
            this.valeurReduction = valeurReduction;
            this.conditions = conditions;
            this.statutOffre = statutOffre;
        }

        // Getters et setters
        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getNomOffre() {
            return nomOffre;
        }

        public void setNomOffre(String nomOffre) {
            this.nomOffre = nomOffre;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public ReductionType getTypeReduction() {
            return typeReduction;
        }

        public void setTypeReduction(ReductionType typeReduction) {
            this.typeReduction = typeReduction;
        }

        public BigDecimal getValeurReduction() {
            return valeurReduction;
        }

        public void setValeurReduction(BigDecimal valeurReduction) {
            this.valeurReduction = valeurReduction;
        }

        public String getConditions() {
            return conditions;
        }

        public void setConditions(String conditions) {
            this.conditions = conditions;
        }

        public OfferStatus getStatutOffre() {
            return statutOffre;
        }

        public void setStatutOffre(OfferStatus statutOffre) {
            this.statutOffre = statutOffre;
        }


    }


