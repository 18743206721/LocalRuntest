package com.xingguang.core.base;

import android.content.Context;
import android.support.annotation.AnimRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;

/**
 * RecyclerView BaseAdapter
 *
 * @param <T> 数据类型
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // 上下文
    protected Context mContext;

    // 数据
    protected List<T> mData;

    // 监听接口
    protected OnItemClickListener mOnItemClickListener;
    protected OnItemLongClickListener mOnItemLongClickListener;

    protected int mLastPosition = -1;

    protected static final int TYPE_HEADER = 1001;
    protected static final int TYPE_NORMAL = 1002;

    protected View mHeaderView;

    public BaseRecyclerAdapter(Context context, List<T> list) {
        mData = list;
        mContext = context;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mHeaderView == null ? mData.size() : mData.size() + 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new Holder(mHeaderView);
        View layout = LayoutInflater.from(mContext).inflate(getLayout(), parent, false);

        RecyclerView.ViewHolder holder = createViewHolder(layout);
        if (holder == null)
            throw new RuntimeException("请先在createViewHolder()方法中返回 ViewHolder");
        return holder;
    }

    /**
     * item 布局id
     */
    protected abstract int getLayout();

    /**
     * 设置数据
     */
    protected abstract void bingView(RecyclerView.ViewHolder holder, T data, int pos);

    /**
     * 创建ViewHolder
     */
    protected abstract RecyclerView.ViewHolder createViewHolder(View layout);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;
        final int pos = getRealPosition(viewHolder);
        final T data = mData.get(pos);

        if (viewHolder instanceof BaseRecyclerAdapter.Holder)
            bingView(viewHolder, data, pos);

        if (mOnItemClickListener != null) {

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getItemViewType(position) != TYPE_HEADER)
                        mOnItemClickListener.onItemClick(pos, data);
                }
            });
        }

        if (mOnItemLongClickListener != null) {

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (getItemViewType(position) != TYPE_HEADER)
                        mOnItemLongClickListener.onItemClick(pos, data);
                    return false;
                }
            });
        }
    }

    public interface OnItemClickListener<T> {
        void onItemClick(int position, T data);
    }

    public interface OnItemLongClickListener<T> {
        void onItemClick(int position, T data);
    }

    protected class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    protected View getView(ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    public void add(int position, T item) {
        mData.add(position, item);
        notifyItemInserted(position);
    }

    public void addMore(List<T> data) {
        int startPosition = mHeaderView == null ? mData.size() : mData.size() + 1;
        mData.addAll(data);
        notifyItemRangeInserted(startPosition, mData.size());
    }

    public void delete(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    public List<T> getList() {
        return mData;
    }

    public void setList(List<T> items) {
        mData = items;
        notifyDataSetChanged();
    }

    protected void setItemAppearAnimation(RecyclerView.ViewHolder holder, int position, @AnimRes int type) {
        if (position > mLastPosition) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), type);
            holder.itemView.startAnimation(animation);
            mLastPosition = position;
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }
}
