package com.omjoonkim.doyouremember.app.writing.selectfrequentlyusedaccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.writing.selectfrequentlyusedaccount.listener.OnItemClickListener;
import com.omjoonkim.doyouremember.realm.entitiy.PersonRealmObject;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class SelectFrequentlyUsedActivity extends AppCompatActivity implements OnItemClickListener {

    @BindDimen(R.dimen.frequent_list_first_margin)
    int spacingFirstSize;

    @BindView(R.id.toolbar_select_frequent_user)
    Toolbar tbSelectUser;

    @BindView(R.id.recycler_write_select_frequently_used_account)
    RecyclerView recyclerView;

    private SelectFrequentlyUsedAdapter adapter;
    private Realm realm;
    private RealmResults<PersonRealmObject> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_frequent_user);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        initRecyclerViewInit();
    }

    private void initRecyclerViewInit(){

        setSupportActionBar(tbSelectUser);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tbSelectUser.setNavigationIcon(R.drawable.back_icon);

        adapter = new SelectFrequentlyUsedAdapter(getApplicationContext(), this);
        result = realm.where(PersonRealmObject.class).findAll();
        adapter.setData(result);
        recyclerView.addItemDecoration(new SelectFrequentUserItemDecoration(spacingFirstSize));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent();
        intent.putExtra("ACCOUNT_NAME", result.get(position).getName());
        intent.putExtra("ACCOUNT_BANK", result.get(position).getAccountList().get(0).getBankType());
        intent.putExtra("ACCOUNT_NUMBER", result.get(position).getAccountList().get(0).getAccountNumber());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close(); // Remember to close Realm when done.
    }
}

