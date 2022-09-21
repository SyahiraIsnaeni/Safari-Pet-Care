package belajar.android.safaripetcare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.MyViewHolder> {

    private List<GuestDetail> guestList;
    private Activity activity;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    public GuestAdapter(List<GuestDetail>guestList, Activity activity){
        this.guestList = guestList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final GuestDetail data = guestList.get(position);
        holder.petName.setText(data.getPetName());
        holder.petSpecies.setText(data.getPetSpecies());
        holder.petGender.setText(data.getPetGender());
        holder.petOwner.setText(data.getPetOwner());
        holder.contactOwner.setText(data.getContactOwner());
        holder.petTreatment.setText(data.getPetTreatment());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int yes) {
                        database.child("Guest").child(data.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(activity, "Data has been removed successfully", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity, "Failed to removed data", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int no) {
                        dialog.dismiss();
                    }
                }).setMessage("Are you sure you want to delete " + data.getPetName() + " ?");
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return guestList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView petName, petSpecies, petGender, petOwner, contactOwner, petTreatment;
        CardView cardResult;
        ImageButton deleteButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            petName = itemView.findViewById(R.id.petname);
            petSpecies = itemView.findViewById(R.id.petspecies);
            petGender = itemView.findViewById(R.id.petgender);
            petOwner = itemView.findViewById(R.id.petowner);
            contactOwner = itemView.findViewById(R.id.contactowner);
            petTreatment = itemView.findViewById(R.id.pettreatment);
            cardResult = itemView.findViewById(R.id.card);
            deleteButton = itemView.findViewById(R.id.delete);

        }
    }
}
