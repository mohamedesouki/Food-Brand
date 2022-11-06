package org.first.foodbrand.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.first.foodbrand.R;
import org.first.foodbrand.models.BeefModel;
import org.first.foodbrand.models.DishModel;

import java.util.List;
import java.util.function.Consumer;

public class FragmentAdapter extends RecyclerView.Adapter<FragmentAdapter.ViewHolder> {
    List<BeefModel> categoryList;
    Context context;
    Consumer<BeefModel> consumer;

    public FragmentAdapter(List<BeefModel> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }
    public void setOnConsumer(Consumer<BeefModel> consumer){
        this.consumer = consumer;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_card,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewTitle.setText(categoryList.get(position).getTitle());
        Glide.with(context).load(categoryList.get(position).getImage()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                consumer.accept(categoryList.get(position));



            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView_category_fragment);
        textViewTitle = itemView.findViewById(R.id.textView_category_fragment);
    }
}
}


