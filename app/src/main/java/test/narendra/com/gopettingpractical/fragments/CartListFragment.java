package test.narendra.com.gopettingpractical.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import io.realm.Realm;
import test.narendra.com.gopettingpractical.R;
import test.narendra.com.gopettingpractical.adapter.CartListAdapter;
import test.narendra.com.gopettingpractical.database.GuideDataTable;
import test.narendra.com.gopettingpractical.modal.GuideDataModal;
import test.narendra.com.gopettingpractical.mvp.Presentor;
import test.narendra.com.gopettingpractical.utils.GuideItemDecoration;
import test.narendra.com.gopettingpractical.utils.ProgressDialog;

/**
 * <h1>Cart List fragment @{@link Fragment}</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */
public class CartListFragment extends Fragment {
    Presentor presentor;
    ProgressDialog pProgressDialog;
    RecyclerView lGuidesList;
    LinearLayout fragmentGuideListMainLayout;
    private Realm realm;

    public static CartListFragment newInstance() {
        Bundle args = new Bundle();
        CartListFragment fragment = new CartListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_list, container, false);
        bindViews(view);
        realm = Realm.getDefaultInstance();


        GuideDataTable modal = realm.where(GuideDataTable.class).findFirst();
        if (modal != null) {
            GuideDataModal menuItems = new GuideDataModal();
            for (int i = 0; i < modal.getData().size(); i++) {
                if (modal.getData().get(i).isAddToCart()) {
                    menuItems.getData().add(modal.getData().get(i));
                }
            }
            showDataList(menuItems);

        } else {

        }
        return view;
    }

    private void bindViews(View view) {
        lGuidesList = (RecyclerView) view.findViewById(R.id.guides_list);
        fragmentGuideListMainLayout = (LinearLayout) view.findViewById(R.id.fragment_guide_list_main_layout);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
    }


    private void showDataList(GuideDataModal menuItems) {
        LinearLayoutManager staggeredGridLayoutManager = new LinearLayoutManager(getActivity());

        lGuidesList.setLayoutManager(staggeredGridLayoutManager);
        CartListAdapter guideAdapter = new CartListAdapter(getActivity(), menuItems);
        lGuidesList.setAdapter(guideAdapter);
        GuideItemDecoration decoration = new GuideItemDecoration(10);
        lGuidesList.addItemDecoration(decoration);
    }
}
