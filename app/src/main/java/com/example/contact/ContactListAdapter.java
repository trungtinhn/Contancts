package com.example.contact;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ContactListAdapter extends ArrayAdapter<ContactItem> {
    Activity Context;
    int Idlayout;
    ArrayList<ContactItem> mylist;
    public ContactListAdapter(Activity context, int idLayout, ArrayList<ContactItem> arrayList){
        super(context, idLayout, arrayList);
        this.Context = context;
        Idlayout = idLayout;
        this.mylist = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = Context.getLayoutInflater();
        convertView = layoutInflater.inflate(Idlayout, null);

        ContactItem item = mylist.get(position);
        TextView name = convertView.findViewById(R.id.txt_name);
        TextView phone = convertView.findViewById(R.id.txt_phone);

        name.setText(item.getName());
        phone.setText(item.getNumber());
        return convertView;
    }
}
