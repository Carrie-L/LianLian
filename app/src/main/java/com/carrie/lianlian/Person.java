package com.carrie.lianlian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by new on 2017/7/26.
 */

public class Person extends AppCompatActivity implements Animal {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Person person=new Person();
        person.eat();
        person.sleep(6);

        animal.eat();
        animal.sleep(6);
    }

    Animal animal =new Animal() {
        @Override
        public void eat() {
            Log.i("Person", "Person1 can eat.");
        }

        @Override
        public void sleep(int time) {
            Log.i("Person", "Person1 can sleep and at least " + time + " hour");
        }
    };

    @Override
    public void eat() {
        Log.i("Person", "Person can eat.");
    }

    @Override
    public void sleep(int time) {
        Log.i("Person", "Person can sleep and at least " + time + " hour");
    }



}
