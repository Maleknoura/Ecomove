package Service;

import Model.Station;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DistanceMatrixAPI {
    private static final String API_KEY = "AIzaSyDFMqbXKH38KdNAI17uhWW5G4lKmlbGQO8";
    private final Connection connection;
    private final StationService stationService;

    public DistanceMatrixAPI(Connection connection, StationService stationService) {
        this.connection = connection;
        this.stationService = stationService;
    }

    public void fetchAndInsertDistances() throws Exception {
        String[] cities = {"Marrakech", "Casablanca", "Rabat", "Tanger", "Fès", "Agadir", "Meknès", "Oujda", "Safi", "El Jadida",
                "Nador", "Kénitra", "Tétouan", "Beni Mellal", "Khouribga", "Laâyoune", "Mohammedia", "Khemisset", "Settat", "Salé",
                "Taza", "Tan-Tan", "Ifrane", "Essaouira", "Guelmim"};

        String origins = String.join("|", cities);
        String destinations = String.join("|", cities);

        String urlStr = String.format(
                "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&key=%s",
                origins, destinations, API_KEY);

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("HTTP error code : " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JsonObject jsonResponse = new Gson().fromJson(response.toString(), JsonObject.class);
        JsonArray rows = jsonResponse.has("rows") ? jsonResponse.getAsJsonArray("rows") : new JsonArray();

        List<Station> stations = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            JsonObject row = rows.get(i).getAsJsonObject();
            JsonArray elements = row.has("elements") ? row.getAsJsonArray("elements") : new JsonArray();

            for (int j = 0; j < elements.size(); j++) {
                JsonObject element = elements.get(j).getAsJsonObject();
                JsonObject distanceObj = element.has("distance") ? element.getAsJsonObject("distance") : new JsonObject();
                String distanceText = distanceObj.has("text") ? distanceObj.get("text").getAsString() : "0 km";
                double distance = parseDistance(distanceText);

                String departure = cities[i];
                String arrival = cities[j];

                stations.add(new Station(UUID.randomUUID(), departure, arrival, distance));
            }
        }

        stationService.insertStationsAndDistances(stations);
    }

    private double parseDistance(String distanceText) {
        try {
            String distanceValue = distanceText.split(" ")[0].replaceAll(",", ".");
            return Double.parseDouble(distanceValue);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing distance: " + distanceText);
            return 0.0;
        }
    }
}
