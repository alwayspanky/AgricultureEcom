package com.example.agricultureecom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.example.agricultureecom.Adapter.CategoryAdapter;
import com.example.agricultureecom.Adapter.GridView1Adapter;
import com.example.agricultureecom.Api.ApiClient;
import com.example.agricultureecom.Models.CategoryModel;
import com.example.agricultureecom.Models.ProductModel;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.slidertypes.BaseSliderView;
import com.glide.slider.library.slidertypes.TextSliderView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    Toolbar toolbar;
    BottomNavigationView bottomView;
    private SliderLayout sliderLayout;
    GridView gridView, gridView1;
    List<ProductModel> productModelList;
    ApiClient apiClient;
    private RecyclerView catRecycler;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchView = findViewById(R.id.searchView);

        /////////////////Navigation Drawer
        drawer = findViewById(R.id.drawer_layout);
        navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        //////////////Bottom Navigation Drawer
        bottomView = findViewById(R.id.bottom_nav);
        //bottomView.setOnNavigationItemSelectedListener(navListner);
        bottomView.setSelectedItemId(R.id.nav_home);
        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        searchView.clearFocus();
                        break;

                    case R.id.nav_category:
                        break;

                    case R.id.nav_search:
                        searchView.setFocusable(true);
                        searchView.setIconified(false);
                        searchView.requestFocusFromTouch();
                        break;

                    case R.id.nav_message:
                        String contact = "+91 8624981432"; // use country code with your phone number
                        String url = "https://api.whatsapp.com/send?phone=" + contact;
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);

                        break;

                    case R.id.nav_basket:
                        Intent basketIntent =  new Intent(MainActivity.this, CartActivity.class);
                        startActivity(basketIntent);
                        overridePendingTransition(0,0);
                        break;

                }

                return true;
            }
        });

        //////// Slider Layout
        sliderLayout = findViewById(R.id.banner_layout);
        sliderLayout.startAutoCycle();
        setupSlider();

        /////////// Grid Layout
        gridView = findViewById(R.id.product_view);
        gridView1 = findViewById(R.id.sparepart_view);

        apiClient = new ApiClient();
        productModelList = new ArrayList<>();
        productList();
        productList1();

        //Category Recycler
        catRecycler = findViewById(R.id.category_recycler);
        catRecycler();

    }

    private void catRecycler() {

        List<CategoryModel> categoryModels = new ArrayList<>();
        categoryModels.add(new CategoryModel("https://tiimg.tistatic.com/categoryimg/v1/1/Rice-781.jpg","Rice"));
        categoryModels.add(new CategoryModel("https://tiimg.tistatic.com/categoryimg/v1/1/Agricultural-Machines-Tools-11.jpg","Machines"));
        categoryModels.add(new CategoryModel("https://tiimg.tistatic.com/categoryimg/v1/1/Seeds-783.jpg","Seeds"));
        categoryModels.add(new CategoryModel("https://tiimg.tistatic.com/categoryimg/v1/1/Fertilizers-12.jpg","Fertilizers"));
        categoryModels.add(new CategoryModel("https://tiimg.tistatic.com/categoryimg/v1/1/Tea-27.jpg","Tea"));
        categoryModels.add(new CategoryModel("https://tiimg.tistatic.com/categoryimg/v1/1/Agro-Products-Commodities-756.jpg","Agro"));
        categoryModels.add(new CategoryModel("https://tiimg.tistatic.com/categoryimg/v1/1/Pesticides-1371.jpg","Pesticides"));
        categoryModels.add(new CategoryModel("https://tiimg.tistatic.com/categoryimg/v1/1/Insecticides-1370.jpg","Insecticide"));

        catRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
        CategoryAdapter adapter = new CategoryAdapter(this,categoryModels);
        catRecycler.setAdapter(adapter);
        catRecycler.scrollToPosition(0);
        adapter.notifyDataSetChanged();
    }


    private void productList() {


        productModelList.add(new ProductModel("https://tiimg.tistatic.com/new_website1/micro_cate_images/2/b/30/23130.jpg?tr=n-w100","Vermicompost","Fertilizers","It's a good product","Rs.100"));
        productModelList.add(new ProductModel("https://tiimg.tistatic.com/new_website1/micro_cate_images/2/b/62/176262.jpg?tr=n-w100","Bio Fertilizers","Fertilizers","It's a good product","Rs.200"));
        productModelList.add(new ProductModel("https://tiimg.tistatic.com/new_website1/micro_cate_images/2/b/14/165414.jpg?tr=n-w100","Nitrobenzene","Fertilizers","It's a good product","Rs.300"));
        productModelList.add(new ProductModel("https://tiimg.tistatic.com/new_website1/micro_cate_images/2/b/00/23100.jpg?tr=n-w100","Agricultural Fertilizers","Fertilizers","It's a good product","Rs.250"));

        gridView.setAdapter(new GridView1Adapter(productModelList));

//        apiClient.apiInterface.getAllProducts().enqueue(new Callback<List<ProductModel>>() {
//            @Override
//            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
//                if(response.isSuccessful()){
//                    productModelList = response.body();
//                    gridView.setAdapter(new GridView1Adapter(productModelList));
//                }else{
//                    Toast.makeText(MainActivity.this,"insufficient data ",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
//                Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }

    private void productList1() {

        productModelList.add(new ProductModel("https://tiimg.tistatic.com/new_website1/micro_cate_images/2/b/30/23130.jpg?tr=n-w100","Vermicompost","Fertilizers","It's a good product","Rs.100"));
        productModelList.add(new ProductModel("https://tiimg.tistatic.com/new_website1/micro_cate_images/2/b/62/176262.jpg?tr=n-w100","Bio Fertilizers","Fertilizers","It's a good product","Rs.200"));
        productModelList.add(new ProductModel("https://tiimg.tistatic.com/new_website1/micro_cate_images/2/b/14/165414.jpg?tr=n-w100","Nitrobenzene","Fertilizers","It's a good product","Rs.300"));
        productModelList.add(new ProductModel("https://tiimg.tistatic.com/new_website1/micro_cate_images/2/b/00/23100.jpg?tr=n-w100","Agricultural Fertilizers","Fertilizers","It's a good product","Rs.250"));

        gridView1.setAdapter(new GridView1Adapter(productModelList));

//        apiClient.apiInterface.getAllProductsCategory("sparepart").enqueue(new Callback<List<ProductModel>>() {
//            @Override
//            public void onResponse(Call<List<ProductModel>> call, Response<List<ProductModel>> response) {
//                if(response.isSuccessful()){
//                    Log.i("sparepart", String.valueOf(response));
//                    productModelList = response.body();
//                    gridView1.setAdapter(new GridView1Adapter(productModelList));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<ProductModel>> call, Throwable t) {
//
//            }
//        });
    }

    private void setupSlider() {
        ArrayList<String> listUrl = new ArrayList<>();
        ArrayList<String> listName = new ArrayList<>();

        listUrl.add("https://www.theindiaforum.in/sites/default/files/field/image/2019/10/01/Ramkumar%20Radhakrishnan%2C%20Wikimedia-1569929663.jpeg");
        listName.add("Future of Farming");

        listUrl.add("https://www.bentoli.com/wp-content/uploads/2017/07/CommercialFarming-1.jpg");
        listName.add("Seed Planting");

        listUrl.add("https://geneticliteracyproject.org/wp-content/uploads/2018/02/blue-tractor-sprayer-2-1024x768.jpg");
        listName.add("New Way of Farming");

        listUrl.add("https://cmsimages.tribuneindia.com/gallary_content/2019/12/2019_12$largeimg_384681379.jpg");
        listName.add("Area under paddy");

                for(int i = 0; i < listUrl.size(); i++)
                {
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.centerCrop();

                    TextSliderView textSliderView = new TextSliderView(this);
                    textSliderView
                            .image(listUrl.get(i))
                            .description(listName.get(i))
                            .setRequestOption(requestOptions)
                            .setProgressBarVisible(true)
                            .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                @Override
                                public void onSliderClick(BaseSliderView baseSliderView) {
                                    return;
                                }
                            });

                    sliderLayout.addSlider(textSliderView);
                }
                sliderLayout.stopCyclingWhenTouch(false);
            }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_homes:
                Toast.makeText(this,"homes", Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_message:
                String contact = "+91 8624981432"; // use country code with your phone number
                String url = "https://api.whatsapp.com/send?phone=" + contact;
                Intent intentMessage = new Intent(Intent.ACTION_VIEW);
                intentMessage.setData(Uri.parse(url));
                startActivity(intentMessage);
                break;

            case R.id.nav_myOrder:
                Intent intentOrder = new Intent(MainActivity.this,MyOrderActivity.class);
                startActivity(intentOrder);
                break;

            case R.id.nav_wishlist:
                Intent intentWishlist = new Intent(MainActivity.this,WishlistActivity.class);
                startActivity(intentWishlist);
                break;

            case R.id.nav_share:
                Toast.makeText(this,"Share", Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_logout:
                Toast.makeText(this,"Logout", Toast.LENGTH_LONG).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
