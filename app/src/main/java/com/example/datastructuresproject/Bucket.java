package com.example.datastructuresproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Bucket extends AppCompatActivity {
    ArrayList<Float> arrayList = new ArrayList<>();
    ArrayAdapter<Float> adapter;

    TextView output,input;
    EditText number;
    Button add, sort, clear, refresh;
    float[] std_array;
    int i, s, array_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.bucket);

        number = findViewById(R.id.text_num);
        add = findViewById(R.id.btn_add);
        sort = findViewById(R.id.btn_sort);
        refresh = findViewById(R.id.btn_refresh);
        clear = findViewById(R.id.btn_clear);
        input = findViewById(R.id.array_input);
        output = findViewById(R.id.array_output);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(number!=null) {
                    arrayList.add(Float.parseFloat(number.getText().toString()));
                    input.setText(arrayList.toString());
                    // adapter.notifyDataSetChanged();
                    number.setText("");

                    Toast.makeText(getApplicationContext(), arrayList.toString(), Toast.LENGTH_LONG).show();
                    s = arrayList.size();

                    Log.d("eeeeeeeeeeeeeeeeeeeeeee", String.valueOf(s));
                }else{
                    Toast.makeText(getApplicationContext(), "Enter number before click Add button", Toast.LENGTH_LONG).show();
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(number!=null) {
                    arrayList.remove(Float.parseFloat(number.getText().toString()));
                    input.setText(arrayList.toString());
                    // adapter.notifyDataSetChanged();
                    number.setText("");

                    Toast.makeText(getApplicationContext(), arrayList.toString(), Toast.LENGTH_LONG).show();
                    s = arrayList.size();

                    Log.d("eeeeeeeeeeeeeeeeeeeeeee", String.valueOf(s));
                }else{
                    Toast.makeText(getApplicationContext(), "Enter number before click Remove button", Toast.LENGTH_LONG).show();
                }
            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                array_size = s;
                Toast.makeText(getApplicationContext(), "Input array confirmed. No more change", Toast.LENGTH_LONG).show();
                final float[] arr = new float[arrayList.size()];
                int index = 0;
                for (final Float value: arrayList) {
                    arr[index++] = value;
                }
                bucketSort(arr, arrayList.size());
                std_array = bucketSort(arr, arrayList.size());
                output.setText(Arrays.toString(std_array));

                //  Toast.makeText(getApplicationContext(),Arrays.toString(std_array), Toast.LENGTH_LONG).show();

            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.setText("");
                output.setText("");
                arrayList.clear();
                std_array = null;
            }
        });
    }

    public float[] bucketSort(float[] arr, int n) {

        @SuppressWarnings("unchecked")
        ArrayList<Float>[] bucket = new ArrayList[11];

        // Create empty buckets
        for (int i = 0; i < 11; i++)
            bucket[i] = new ArrayList<Float>();

        float[] sorted_array = new float[arr.length];

        // Add elements into the buckets
        for (int i = 0; i < n; i++) {
            int bucketIndex = (int) arr[i] / 100;
            bucket[bucketIndex].add(arr[i]);
        }

        // Sort the elements of each bucket
        for (int i = 0; i < 11; i++) {
            Collections.sort((bucket[i]));
        }

        // Get the sorted array
        int index = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0, size = bucket[i].size(); j < size; j++) {
                sorted_array[index++] = bucket[i].get(j);
            }
        }
        return sorted_array;
    }
}