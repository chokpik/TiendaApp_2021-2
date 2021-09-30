package com.example.tiendaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter {

    private ArrayList<Producto> listado;
    private OnItemClickListener onItemClickListener;

    public ProductoAdapter(ArrayList<Producto> listado)
    {
        this.listado = listado;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setListado(ArrayList<Producto> listado) {
        this.listado = listado;
        notifyDataSetChanged();
    }


    public class ProductoViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProducto;
        TextView tvNombreProducto;
        TextView tvPrecio;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProducto = itemView.findViewById(R.id.iv_item_producto);
            tvNombreProducto = itemView.findViewById(R.id.tv_nombre_item_producto);
            tvPrecio = itemView.findViewById(R.id.tv_precio_item_producto);

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);

        return new ProductoViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Producto producto = listado.get(position);

        final ProductoViewHolder miHolder = (ProductoViewHolder) holder;
        miHolder.tvNombreProducto.setText(producto.getNombre());
        miHolder.tvPrecio.setText(String.valueOf(producto.getPrecio()));
        Glide.with(holder.itemView.getContext()).load(producto.getUrlImagen()).into(miHolder.ivProducto);

        miHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(miHolder.itemView.getContext(), "item: " + producto.getNombre(), Toast.LENGTH_SHORT).show();

                if(onItemClickListener != null){

                    onItemClickListener.onItemClick(producto);

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return listado.size();
    }


    public interface OnItemClickListener{
        void onItemClick(Producto producto);
    }

}
