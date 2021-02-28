package com.example.basicfbaseapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference ref;
    Button add;
    EditText name,enrollment;
    ListView listView;
    ArrayList<StudentRead> studentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Students");

        name = findViewById(R.id.name);
        enrollment = findViewById(R.id.enrollment);
        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameA = name.getEditableText().toString();
                String enrollmentA = enrollment.getEditableText().toString();

                name.onEditorAction(EditorInfo.IME_ACTION_DONE);
                enrollment.onEditorAction(EditorInfo.IME_ACTION_DONE);

                if (nameA.matches("") || enrollmentA.matches("")){
                    Toast.makeText(getApplicationContext(),"Please Enter valid data",Toast.LENGTH_LONG).show();
                }
                else {
                    Student student = new Student(nameA, enrollmentA);
                    ref.child(enrollmentA).setValue(student);
                    Toast.makeText(getApplicationContext(),"Data Added",Toast.LENGTH_LONG).show();
                }
            }
        });

        listView = findViewById(R.id.listStudents);
        StudentAdapter studentAdapter = new StudentAdapter(this, studentList);
        listView.setAdapter(studentAdapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentList .clear();
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        StudentRead studentRead = dataSnapshot.getValue(StudentRead.class);
                        studentList.add(studentRead);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
            }
        });
    }

}