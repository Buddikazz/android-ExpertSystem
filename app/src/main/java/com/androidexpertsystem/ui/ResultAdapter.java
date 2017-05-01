package com.androidexpertsystem.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidexpertsystem.R;
import com.androidexpertsystem.models.ResultModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Serhii Slobodianiuk on 01.05.2017.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {


    private final ResultActivity mResultActivity;
    private final ArrayList<ResultModel> mData;

    public ResultAdapter(ResultActivity resultActivity, ArrayList<ResultModel> data) {
        mResultActivity = resultActivity;
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(mData.get(position).name);
        holder.proc.setText(mData.get(position).proc);
        holder.ram.setText(mData.get(position).ram);
        holder.memory.setText(mData.get(position).memory);
        holder.gpu.setText(mData.get(position).gpu);
        holder.os.setText(mData.get(position).os);
        holder.display.setText(mData.get(position).display);
        holder.battery.setText(mData.get(position).battery);
        holder.camera.setText(mData.get(position).camera);
        holder.usb.setText(mData.get(position).usb);
        holder.bluetooth.setText(mData.get(position).bluetooth);

        Picasso.with(mResultActivity)
                .load(mData.get(position).image)
                .fit()
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView image;

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.proc)
        TextView proc;

        @BindView(R.id.ram)
        TextView ram;

        @BindView(R.id.memory)
        TextView memory;

        @BindView(R.id.gpu)
        TextView gpu;

        @BindView(R.id.os)
        TextView os;

        @BindView(R.id.display)
        TextView display;

        @BindView(R.id.battery)
        TextView battery;

        @BindView(R.id.camera)
        TextView camera;

        @BindView(R.id.usb)
        TextView usb;

        @BindView(R.id.bluetooth)
        TextView bluetooth;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
