package com.carrie.lldiary;

import android.app.Fragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.carrie.lldiary.activity.DiaryActivity;
import com.carrie.lldiary.inter.OnMenuClickListener;
import com.carrie.lldiary.viewModel.view.CircleView;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by new on 2017/7/14.
 */

public class MainFragment extends Fragment implements OnMenuClickListener {

    private ArrayList<Integer> colors;

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

        colors = new ArrayList<>();
        colors.add(R.color.purple_deep);
        colors.add(R.color.pink_deep);
        colors.add(R.color.pink_light);
        colors.add(R.color.yellow);
        colors.add(R.color.green_light);
        colors.add(R.color.orange);

        CircleView circleButtonView = (CircleView) root.findViewById(R.id.view_circle);
        circleButtonView.setOnMenuClickListener(this);

        testRxJava();


        setAnimal(new Animal() {
            @Override
            public void eat() {
                Log.i("MainFragment", "Person can eat.");
            }

            @Override
            public void sleep(int time) {
                Log.i("MainFragment", "Person can sleep and at least " + time + " hour");
            }
        });

        mAnimal.eat();
        mAnimal.sleep(6);

        return root;
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

    private Animal mAnimal;

    private void setAnimal(Animal animal) {
        mAnimal = animal;
    }

    @Override
    public void onClick(int position) {
        switch (position) {
            case 0:

                break;
            case 1:

                break;

            case 2:
                //日记本
                Intent intent = new Intent(getActivity(), DiaryActivity.class);
                startActivity(intent);
                break;
        }
    }
}
