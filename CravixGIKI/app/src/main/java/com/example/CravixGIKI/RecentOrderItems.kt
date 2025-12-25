package com.example.CravixGIKI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.CravixGIKI.Adapter.RecentBuyAdapter
import com.example.CravixGIKI.Model.OrderDetails
import com.example.CravixGIKI.databinding.ActivityRecentOrderItemsBinding

class RecentOrderItems : AppCompatActivity() {
    private val binding: ActivityRecentOrderItemsBinding by lazy {
        ActivityRecentOrderItemsBinding.inflate(layoutInflater)
    }
    private lateinit var allFoodNames: ArrayList<String>
    private lateinit var allFoodImages: ArrayList<String>
    private lateinit var allFoodPrices: ArrayList<String>
    private lateinit var allFoodQuantities: ArrayList<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.backButton.setOnClickListener {
            finish()
        }
        val recentOrderItems = if (android.os.Build.VERSION.SDK_INT >= 33) {
            intent.getSerializableExtra(
                "RecentBuyOrderItem",
                ArrayList::class.java
            ) as? ArrayList<OrderDetails>
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("RecentBuyOrderItem") as? ArrayList<OrderDetails>
        }

//        val recentOrderItems =
//            intent.getParcelableArrayListExtra<OrderDetails>("RecentBuyOrderItem")
        recentOrderItems?.let { orderDetails ->
            if (orderDetails.isNotEmpty()) {
                val recentOrderItem = orderDetails[0]

                allFoodNames = recentOrderItem.foodNames ?: arrayListOf()
                allFoodImages = recentOrderItem.foodImages ?: arrayListOf()
                allFoodPrices = recentOrderItem.foodPrices ?: arrayListOf()
                allFoodQuantities = recentOrderItem.foodQuantities ?: arrayListOf()

            }

        }
        setAdapter()
    }

    private fun setAdapter() {
        val rv = binding.recyclerViewRecentBuy
        rv.layoutManager = LinearLayoutManager(this)
        val adapter =
            RecentBuyAdapter(this, allFoodNames, allFoodImages, allFoodPrices, allFoodQuantities)
        rv.adapter = adapter
    }
}