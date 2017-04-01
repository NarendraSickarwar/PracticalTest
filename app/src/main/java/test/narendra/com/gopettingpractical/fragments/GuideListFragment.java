package test.narendra.com.gopettingpractical.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import io.realm.Realm;
import test.narendra.com.gopettingpractical.R;
import test.narendra.com.gopettingpractical.adapter.GuideListAdapter;
import test.narendra.com.gopettingpractical.database.GuideDataTable;
import test.narendra.com.gopettingpractical.modal.GuideDataModal;
import test.narendra.com.gopettingpractical.mvp.MenuMVP;
import test.narendra.com.gopettingpractical.mvp.Presentor;
import test.narendra.com.gopettingpractical.utils.GuideItemDecoration;
import test.narendra.com.gopettingpractical.utils.ProgressDialog;
import test.narendra.com.gopettingpractical.utils.Utils;

/**
 * <h1>Guide List frament @{@link Fragment}</h1>
 *
 * @author Narendra Singh
 * @version 1.0
 * @since 01-04-2017
 */
public class GuideListFragment extends Fragment implements MenuMVP.RequiredViewOps {
    Presentor presentor;
    ProgressDialog pProgressDialog;

    RecyclerView lGuidesList;
    LinearLayout fragmentGuideListMainLayout;
    private Realm realm;

    public static GuideListFragment newInstance() {
        Bundle args = new Bundle();
        GuideListFragment fragment = new GuideListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide_list, container, false);
        bindViews(view);
        presentor = new Presentor(this);
        presentor.inIt(getActivity());
        pProgressDialog = new ProgressDialog(getActivity());
        realm = Realm.getDefaultInstance();

        GuideDataTable modal = realm.where(GuideDataTable.class).findFirst();

        if (modal != null) {

            /**
             * get Data from database GuideDataTable -> GuideDataModal
             */
            GuideDataModal menuItems = new GuideDataModal();
            menuItems.getData().addAll(modal.getData());
            // set on list
            showDataList(menuItems);
            /***
             * send event to update budge on main activity
             */

            Utils.sendEventBusData(modal.getTotal());
        } else {
            //calling webservice
            presentor.getGuideItems();
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

    @Override
    public void hideProgressBar() {
        pProgressDialog.hideProgressDialog();
    }

    @Override
    public void showProgressBar() {
        pProgressDialog.showProgressDialog(getString(R.string.pleasewait));
    }

    @Override
    public void showMessage(String message) {
        Utils.showSnackBar(fragmentGuideListMainLayout, message);
    }

    @Override
    public void showMessage(@StringRes int message) {
        Utils.showSnackBar(fragmentGuideListMainLayout, getString(message));
    }

    @Override
    public void showGuideItems(GuideDataModal menuItems) {

        showDataList(menuItems);
        GuideDataTable dataTable = new GuideDataTable();
        /**
         * save data in data base GuideDataModal -> GuideDataTable
         */
        dataTable.getData().addAll(menuItems.getData());
        Utils.saveRealmDataBase(realm, dataTable);

    }

    private void showDataList(GuideDataModal menuItems) {
        LinearLayoutManager staggeredGridLayoutManager = new LinearLayoutManager(getActivity());

        lGuidesList.setLayoutManager(staggeredGridLayoutManager);
        GuideListAdapter guideAdapter = new GuideListAdapter(getActivity(), menuItems);
        lGuidesList.setAdapter(guideAdapter);
        GuideItemDecoration decoration = new GuideItemDecoration(10);
        lGuidesList.addItemDecoration(decoration);
    }
}
