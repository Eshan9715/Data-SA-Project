package com.example.datastructuresproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class NumberWise extends Fragment {
    ArrayList<Float> arrayList3 = new ArrayList<>();
    TextView root,node_1,node_2,node_3,input_array;
    EditText number_array;
    Button add3, refresh3;
    float[] root_arr,node_1_arr,node_2_arr,node_3_arr,z,z1,z2;
    int i,m, array_size,s;
    float a,b,c,d,e,f, searchNum,num;
    boolean search = false;
    int increment = 0;

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_number_wise, container, false);

        number_array = v.findViewById(R.id.txt_btree_num);
        add3 = v.findViewById(R.id.btn_number_add);
        refresh3 = v.findViewById(R.id.btn_number_refresh);
        input_array = v.findViewById(R.id.array_number_input);

        root = v.findViewById(R.id.root1);
        node_1 = v.findViewById(R.id.node_11);
        node_2 = v.findViewById(R.id.node_22);
        node_3 = v.findViewById(R.id.node_33);

        root_arr = new float[2];
        node_1_arr = new float[2];
        node_2_arr = new float[2];
        node_3_arr = new float[2];

        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //---------------------------------------------------------------------------------Buttons--------------------------------------------------------------------------------
        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTree();
            }
        });


        refresh3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
                increment=0;

            }
        });

        refresh3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (!search) {
                    add3.setText("add");
                    refresh();
                    increment=0;
                    add3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showTree();
                        }
                    });
                    refresh3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            refresh();
                            increment=0;
                        }
                    });
                }
                return true;
            }
        });

        return v;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------Methods--------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    public void showTree(){
        if(validateInput()){
            increment++;
            add();
            if (increment == 1) {
                a = arrayList3.get(0);
                root_arr[0] = a;
                root.setText(Arrays.toString(root_arr));
                root.setBackgroundResource(R.color.light_blue);
            }
            if (increment == 2) {
                b = arrayList3.get(1);
                root_arr[1] = b;
                Arrays.sort(root_arr);
                root.setText(Arrays.toString(root_arr));
            }
            if (increment == 3) {
                c = arrayList3.get(2);
                z = new float[root_arr.length];
                for (int i = 0; i < root_arr.length; i++) {
                    z[i] = root_arr[i];
                }

                z = addElement(z, c);
                Arrays.sort(z);

                root_arr = removeTheElement(root_arr, 1);
                root_arr[0] = z[1];
                node_1_arr[0] = z[0];
                node_3_arr[0] = z[2];

                root.setText(Arrays.toString(root_arr));
                node_1.setText(Arrays.toString(node_1_arr));
                node_3.setText(Arrays.toString(node_3_arr));

                node_1.setBackgroundResource(R.color.light_blue);
                node_3.setBackgroundResource(R.color.light_blue);
            }
            if (increment == 4) {
                d = arrayList3.get(3);
                if (d < root_arr[0]) {
                    addElement(node_1_arr, d);
                    Arrays.sort(node_1_arr);
                    node_1.setText(Arrays.toString(node_1_arr));

                } else {
                    addElement(node_3_arr, d);
                    Arrays.sort(node_3_arr);
                    node_3.setText(Arrays.toString(node_3_arr));

                }
            }
            if (increment == 5) {
                e = arrayList3.get(4);
                if (e < root_arr[0]) {
                    z1 = new float[node_1_arr.length];
                    for (int i = 0; i < node_1_arr.length; i++) {
                        z1[i] = node_1_arr[i];
                    }
                    z1 = addElement(z1, e);
                    Arrays.sort(z1);

                    if (z1.length == 2) {
                        node_1_arr = z1;
                        Arrays.sort(node_1_arr);
                        node_1.setText(Arrays.toString(node_1_arr));

                    } else {
                        root_arr = addElement(root_arr, z1[1]);
                        Arrays.sort(root_arr);
                        node_1_arr = removeTheElement(node_1_arr, 1);
                        node_1_arr[0] = z1[0];
                        node_2_arr[0] = z1[2];
                        node_1.setText(Arrays.toString(node_1_arr));
                        root.setText(Arrays.toString(root_arr));
                        node_2.setBackgroundResource(R.color.light_blue);
                        node_2.setText(Arrays.toString(node_2_arr));

                    }
                } else {
                    z1 = new float[node_3_arr.length];
                    for (int i = 0; i < node_3_arr.length; i++) {
                        z1[i] = node_3_arr[i];
                    }
                    z1 = addElement(z1, e);
                    Arrays.sort(z1);

                    if (z1.length == 2) {
                        node_3_arr = new float[z1.length];
                        for (int i = 0; i < z1.length; i++) {
                            node_3_arr[i] = z1[i];
                        }
                        Arrays.sort(node_3_arr);
                        node_3.setText(Arrays.toString(node_3_arr));

                    } else {
                        root_arr = addElement(root_arr, z1[1]);
                        Arrays.sort(root_arr);
                        node_3_arr = removeTheElement(node_3_arr, 1);
                        node_2_arr[0] = z1[0];
                        node_3_arr[0] = z1[2];
                        root.setText(Arrays.toString(root_arr));
                        node_2.setBackgroundResource(R.color.light_blue);
                        node_2.setText(Arrays.toString(node_2_arr));
                        node_3.setText(Arrays.toString(node_3_arr));

                    }
                }
            }
            if (increment == 6) {
                f = arrayList3.get(5);
                if (f < root_arr[0]) {
                    z2 = new float[node_1_arr.length];
                    for (int i = 0; i < node_1_arr.length; i++) {
                        z2[i] = node_1_arr[i];
                    }
                    z2 = addElement(z2, f);
                    Arrays.sort(z2);

                    if (z2.length == 2) {
                        node_1_arr = z2;
                        Arrays.sort(node_1_arr);
                        node_1.setText(Arrays.toString(node_1_arr));


                    } else {
                        root_arr = addElement(root_arr, z2[1]);
                        Arrays.sort(root_arr);
                        node_1_arr = removeTheElement(node_1_arr, 1);
                        node_1_arr[0] = z2[0];
                        node_2_arr[0] = z2[2];
                        node_1.setText(Arrays.toString(node_1_arr));
                        node_2.setBackgroundResource(R.color.light_blue);
                        node_2.setText(Arrays.toString(node_2_arr));

                    }
                } else {
                    z2 = new float[node_3_arr.length];
                    for (int i = 0; i < node_3_arr.length; i++) {
                        z2[i] = node_3_arr[i];
                    }
                    z2 = addElement(z2, f);
                    Arrays.sort(z2);

                    if (z2.length == 2) {
                        node_3_arr = new float[z2.length];
                        for (int i = 0; i < z2.length; i++) {
                            node_3_arr[i] = z2[i];
                        }
                        Arrays.sort(node_3_arr);
                        node_3.setText(Arrays.toString(node_3_arr));

                    } else {
                        root_arr = addElement(root_arr, z2[1]);
                        Arrays.sort(root_arr);
                        node_3_arr = removeTheElement(node_3_arr, 1);
                        node_2_arr[0] = z2[0];
                        node_3_arr[0] = z2[2];
                        root.setText(Arrays.toString(root_arr));
                        node_2.setBackgroundResource(R.color.light_blue);
                        node_2.setText(Arrays.toString(node_2_arr));
                        node_3.setText(Arrays.toString(node_3_arr));

                    }
                }
                searchFunction();
            }
        }else{
            return;
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void searchFunction() {
        search = true;
        if (search) {
            add3.setText("Search");
            add3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (number_array != null) {
                        searchNum = Float.parseFloat(number_array.getText().toString());
                        for (int h = 0; h < arrayList3.size(); h++) {
                            if (searchNum == arrayList3.get(h)) {
                                numSearch(root_arr, root);
                                numSearch(node_1_arr, node_1);
                                numSearch(node_2_arr, node_2);
                                numSearch(node_3_arr, node_3);

                            } else {
                                Toast.makeText(NumberWise.this.getContext(), searchNum + " " + "is not in the B-Tree", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(NumberWise.this.getContext(), "Enter number before click Add button", Toast.LENGTH_LONG).show();
                    }
                }
            });
            refresh3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    number_array.setText("");
                    root.setBackgroundResource(R.color.light_blue);
                    node_1.setBackgroundResource(R.color.light_blue);
                    node_2.setBackgroundResource(R.color.light_blue);
                    node_3.setBackgroundResource(R.color.light_blue);
                }
            });
        }
        search = false;
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void numSearch(float[] arr, TextView textView) {
        for (int l = 0; l < arr.length; l++) {
            if (searchNum == arr[l]) {
                textView.setBackgroundResource(R.color.red);
                Toast.makeText(NumberWise.this.getContext(), searchNum + " " + "is found in B-Tree", Toast.LENGTH_LONG).show();
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public  void  add(){
        if (number_array != null) {
            arrayList3.add(Float.parseFloat(number_array.getText().toString()));
            input_array.setText(arrayList3.toString());
            // adapter.notifyDataSetChanged();
            number_array.setText("");

            if (arrayList3.size() == 6){
//                Toast.makeText(number_input.this.getContext(), arrayList3.toString(), Toast.LENGTH_LONG).show();
                Toast.makeText(NumberWise.this.getContext(), "B-Tree of level 2 was created.", Toast.LENGTH_LONG).show();
            }


            //m = arrayList2.size();

            //Log.d("eeeeeeeeeeeeeeeeeeeeeee", String.valueOf(m));
        } else {
            Toast.makeText(NumberWise.this.getContext(), "Enter number before click Add button", Toast.LENGTH_LONG).show();
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static float[] removeTheElement(float[] arr, int index)
    {
        if (arr == null || index < 0
                || index >= arr.length) {
            return arr;
        }
        float[] anotherArray = new float[arr.length - 1];

        for (int i = 0, k = 0; i < arr.length; i++) {
            if (i == index) {
                continue;
            }
            anotherArray[k++] = (int) arr[i];
        }
        return anotherArray;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static float[] addElement(float[] arr, float value){

        for(int k=0; k< arr.length; k++) {
            if (arr[k] == 0.0f) {
                arr[k] = value;
                return arr;
            }
        }
        int n = arr.length;
        float[] new_arr = new float[n + 1];

        for(int p=0; p< n; p++){
            new_arr[p] = arr[p];
        }
        new_arr[n] = value;

        return new_arr;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public void refresh(){
        input_array.setText("");
        arrayList3.clear();
        root.setText("");
        node_1.setText("");
        node_2.setText("");
        node_3.setText("");
        root.setBackgroundResource(R.color.white);
        node_1.setBackgroundResource(R.color.white);
        node_2.setBackgroundResource(R.color.white);
        node_3.setBackgroundResource(R.color.white);
        root_arr = new float[2];
        node_1_arr = new float[2];
        node_2_arr = new float[2];
        node_3_arr = new float[2];

    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private Boolean validateInput(){

        String number = number_array.getText().toString();

        if(number.isEmpty()){
            number_array.setError("Field cannot be empty");
            return false;
        }else{
            number_array.setError(null);
            //  number_array.setEnabled(false);   //to remove the space of the error
            return true;
        }
    }
}