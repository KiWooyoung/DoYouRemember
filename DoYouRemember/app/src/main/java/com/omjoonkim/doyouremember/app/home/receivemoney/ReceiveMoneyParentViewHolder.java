package com.omjoonkim.doyouremember.app.home.receivemoney;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.daimajia.swipe.SwipeLayout;
import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.home.receivemoney.listener.OnHomeReceiveClickListener;
import com.omjoonkim.doyouremember.model.HomeReceiveChildData;
import com.omjoonkim.doyouremember.model.HomeReceiveParentData;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceiveMoneyParentViewHolder extends ParentViewHolder {

    public static final String TAG = ReceiveMoneyParentViewHolder.class.getSimpleName();

    @BindView(R.id.textview_receive_title)
    TextView tvReceiveTitle;

    @BindView(R.id.textview_receive_debtor_count)
    TextView tvReceiveDebtorCount;

    @BindView(R.id.textview_receive_price_total)
    TextView tvReceivePriceTotal;

    @BindView(R.id.frame_receive_deadline)
    FrameLayout frameReceiveDeadline;

    @BindView(R.id.imageView_receive_parent_arrow)
    ImageView imgReceiveParentArrow;

    @BindView(R.id.swipe_home_receive_item)
    SwipeLayout swipeHomeReceive;

    @BindView(R.id.imageview_receive_delete)
    ImageView imgReceiveDelete;

    public ReceiveMoneyParentViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(HomeReceiveParentData parentData){
        swipeHomeReceive.setShowMode(SwipeLayout.ShowMode.PullOut);

        swipeHomeReceive.addDrag(SwipeLayout.DragEdge.Right,
                swipeHomeReceive.findViewById(R.id.swipe_home_receive_menu));

        tvReceiveTitle.setText(parentData.getTitle());
        parentData.sumChecked();
        String countReceiveDebtors = parentData.getCheckedCount() + "명/" + parentData.getChildList().size()+"명";
        tvReceiveDebtorCount.setText(countReceiveDebtors);
        String priceReceiveTotalKRW = NumberFormat.getInstance(Locale.KOREA).format(parentData.getTotalPrice());
        tvReceivePriceTotal.setText(priceReceiveTotalKRW+"원");

        swipeHomeReceive.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {
                Log.v(TAG, "========swipe onStartOpen 클릭=========");
                collapseView();
            }

            @Override
            public void onOpen(SwipeLayout layout) {
                Log.v(TAG, "========swipe onOpen 클릭=========");
                setExpanded(false);
            }

            @Override
            public void onStartClose(SwipeLayout layout) {
                Log.v(TAG, "========swipe onStartClose 클릭=========");
                setExpanded(false);
            }

            @Override
            public void onClose(SwipeLayout layout) {
                Log.v(TAG, "========swipe onClose 클릭=========");
                setExpanded(true);
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                Log.v(TAG, "========swipe onUpdate 클릭=========");
            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                Log.v(TAG, "========swipe onHandRelease 클릭=========");
            }
        });

        swipeHomeReceive.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "========swipe surface 클릭=========");
                if (isExpanded()){
                    collapseView();
                }else{
                    expandView();
                }
            }
        });
    }
}

