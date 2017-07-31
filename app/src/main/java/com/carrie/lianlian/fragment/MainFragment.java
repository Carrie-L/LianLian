package com.carrie.lianlian.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carrie.lianlian.R;
import com.carrie.lianlian.activity.CommonActivity;
import com.carrie.lianlian.activity.DiaryActivity;
import com.carrie.lianlian.activity.PlanActivity;
import com.carrie.lianlian.inter.OnMenuClickListener;
import com.carrie.lianlian.view.CircleView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 主界面Fragment
 * Created by Carrie on 2017/7/14.
 */
public class MainFragment extends Fragment implements OnMenuClickListener {

    private Intent intent;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.layout_main, container, false);

        CircleView circleButtonView = (CircleView) root.findViewById(R.id.view_circle);
        circleButtonView.setOnMenuClickListener(this);

        testRxJava();

        return root;
    }

    @Override
    public void onClick(int position, int resColor) {
        switch (position) {
            case 0:
                intent(CommonActivity.class,resColor);
                break;
            case 1:
                intent(CommonActivity.class,resColor);
                break;
            case 2:
                //日记本
                intent = new Intent(getActivity(), DiaryActivity.class);
                intent.putExtra("Color",resColor);
                startActivity(intent);
                break;
            case 3:
                //计划本
                intent(PlanActivity.class,resColor);
                break;
            case 4:
                //恋恋
                intent(PlanActivity.class,resColor);
                break;
            case 5:
                //纪念日
                intent(PlanActivity.class,resColor);
                break;
        }
    }

    private <T> void intent(Class<T> t,int resColor){
        intent = new Intent(getActivity(), t);
        intent.putExtra("Color",resColor);
        startActivity(intent);
    }

    private void testRxJava() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

            }
        });

        String[] names = {"Carrie", "RxJava"};
        Observable.fromArray(names).subscribe(new Consumer<String>() {

            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("Hello " + s + "!");
            }
        });
    }
}
