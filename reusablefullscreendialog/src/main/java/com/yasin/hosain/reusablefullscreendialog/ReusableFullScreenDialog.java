package com.yasin.hosain.reusablefullscreendialog;


import android.app.Dialog;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by YASIN on 04,July,2019
 * Email: yasinenubd5@gmail.com
 */
public class ReusableFullScreenDialog extends DialogFragment {

    public static final String TITLE_KEY="title";
    public static final String LAYOUT_KEY="layout";
    public static final String THEME_KEY="theme";
    public static final String DIALOG_KEY="dialog";
    public static final String TAG ="ReusableFullScreenDialog";

    private int themeId;
    private int layoutId;
    private String title;
    private OnSetFullScreenDialogView onSetFullScreenDialogView;

    public static ReusableFullScreenDialog newInstance(int layoutResourceID, int themeResourceId, String title, OnSetFullScreenDialogView onSetFullScreenDialogView) {
        ReusableFullScreenDialog frag = new ReusableFullScreenDialog();
        Bundle args = new Bundle();
        args.putInt(LAYOUT_KEY,layoutResourceID);
        args.putInt(THEME_KEY,themeResourceId);
        args.putString(TITLE_KEY, title);
        args.putParcelable(DIALOG_KEY,onSetFullScreenDialogView);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeId=getArguments().getInt(THEME_KEY);
        layoutId=getArguments().getInt(LAYOUT_KEY);
        title=getArguments().getString(TITLE_KEY);
        onSetFullScreenDialogView=getArguments().getParcelable(DIALOG_KEY);
        setStyle(android.app.DialogFragment.STYLE_NORMAL,themeId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Toolbar toolbar=new Toolbar(getContext());
        TypedValue tv = new TypedValue();
        int actionBarHeight=130;
        if (getContext().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,getResources().getDisplayMetrics());
        }
        LinearLayout.LayoutParams toolBarParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, actionBarHeight);
        toolbar.setLayoutParams(toolBarParams);
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
        TypedValue typedValue = new TypedValue();
        TypedArray a = getContext().obtainStyledAttributes(typedValue.data, new int[] { R.attr.colorPrimary});
        int color = a.getColor(0, 0);
        a.recycle();
        toolbar.setBackgroundColor(color);
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_close_clear_cancel);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        View view=inflater.inflate(layoutId, container, false);
        ((ViewGroup)view).addView(toolbar);
        return onSetFullScreenDialogView.setView(view,getDialog());
    }

    public interface OnSetFullScreenDialogView extends Parcelable {
        View setView(View view, Dialog dialog);
    }
}