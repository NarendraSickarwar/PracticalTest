package test.narendra.com.gopettingpractical.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import io.realm.Realm;
import test.narendra.com.gopettingpractical.R;
import test.narendra.com.gopettingpractical.database.GuideDataTable;
import test.narendra.com.gopettingpractical.modal.GuideDataModal;
import test.narendra.com.gopettingpractical.utils.Utils;

/**
 * <h1>Guide List adapter, provides data for guide recycler@{@link android.support.v7.widget.RecyclerView.Adapter}</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */
public class GuideListAdapter extends RecyclerView.Adapter<GuideListAdapter.ViewHolder> {
    Context _cContext;
    Realm realm;
    private GuideDataModal mItems;


    public GuideListAdapter(Context _cContext, GuideDataModal items) {
        this.mItems = items;
        this._cContext = _cContext;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_guide, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.textviewGuideName.setText(mItems.getData().get(position).getName());
        holder.textviewGuideEndDate.setText(mItems.getData().get(position).getEndDate());
        Glide.with(_cContext).load(mItems.getData().get(position).getIcon())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        holder.imageViewGuide.setImageDrawable(resource);
                        holder.imageProgressBar.setVisibility(View.GONE);
                    }
                });

        holder.buttonGuideAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int addTocartItem;
                GuideDataTable dataTable = realm.where(GuideDataTable.class).findFirst();
                if (dataTable != null) {
                    realm.beginTransaction();
                    /***
                     * increase database cart item count
                     */
                    addTocartItem = dataTable.getTotal();
                    addTocartItem++;
                    dataTable.setTotal(addTocartItem);
                    for (int i = 0; i < dataTable.getData().size(); i++) {
                        if (dataTable.getData().get(i).getName().equals
                                (mItems.getData().get(holder.getAdapterPosition()).getName())) {
/***
 * add data in cart list and set data for main activity
 */
                            dataTable.getData().get(holder.getAdapterPosition()).setAddToCart(true);


                            /***
                             * hide add to cart button
                             */
                            holder.buttonGuideAddToCart.setVisibility(View.GONE);

                            break;
                        }

                    }

                    realm.copyToRealmOrUpdate(dataTable);
                    realm.commitTransaction();
                    /***
                     * send event to update budge on main activity
                     */
                    Utils.sendEventBusData(addTocartItem);

                }


            }
        });

        /***
         * hide and show add to cart button
         */
        if (mItems.getData().get(position).isAddToCart()) {
            holder.buttonGuideAddToCart.setVisibility(View.GONE);
        } else {
            holder.buttonGuideAddToCart.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        if (mItems != null) {
            return mItems.getData().size();
        }
        return 0;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewGuide;
        TextView textviewGuideName, textviewGuideEndDate;
        ProgressBar imageProgressBar;
        Button buttonGuideAddToCart;

        ViewHolder(View itemView) {
            super(itemView);

            imageViewGuide = (ImageView) itemView.findViewById(R.id.image_guide);
            textviewGuideName = (TextView) itemView.findViewById(R.id.textview_guide_name);
            textviewGuideEndDate = (TextView) itemView.findViewById(R.id.textview_guide_end_date);
            imageProgressBar = (ProgressBar) itemView.findViewById(R.id.image_progressbar);
            buttonGuideAddToCart = (Button) itemView.findViewById(R.id.button_guide_add_to_cart);
        }
    }
}