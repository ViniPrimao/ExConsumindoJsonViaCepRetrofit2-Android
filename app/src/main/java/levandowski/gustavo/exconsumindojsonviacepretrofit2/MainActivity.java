package levandowski.gustavo.exconsumindojsonviacepretrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author primao
 */
public class MainActivity extends AppCompatActivity {
    private EditText etCEP;
    private Button btnOK;
    private ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCEP=findViewById(R.id.ma_et_cep);
        btnOK=findViewById(R.id.ma_et_btn);
        progress=findViewById(R.id.ma_progress);

        Gson g = new GsonBuilder().registerTypeAdapter(CEP.class,new CEPDeserializer()).create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIRetrofitService.BASE_URL).addConverterFactory(GsonConverterFactory.create(g)).build();
        final APIRetrofitService service = retrofit.create(APIRetrofitService.class);
        progress.setVisibility(View.INVISIBLE);
        progress.getSecondaryProgressTintList();
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etCEP.getText().toString().isEmpty()){
                    progress.setVisibility(View.VISIBLE);
                    Call<CEP> callCEP = service.getEnderecoByCEP(etCEP.getText().toString());
                    callCEP.enqueue(new Callback<CEP>() {
                        @Override
                        public void onResponse(Call<CEP> call, Response<CEP> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(getBaseContext(), "Erro", Toast.LENGTH_LONG).show();
                            } else {
                                CEP cep = response.body();
                                Toast.makeText(getBaseContext(), "Cep Consumido"+cep.toString() ,Toast.LENGTH_LONG).show();
                            }
                            progress.setVisibility(View.INVISIBLE);
                        }
                        @Override
                        public void onFailure(Call<CEP> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
}
