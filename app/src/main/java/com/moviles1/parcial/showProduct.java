package com.moviles1.parcial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.moviles1.parcial.Adapter.ProductAdapter;
import com.moviles1.parcial.Entities.Product;
import com.moviles1.parcial.databinding.ActivityShowProductBinding;

import java.util.ArrayList;

public class showProduct extends AppCompatActivity {

    private ActivityShowProductBinding showBinding;
    private FirebaseFirestore db;

    ArrayList<Product> productArrayList;
    ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showBinding = ActivityShowProductBinding.inflate(getLayoutInflater());
        View view = showBinding.getRoot();
        setContentView(view);
        db = FirebaseFirestore.getInstance();
        productArrayList = new ArrayList<>();
        productAdapter = new ProductAdapter(this,productArrayList);
        showBinding.rvlist.setHasFixedSize(true);
        showBinding.rvlist.setLayoutManager(new LinearLayoutManager(this));
        showBinding.rvlist.setAdapter(productAdapter);
        getProducts();
    }

    public void  getProducts(){
        db.collection("products").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!= null){
                    Toast.makeText(getApplicationContext(), "failed to retrive data", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (DocumentChange dc : value.getDocumentChanges())
                {
                    if (dc.getType() == DocumentChange.Type.ADDED)
                    {
                        productArrayList.add(dc.getDocument().toObject(Product.class));
                    }
                }
                productAdapter.notifyDataSetChanged();
            }
        });


    }
}