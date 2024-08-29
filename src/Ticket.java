import java.time.LocalDateTime;
import java.util.UUID;
import java.math.BigDecimal;


public class Ticket {

        private UUID id;
        private TransportType typeTransport;
        private BigDecimal prixAchat;
        private BigDecimal prixVente;
        private LocalDateTime dateVente;
        private TicketStatus statutBillet;


        public Ticket(UUID id, TransportType typeTransport, BigDecimal prixAchat,
                      BigDecimal prixVente, LocalDateTime dateVente, TicketStatus statutBillet) {
            this.id = id;
            this.typeTransport = typeTransport;
            this.prixAchat = prixAchat;
            this.prixVente = prixVente;
            this.dateVente = dateVente;
            this.statutBillet = statutBillet;
        }


        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public TransportType getTypeTransport() {
            return typeTransport;
        }

        public void setTypeTransport(TransportType typeTransport) {
            this.typeTransport = typeTransport;
        }

        public BigDecimal getPrixAchat() {
            return prixAchat;
        }

        public void setPrixAchat(BigDecimal prixAchat) {
            this.prixAchat = prixAchat;
        }

        public BigDecimal getPrixVente() {
            return prixVente;
        }

        public void setPrixVente(BigDecimal prixVente) {
            this.prixVente = prixVente;
        }

        public LocalDateTime getDateVente() {
            return dateVente;
        }

        public void setDateVente(LocalDateTime dateVente) {
            this.dateVente = dateVente;
        }

        public TicketStatus getStatutBillet() {
            return statutBillet;
        }

        public void setStatutBillet(TicketStatus statutBillet) {
            this.statutBillet = statutBillet;
        }

    }


