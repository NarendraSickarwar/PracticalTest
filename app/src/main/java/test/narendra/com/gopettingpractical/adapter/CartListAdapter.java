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
 * <h1>Cart List adapter, provides data for cart recycler@{@link android.support.v7.widget.RecyclerView.Adapter}</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */
public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    Context _cContext;
    Realm realm;
    private GuideDataModal mItems;

    public CartListAdapter(Context _cContext, GuideDataModal items) {
        this.mItems = items;
        this._cContext = _cContext;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public CartListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textviewCartProductName.setText(mItems.getData().get(position).getName());
        holder.textviewCartProductEndDate.setText(mItems.getData().get(position).getEndDate());
        Glide.with(_cContext).load(mItems.getData().get(position).getIcon())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        holder.imageCartProduct.setImageDrawable(resource);
                        holder.imageCartProductProgressbar.setVisibility(View.GONE);
                    }
                });

        holder.buttonRemoveProduct.setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View view) {

                                                              /**
                                                               * remove cart item form cart list and update realm database
                                                               */
                                                              int addTocartItem = 0;
                                                              realm.beginTransaction();
                                                              GuideDataTable dataTable = realm.where(GuideDataTable.class).findFirst();
                                                              if (dataTable != null) {
                                                                  addTocartItem = dataTable.getTotal();
                                                                  addTocartItem--;
                                                                  dataTable.setTotal(addTocartItem);

                                                                  for (int i = 0; i < dataTable.getData().size(); i++) {
                                                                      if (dataTable.getData().get(i).getName().equals
                                                                              (mItems.getData().get(holder.getAdapterPosition()).getName())) {
                                                                          dataTable.getData().get(i).setAddToCart(false);
                                                                          break;
                                                                      }

                                                                  }


                                                              }
                                                              realm.copyToRealmOrUpdate(dataTable);
                                                              realm.commitTransaction();
/**
 * cart adapter data notify
 */
                                                              GuideDataTable data = realm.where(GuideDataTable.class).findFirst();
                                                              mItems.getData().clear();
                                                              for (int i = 0; i < data.getData().size(); i++) {
                                                                  if (data.getData().get(i).isAddToCart()) {
                                                                      mItems.getData().add(data.getData().get(i));

                                                                  }
                                                                  notifyDataSetChanged();

                                                              }

                                                              /***
                                                               * send event to update budge on main activity
                                                               */
                                                              Utils.sendEventBusData(addTocartItem);
                                                          }
                                                      }

        );
    }


    @Override
    public int getItemCount() {
        if (mItems != null) {
            return mItems.getData().size();
        }
        return 0;
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageCartProduct;
        TextView textviewCartProductName, textviewCartProductEndDate;
        ProgressBar imageCartProductProgressbar;
        Button buttonRemoveProduct;

        ViewHolder(View itemView) {
            super(itemView);

            imageCartProduct = (ImageView) itemView.findViewById(R.id.image_cart_product);
            textviewCartProductName = (TextView) itemView.findViewById(R.id.textview_cart_product_name);
            textviewCartProductEndDate = (TextView) itemView.findViewById(R.id.textview_cart_product_end_date);
            imageCartProductProgressbar = (ProgressBar) itemView.findViewById(R.id.image_cart_product_progressbar);
            buttonRemoveProduct = (Button) itemView.findViewById(R.id.button_remove_product);
        }
    }
}