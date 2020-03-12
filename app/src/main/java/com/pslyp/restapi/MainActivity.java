package com.pslyp.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pslyp.restapi.api.retrofitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button sendButton;
    private EditText pathText;
    private TextView mText;
    private Spinner requestMethodSpin, httpSpin;

    private String BASE_URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();
    }

    private void initInstance() {
        mText = findViewById(R.id.url_text_view);
        pathText = findViewById(R.id.path_edit_text);
        sendButton = findViewById(R.id.send_button);
        sendButton.setOnClickListener(sendRequest);
        requestMethodSpin = findViewById(R.id.request_method_spinner);
//        requestMethodSpin.setOnItemSelectedListener(methodItems);
        httpSpin = findViewById(R.id.http_spinner);
//        httpSpin.setOnItemSelectedListener(httpItems);

        ArrayAdapter<CharSequence> requestAdapter = ArrayAdapter.createFromResource(this, R.array.method_array, android.R.layout.simple_spinner_item);
        requestAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        requestMethodSpin.setAdapter(requestAdapter);

        ArrayAdapter<CharSequence> httpAdapter = ArrayAdapter.createFromResource(this, R.array.http_array, android.R.layout.simple_spinner_item);
        httpAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        httpSpin.setAdapter(httpAdapter);
    }

//    private AdapterView.OnItemSelectedListener methodItems = new AdapterView.OnItemSelectedListener() {
//        @Override
//        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> adapterView) {
//
//        }
//    };
//
//    private AdapterView.OnItemSelectedListener httpItems = new AdapterView.OnItemSelectedListener() {
//        @Override
//        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> adapterView) {
//
//        }
//    };

    private View.OnClickListener sendRequest = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String http = httpSpin.getSelectedItem().toString().trim();
            String path = pathText.getText().toString().trim();
            String url = http + path;

            mText.setText(url);

            Call<ResponseBody> call = retrofitClient.getInstance().api().get(url);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    int status = response.code();

                    if(status == 200) {
                        Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }
    };
}
