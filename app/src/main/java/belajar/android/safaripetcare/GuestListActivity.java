package belajar.android.safaripetcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GuestListActivity extends AppCompatActivity {

    ImageButton deleteButton;
    GuestAdapter guestAdapter;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<GuestDetail> guestList;
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);

        deleteButton = findViewById(R.id.delete);
        list = findViewById(R.id.list);

        RecyclerView.LayoutManager guestLayout = new LinearLayoutManager(this);
        list.setLayoutManager(guestLayout);
        list.setItemAnimator(new DefaultItemAnimator());

        viewData();

    }

    private void viewData() {

        database.child("Guest").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                guestList = new ArrayList<>();
                for(DataSnapshot item : snapshot.getChildren()){
                    GuestDetail guest = item.getValue(GuestDetail.class);
                    guest.setKey(item.getKey());
                    guestList.add(guest);
                }

                guestAdapter = new GuestAdapter(guestList, GuestListActivity.this);
                list.setAdapter(guestAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}