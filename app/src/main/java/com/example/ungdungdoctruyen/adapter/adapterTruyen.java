package com.example.ungdungdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.ungdungdoctruyen.R;

import java.util.ArrayList;
import com.example.ungdungdoctruyen.modle.*;
import com.squareup.picasso.Picasso;

public class adapterTruyen extends BaseAdapter {

    private Context context;
    private ArrayList<Truyen> listTruyen;

    public adapterTruyen(Context context, ArrayList<Truyen> truyenArraylist) {
        this.context = context;
        this.listTruyen = truyenArraylist;
    }

    @Override
    public int getCount() {
        return listTruyen.size();
    }

    @Override
    public Object getItem(int position) {
        return listTruyen.get(  position );
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    public class  ViewHolder{
        TextView txtTenTruyen;
        ImageView imageTruyen;

    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();
        LayoutInflater inflater =(LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        view = inflater.inflate( R.layout.newtruyen,null );
        viewHolder.txtTenTruyen = view.findViewById( R.id.textviewTenTruyen );
        viewHolder.imageTruyen = view.findViewById( R.id.imgNewTruyen );
        view.setTag( viewHolder );
        Truyen truyen =(Truyen) getItem( position );
        viewHolder.txtTenTruyen.setText( truyen.getTenTruyen() );
        Picasso.get().load( truyen.getAnh() ).placeholder( R.drawable.ic_dowload).error( R.drawable.ic_imagesupot).into( viewHolder.imageTruyen );

        return view;
    }
}
