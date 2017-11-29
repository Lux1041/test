package com.example.tianyi.iphoneassist.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ActionProvider;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tianyi.iphoneassist.R;

/**
 * Created by Tianyi on 2017/11/28.
 */

public class BadgeActionProvider extends ActionProvider {

    private Context mContext;
    private LayoutInflater inflater;

    private ImageView imgDownload;
    private TextView tvNumber;

    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public BadgeActionProvider(Context context) {
        super(context);
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View onCreateActionView() {

        int size = getContext().getResources().getDimensionPixelSize(android.support.design.R.dimen.abc_action_bar_default_height_material);
        ViewGroup.LayoutParams lp = new RecyclerView.LayoutParams(size, size);

        View view = inflater.inflate(R.layout.toolbar_item_download, null);

        view.setLayoutParams(lp);

        imgDownload = (ImageView) view.findViewById(R.id.tool_bar_download);
        tvNumber = (TextView) view.findViewById(R.id.tool_bar_textnumber);

        return view;
    }

    public void setDownloadIcon(Drawable drawable){
        imgDownload.setImageDrawable(drawable);
    }

    public void setNumber(int number){
        tvNumber.setVisibility(View.VISIBLE);
        tvNumber.setText(number + "");
    }

}
