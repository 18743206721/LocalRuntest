package com.xingguang.localrun.maincode.mine.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xingguang.localrun.R;
import com.xingguang.localrun.utils.ImageLoader;
import com.xingguang.localrun.view.CommonViewHolder;

import java.util.ArrayList;

/**
 * 创建时间：2017/7/15 0015 13:57
 * 修改备注：
 */

public class PinglunPhotosAdapter extends RecyclerView.Adapter<CommonViewHolder> {
    private ArrayList<String> photos;
    private Context mContext;
    private AddPhotosListener addPhotosListener;


    public PinglunPhotosAdapter(ArrayList<String> photos, Context mContext) {
        this.photos = photos;
        this.mContext = mContext;
    }

    //添加照片
    public void setAddPhotosListener(AddPhotosListener addPhotosListener) {
        this.addPhotosListener = addPhotosListener;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = null;
        switch (viewType) {
            case 1:
                commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_pinglun_gridview);
                break;
            case 2:
                commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_pinglun_text);
                break;
        }
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {

        int type = getItemViewType(position);
        switch (type) {
            case 1:
                ImageView item_iv_gridphoto = holder.getItemView().findViewById(R.id.item_iv_gridphoto);
                ImageLoader.getInstance().initGlide(mContext).loadImage(photos.get(position), item_iv_gridphoto);


                //方法二：编辑时，将图片路径转换成文件格式的/storage/emulated/0/MagazineUnlock/magazine-unlock-01-2.3.850-_cf01710a88274aaf963aaa6a1755afa6.jpg
//                String fileName = "a";
//                FileOutputStream b = null;
//                Bitmap image = (item_iv_gridphoto.getDrawingCache());
//                File file = new File("/sdcard/abcd");
//                if (!file.exists()) {
//                    file.mkdir();
//                }
//                String newFilePath = "/sdcard/abcd"+"/" + fileName;
//                file = new File(newFilePath);
//                try {
//                    b = new FileOutputStream(file);
//                    file.createNewFile();
//                    b = new FileOutputStream(fileName);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                image.compress(Bitmap.CompressFormat.PNG, 100, b);// 把数据写入文件

                break;
            case 2:
                holder.setItemClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addPhotosListener.addPhotos();
                    }
                });
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (photos != null && position < photos.size()) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        if (photos.size()==5){
            return photos.size()+1;
        }else {
            return photos == null ? 1 : photos.size() + 1;
        }
    }

    public interface AddPhotosListener {
        void addPhotos();
    }



}
