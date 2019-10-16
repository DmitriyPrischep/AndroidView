package space.dmitry.firstapp;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        myAdapter = new MyDataAdapter(NumberSource.getInstance().getData());

        int spanCount;
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            spanCount = 3;
        } else {
            spanCount = 4;
        }
        recyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));
        recyclerView.setAdapter(myAdapter);
    }

    class MyDataAdapter extends RecyclerView.Adapter<MyViewHolder> {

        List<NumberSource.Number> myData;

        MyDataAdapter(List<NumberSource.Number> myData) {
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
            NumberSource.Number number = this.myData.get(position);
            holder.numberValue.setText(String.valueOf(number.value));
            int colorType = (number.value % 2 == 0) ? R.color.colorRed: R.color.colorBlue;
            holder.numberValue.setTextColor(getResources().getColor(colorType, null));
        }

        @Override
        public int getItemCount() {
            return myData.size();
        }

        public void addElement() {
            myData.add(new NumberSource.Number(myData.size()));
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView numberValue;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            numberValue = itemView.findViewById(R.id.number);

            numberValue.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                NumberSource.Number number = myAdapter.myData.get(pos);
                Toast.makeText(getApplicationContext(), Integer.toString(number.value), Toast.LENGTH_SHORT).show();
            });

            Button button = findViewById(R.id.add_button);
            button.setOnClickListener(v -> {
                myAdapter.addElement();
//                clickListener.onChangeContent();
                Toast.makeText(getApplicationContext(), Integer.toString(myAdapter.getItemCount()), Toast.LENGTH_SHORT).show();
            });

        }
    }
}
