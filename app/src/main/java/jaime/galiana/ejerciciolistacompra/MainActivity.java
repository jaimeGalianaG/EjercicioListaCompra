package jaime.galiana.ejerciciolistacompra;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import jaime.galiana.ejerciciolistacompra.adapters.ProductAdapter;
import jaime.galiana.ejerciciolistacompra.databinding.ActivityMainBinding;
import jaime.galiana.ejerciciolistacompra.modelos.Product;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ArrayList<Product> productList;
    private ProductAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        productList = new ArrayList<>();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearProduct().show();
            }
        });
    }
    private AlertDialog crearProduct(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle(R.string.titleCreate);
        builder.setCancelable(false);

        View productViewModel = LayoutInflater.from(MainActivity.this).inflate(R.layout.product_view_model, null);
        EditText txtName = productViewModel.findViewById(R.id.txtNameProductViewModel);
        EditText txtQuantity = productViewModel.findViewById(R.id.txtQuantityProductViewModel);
        EditText txtPrice = productViewModel.findViewById(R.id.txtPriceProductViewModel);
        TextView lbTotal = productViewModel.findViewById(R.id.lbTotalProductViewModel);

        builder.setView(productViewModel);
        builder.setNegativeButton(R.string.cancel, null);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    try {
                        int quantity = Integer.parseInt(txtQuantity.getText().toString());
                        float price = Float.parseFloat(txtPrice.getText().toString());
                        float total = quantity * price;

                        lbTotal.setText(String.valueOf(total));
                    }catch (Exception e){

                    }
                }
            };
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (txtName.getText().toString().isEmpty() || txtQuantity.getText().toString().isEmpty() || txtName.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, R.string.missing, Toast.LENGTH_SHORT).show();
                }else{
                    Product product = new Product(
                            txtName.getText().toString(),
                            Integer.parseInt(txtQuantity.getText().toString()),
                            Float.parseFloat(txtPrice.getText().toString())
                    );
                    productList.add(0, product);
                    Toast.makeText(MainActivity.this, product.toString(), Toast.LENGTH_SHORT).show();
                    //adapter.notifyDataSetChanged();
                }
            }
        });

        return builder.create();
    }
}