package sv.edu.udb.guia6ejercicio2.interfaces;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicio {
    private static String IP="192.168.0.3"; //Agregar su dirección IP
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://"+IP+"/APIUDB/producto/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static APIService service =
            retrofit.create(APIService.class);
}

