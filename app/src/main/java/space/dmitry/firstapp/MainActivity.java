package space.dmitry.firstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyDataAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.list);

        myAdapter = new MyDataAdapter(DataSource.getInstance().getData());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }

    class MyDataAdapter extends RecyclerView.Adapter<MyViewHolder> {

        List<DataSource.MyData> myData;

        public MyDataAdapter(List<DataSource.MyData> myData) {
            this.myData = myData;
        }

        @NonNull
        @Override
        // Создание ViewHolder'а на основе разметки list_item
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("ListActivity", "onCreateViewHolder");
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new MyViewHolder(view);
        }

        //Заполняем ViewHolder'ы данными из DataSource
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Log.d("ListActivity", "onBindViewHolder" + position);
            DataSource.MyData data = this.myData.get(position);

            holder.title.setText(data.mTitle);
            Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
            bitmap.eraseColor(data.mColor);

            holder.imageView.setImageBitmap(bitmap);
        }

        @Override
        public int getItemCount() {
            return myData.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            imageView.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                DataSource.MyData myData = myAdapter.myData.get(pos);
                Toast.makeText(getApplicationContext(), myData.mTitle, Toast.LENGTH_SHORT).show();
            });
        }
    }
}
