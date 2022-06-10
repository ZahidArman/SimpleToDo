package sg.edu.rp.c346.id21005622.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etToDo;
    Spinner spnChoice;
    Button btnAdd;
    Button btnClear;
    Button btnDelete;
    ListView lvToDo;
    ArrayList<String> alToDo;
    ArrayAdapter<String> aaToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etToDo = findViewById(R.id.editTextToDo);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        btnDelete = findViewById(R.id.btnDelete);
        lvToDo = findViewById(R.id.listViewToDo);
        spnChoice = findViewById(R.id.spinner);

        alToDo = new ArrayList<>();

        aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alToDo);
        lvToDo.setAdapter(aaToDo);

        spnChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        etToDo.setHint("Type in a new task here");
                        btnDelete.setEnabled(false);
                        btnDelete.setTextColor(Color.parseColor("#808080"));
                        btnAdd.setEnabled(true);
                        btnAdd.setTextColor(Color.parseColor("#000000"));
                        break;
                    case 1:
                        etToDo.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        btnAdd.setTextColor(Color.parseColor("#808080"));
                        btnDelete.setTextColor(Color.parseColor("#000000"));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String toDo = etToDo.getText().toString();
                alToDo.add(toDo);

                aaToDo.notifyDataSetChanged();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alToDo.clear();
                aaToDo.notifyDataSetChanged();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = Integer.parseInt(etToDo.getText().toString());
                if (alToDo.size()== 0){
                    Toast.makeText(MainActivity.this,"You don't have any task to remove",Toast.LENGTH_SHORT).show();
                }
                else if(pos > alToDo.size() || pos <= 0){
                    Toast.makeText(MainActivity.this,"Wrong index number",Toast.LENGTH_SHORT).show();
                }
                else{
                    alToDo.remove(pos-1);
                    aaToDo.notifyDataSetChanged();
                }
            }
        });

    }
}