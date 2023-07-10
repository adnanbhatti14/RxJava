package com.example.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        simpleObserver()
        createObservable()
    }

    private fun createObservable() {
       val observable = Observable.create<String>{
           it.onNext("One")
           it.onError(IllegalArgumentException("Error in Observable"))
           it.onNext("Two")
           it.onComplete()
       }

        observable.subscribe(object: Observer<String>{

            override fun onSubscribe(d: Disposable) {
                Log.e("simpleObserver", "onSubscribe")
            }

            override fun onError(e: Throwable) {
                Log.e("simpleObserver", "onError ${e.message}")
            }

            override fun onComplete() {
                Log.e("simpleObserver", "onComplete")
            }

            override fun onNext(t: String) {
                Log.e("simpleObserver", "onNext $t")
            }

        })
    }

    private fun simpleObserver(){
        val list = listOf<String>("A", "B", "C")
        val observable = Observable.fromIterable(list)
        observable.subscribe(object :Observer<String>{
            override fun onSubscribe(d: Disposable) {
                Log.e("simpleObserver", "onSubscribe")
            }

            override fun onError(e: Throwable) {
                Log.e("simpleObserver", "onError ${e.message}")
            }

            override fun onComplete() {
                Log.e("simpleObserver", "onComplete")
            }

            override fun onNext(t: String) {
                Log.e("simpleObserver", "onNext $t")
            }

        })
    }
}