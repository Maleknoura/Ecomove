import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DbFunction db = new DbFunction();
        Connection conn =db.connectToDb("Ecomove","postgres","administrateur");
        db.createTablePartner(conn,"PromotionalOffer");
        db.createTablePartner(conn,"Ticket");
    }

}