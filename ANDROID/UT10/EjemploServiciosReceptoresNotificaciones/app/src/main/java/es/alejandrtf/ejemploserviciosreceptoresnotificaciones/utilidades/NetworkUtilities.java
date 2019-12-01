package es.alejandrtf.ejemploserviciosreceptoresnotificaciones.utilidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Sandra on 21/02/2017.
 */

public final class NetworkUtilities {


    /**
     * This method returns the entire result from the HTTP response en forma de array de Strings (cada linea del fichero).
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String[] getResponseFromHttpUrl(URL url) throws IOException {
        BufferedReader br;
        ArrayList<String> lineasList = new ArrayList<>();
        String linea;

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


        try {
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));


            while ((linea = br.readLine()) != null) {
                lineasList.add(linea);
            }
            br.close();
            if (lineasList.size() > 0)
                return (lineasList.toArray(new String[lineasList.size()]));
            else
                return null;
        } finally {
            urlConnection.disconnect();
        }
    }
}

