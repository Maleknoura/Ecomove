package Service;

import DAO.StationDAO;
import Model.Station;

import java.sql.SQLException;
import java.util.List;

public class StationService {
    private final StationDAO stationDAO;

    public StationService(StationDAO stationDAO) {
        this.stationDAO = stationDAO;
    }

    public void insertStationsAndDistances(List<Station> stations) throws SQLException {
        for (Station station : stations) {
            stationDAO.insert(station);
        }
    }
}
