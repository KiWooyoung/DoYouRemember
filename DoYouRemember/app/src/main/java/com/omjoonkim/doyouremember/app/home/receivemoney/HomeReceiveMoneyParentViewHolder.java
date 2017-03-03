package com.omjoonkim.doyouremember.app.home.receivemoney;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.model.HomeReceiveParentData;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.animation.Animation.RELATIVE_TO_SELF;


public class HomeReceiveMoneyParentViewHolder extends GroupViewHolder {

    public static final String TAG = HomeReceiveMoneyParentViewHolder.class.getSimpleName();

    @BindView(R.id.textView_receive_title)
    TextView tvReceiveTitle;

    @BindView(R.id.textView_receive_debtor_count)
    TextView tvReceiveDebtorCount;

    @BindView(R.id.textView_receive_price_total)
    TextView tvReceivePriceTotal;

    @BindView(R.id.frame_receive_deadline)
    FrameLayout frameReceiveDeadline;

    @BindView(R.id.imageView_receive_parent_arrow)
    ImageView imgReceiveParentArrow;

    @BindView(R.id.swipe_home_receive_item)
    SwipeLayout swipeHomeReceive;

    @BindView(R.id.imageView_receive_delete)
    ImageView imgReceiveDelete;

    Context mContext;

    public HomeReceiveMoneyParentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mContext = itemView.getContext();
    }

    @Override
    public void expand() {

    }

    @Override
    public void collapse() {

    }

    public void bind(HomeReceiveParentData parentData, final int parentPosition){
        swipeHomeReceive.setShowMode(SwipeLayout.ShowMode.PullOut);

        swipeHomeReceive.addDrag(SwipeLayout.DragEdge.Right,
                swipeHomeReceive.findViewById(R.id.swipe_home_receive_menu));

        tvReceiveTitle.setText(parentData.getTitle());


        int numOfTotalChild = parentData.getItemCount();
        int countChecked = 0;

        for (int i=0; i<numOfTotalChild; i++){
            if (parentData.isChildChecked(i)){
                countChecked += 1;
            }
        }

        String countReceiveDebtors = countChecked + "명/" + numOfTotalChild + "명";
        tvReceiveDebtorCount.setText(countReceiveDebtors);

        String priceReceiveTotalKRW = NumberFormat.getInstance(Locale.KOREA).format(parentData.getTotalPrice());
        tvReceivePriceTotal.setText(priceReceiveTotalKRW+"원");

        swipeHomeReceive.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onGroupClick(parentPosition);
            }
        });
    }

    private void animateExpand() {
        RotateAnimation rotateExpand =
                new RotateAnimation(0, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotateExpand.setDuration(200);
        rotateExpand.setFillAfter(true);
        imgReceiveParentArrow.startAnimation(rotateExpand);
    }

    private void animateCollapse() {
        RotateAnimation rotateCollapse =
                new RotateAnimation(180, 0, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotateCollapse.setDuration(200);
        rotateCollapse.setFillAfter(true);
        imgReceiveParentArrow.setAnimation(rotateCollapse);
    }

}
