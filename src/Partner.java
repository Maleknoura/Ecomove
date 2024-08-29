import java.util.Date;
import java.util.UUID;
public class Partner {

        private UUID id;
        private String nomCompagnie;
        private String contactCommercial;
        private TransportType typeTransport;
        private String zoneGeographique;
        private String conditionsSpeciales;
        private PartnerStatus statutPartenaire;
        private Date dateCreation;


        public Partner(UUID id, String nomCompagnie, String contactCommercial,
                       TransportType typeTransport, String zoneGeographique,
                       String conditionsSpeciales, PartnerStatus statutPartenaire,
                       Date dateCreation) {
            this.id = id;
            this.nomCompagnie = nomCompagnie;
            this.contactCommercial = contactCommercial;
            this.typeTransport = typeTransport;
            this.zoneGeographique = zoneGeographique;
            this.conditionsSpeciales = conditionsSpeciales;
            this.statutPartenaire = statutPartenaire;
            this.dateCreation = dateCreation;
        }


        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getNomCompagnie() {
            return nomCompagnie;
        }

        public void setNomCompagnie(String nomCompagnie) {
            this.nomCompagnie = nomCompagnie;
        }

        public String getContactCommercial() {
            return contactCommercial;
        }

        public void setContactCommercial(String contactCommercial) {
            this.contactCommercial = contactCommercial;
        }

        public TransportType getTypeTransport() {
            return typeTransport;
        }

        public void setTypeTransport(TransportType typeTransport) {
            this.typeTransport = typeTransport;
        }

        public String getZoneGeographique() {
            return zoneGeographique;
        }

        public void setZoneGeographique(String zoneGeographique) {
            this.zoneGeographique = zoneGeographique;
        }

        public String getConditionsSpeciales() {
            return conditionsSpeciales;
        }

        public void setConditionsSpeciales(String conditionsSpeciales) {
            this.conditionsSpeciales = conditionsSpeciales;
        }

        public PartnerStatus getStatutPartenaire() {
            return statutPartenaire;
        }

        public void setStatutPartenaire(PartnerStatus statutPartenaire) {
            this.statutPartenaire = statutPartenaire;
        }

        public Date getDateCreation() {
            return dateCreation;
        }

        public void setDateCreation(Date dateCreation) {
            this.dateCreation = dateCreation;
        }


    }


