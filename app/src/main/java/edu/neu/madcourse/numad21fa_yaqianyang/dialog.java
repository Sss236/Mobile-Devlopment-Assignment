package edu.neu.madcourse.numad21fa_yaqianyang;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;

public class dialog extends androidx.fragment.app.DialogFragment
{
    private final RviewAdapter rviewAdapter;
    private EditText name,link;

    public dialog(RviewAdapter rviewAdapter){
        this.rviewAdapter = rviewAdapter;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog,null);

        name = view.findViewById(R.id.item_name);
        link = view.findViewById(R.id.item_link);
        View v = getActivity().findViewById(R.id.recycler_view);
        String title = "Create new URL";
        builder.setView(view).setMessage(title)
                .setNegativeButton("Confirm", (dialog, i) -> {
                    if(link.getText().toString().startsWith("https://") && link.getText().toString().endsWith(".com")) {
                        rviewAdapter.addItem(new ItemCard(name.getText().toString(),link.getText().toString()));
                        Snackbar.make(v, "Input saved successfully!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    } else{
                        Snackbar.make(v, "Invalid input.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        dialog.dismiss();
                    }

                });
        return builder.create();
    }
}
