package com.happy.ninjanote.layoutbox;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.happy.ninjanote.adapter.InfoAdapter002;
import com.happy.ninjanote.data.InfoData001;
import com.happy.ninjanote.utility.Constant;

import java.util.ArrayList;
import java.util.List;

public class ListHeaderShowcase extends LayoutShowcase002 {
    private static final String KEY_BG_COLOR = "key_bg_color";
    private static final String KEY_TITLE_COLOR = "key_title_color";
    private static final String KEY_COLOR_INDEX = "key_color_index";
    private int bgColor, titleColor, colorIndex;
    private List<InfoData001> dataList = new ArrayList<>();

    public static void launch(final Context appCtx,
                              final int bgColor,
                              final int titleColor,
                              final int colorIndex) {
        final Intent intent = new Intent(appCtx, ListHeaderShowcase.class);

        Bundle bundle = new Bundle();
        bundle.putInt(KEY_BG_COLOR, bgColor);
        bundle.putInt(KEY_TITLE_COLOR, titleColor);
        bundle.putInt(KEY_COLOR_INDEX, colorIndex);
        intent.putExtras(bundle);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appCtx.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        makeData();
        super.onCreate(savedInstanceState);
    }

    public void makeData2() {
        int base = 20;


        Log.d("testt", "base:" + Integer.toBinaryString(base));
        for (int index = 0; index < 33; index++) {
            Log.d("testt", "base << " + index + ":" + Integer.toBinaryString(base << index));
        }
        for (int index = 0; index < 33; index++) {
            Log.d("testt", "base << " + index + ":" + Integer.toHexString(base << index));
        }


    }

    public void makeData() {
        final Intent intent = getIntent();
        bgColor = intent.getIntExtra(KEY_BG_COLOR, -1);
        titleColor = intent.getIntExtra(KEY_TITLE_COLOR, -1);
        colorIndex = intent.getIntExtra(KEY_COLOR_INDEX, -1);

        boolean isBgColor = true;
        int shift = 0;

        int start = 0, end = 0;

        switch (colorIndex) {
            case Constant.CL_01: {
                start = (bgColor & 0xffffff00);
                end = (bgColor | 0xff0000ff);
            }
            break;
            case Constant.CL_02: {
                start = (bgColor & 0xffff00ff);
                end = (bgColor | 0xff00ff00);
                shift = 8;
            }
            break;
            case Constant.CL_03: {
                start = (bgColor & 0xff00ffff);
                end = (bgColor | 0xffff0000);
                shift = 16;
            }
            break;
            case Constant.CL_04: {
                start = (titleColor & 0xffffff00);
                end = (titleColor | 0xff0000ff);
                isBgColor = false;
            }
            break;
            case Constant.CL_05: {
                start = (titleColor & 0xffff00ff);
                end = (titleColor | 0xff00ff00);
                isBgColor = false;
                shift = 8;

            }
            break;
            case Constant.CL_06: {
                start = (titleColor & 0xff00ffff);
                end = (titleColor | 0xffff0000);
                isBgColor = false;
                shift = 16;

            }
            break;
        }

        String bgClStr = "", titleClStr = "", tmpStr, tmpStr1, tmpStr2, tmpStr3;

        for (int index = start; index <= end; ) {
            final InfoData001 data = new InfoData001();
            data.viewType = Constant.V_T_INFO_001;

            index += (5 << shift);

            if (isBgColor) {
              /*background color*/
                data.infoInt001_01 = index;
               /*title color*/
                data.infoInt001_02 = titleColor;
            } else {
              /*background color*/
                data.infoInt001_01 = bgColor;
               /*title color*/
                data.infoInt001_02 = index;
            }

            switch (colorIndex) {
                case Constant.CL_01: {
                    tmpStr = Integer.toHexString(data.infoInt001_01);
                    tmpStr1 = tmpStr.substring(0, 6);
                    tmpStr2 = " " + tmpStr.substring(6) + " ";
                    bgClStr = tmpStr1 + tmpStr2;

                    titleClStr = Integer.toHexString(data.infoInt001_02);

                }
                break;
                case Constant.CL_02: {
                    tmpStr = Integer.toHexString(data.infoInt001_01);
                    tmpStr1 = tmpStr.substring(0, 4);
                    tmpStr2 = " " + tmpStr.substring(4, 6) + " ";
                    tmpStr3 = tmpStr.substring(6);
                    bgClStr = tmpStr1 + tmpStr2 + tmpStr3;

                    titleClStr = Integer.toHexString(data.infoInt001_02);
                }
                break;
                case Constant.CL_03: {
                    tmpStr = Integer.toHexString(data.infoInt001_01);
                    tmpStr1 = tmpStr.substring(0, 2);
                    tmpStr2 = " " + tmpStr.substring(2, 4) + " ";
                    tmpStr3 = tmpStr.substring(4);
                    bgClStr = tmpStr1 + tmpStr2 + tmpStr3;

                    titleClStr = Integer.toHexString(data.infoInt001_02);
                }
                break;
                case Constant.CL_04: {
                    tmpStr = Integer.toHexString(data.infoInt001_02);
                    tmpStr1 = tmpStr.substring(0, 6);
                    tmpStr2 = " " + tmpStr.substring(6) + " ";
                    titleClStr = tmpStr1 + tmpStr2;

                    bgClStr = Integer.toHexString(data.infoInt001_01);
                }
                break;
                case Constant.CL_05: {
                    tmpStr = Integer.toHexString(data.infoInt001_02);
                    tmpStr1 = tmpStr.substring(0, 4);
                    tmpStr2 = " " + tmpStr.substring(4, 6) + " ";
                    tmpStr3 = tmpStr.substring(6);
                    titleClStr = tmpStr1 + tmpStr2 + tmpStr3;

                    bgClStr = Integer.toHexString(data.infoInt001_01);
                }
                break;
                case Constant.CL_06: {
                    tmpStr = Integer.toHexString(data.infoInt001_02);
                    tmpStr1 = tmpStr.substring(0, 2);
                    tmpStr2 = " " + tmpStr.substring(2, 4) + " ";
                    tmpStr3 = tmpStr.substring(4);
                    titleClStr = tmpStr1 + tmpStr2 + tmpStr3;


                    bgClStr = Integer.toHexString(data.infoInt001_01);
                }
                break;
            }
            data.infoStr001_01 = "bkground/title: "
                    + bgClStr + "/" + titleClStr;
            dataList.add(data);
        }

    }

    @Override
    protected RecyclerView.Adapter makeAdapter() {
        return new InfoAdapter002(appCtx, dataList);
    }
}