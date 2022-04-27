package sv.edu.udb.guia6ejercicio2.interfaces;


import retrofit2.http.Query;
import sv.edu.udb.guia6ejercicio2.models.Producto;
import sv.edu.udb.guia6ejercicio2.models.RespProducto;
import sv.edu.udb.guia6ejercicio2.models.Respuesta;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface APIService {
    @GET(".")
    Call<List<Producto>> getProducts();
    @GET("index.php")
    Call<RespProducto> getProductById(@Query("codigo") String codigo);
    @POST("agregar/index.php")
    Call<Respuesta> insertProduct(@Body Producto producto);
    @DELETE("eliminar/index.php")
    Call<Respuesta> deleteProduct(@Query("codigo") String codigo);
    @PUT("actualizar/index.php")
    Call<Respuesta> updateProduct(@Query("codigo") String codigo, @Body Producto producto);
}
