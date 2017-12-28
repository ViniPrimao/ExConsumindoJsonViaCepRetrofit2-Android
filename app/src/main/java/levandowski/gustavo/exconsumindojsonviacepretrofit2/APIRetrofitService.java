package levandowski.gustavo.exconsumindojsonviacepretrofit2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author primao
 */

public interface APIRetrofitService {
     String BASE_URL = "https://viacep.com.br/ws/";
     @GET("{CEP}/json/")
     Call<CEP> getEnderecoByCEP(@Path("CEP") String CEP);

    @GET("{estado}/{cidade}/{rua}/json/")
    Call<List<CEP>> getCEPByCidadeEstadoEndereco(@Path("estado") String estado,@Path("cidade") String cidade,@Path("rua") String rua);
}
