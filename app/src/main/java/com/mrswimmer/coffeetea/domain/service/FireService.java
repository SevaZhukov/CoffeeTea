package com.mrswimmer.coffeetea.domain.service;

import android.support.constraint.solver.widgets.Snapshot;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageReference;
import com.kelvinapps.rxfirebase.DataSnapshotMapper;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;
import com.kelvinapps.rxfirebase.RxFirebaseStorage;
import com.mrswimmer.coffeetea.data.User;

public class FireService {
    private DatabaseReference reference;

    public FireService() {
        reference = FirebaseDatabase.getInstance().getReference();
    }

    public void getDB(DbFromFireCallBack callBack) {
        RxFirebaseDatabase.observeSingleValueEvent(reference.child("users").child("first"), User.class)
                .subscribe(callBack::onSuccess, callBack::onError);
    }

    public interface DbFromFireCallBack {
        void onSuccess(User user);
        void onError(Throwable e);
    }
}
