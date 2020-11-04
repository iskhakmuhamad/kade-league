package com.example.myleaguemvp.view.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myleaguemvp.R
import com.example.myleaguemvp.Util.DbMatchUtil
import com.example.myleaguemvp.Util.KeyUtil
import com.example.myleaguemvp.adapter.DbMatchAdapter
import com.example.myleaguemvp.database.DbMatchModel
import com.example.myleaguemvp.database.database
import com.example.myleaguemvp.view.activity.FavoriteDetailActivity
import kotlinx.android.synthetic.main.fragment_fav_next.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavNextFragment : Fragment() {
    private val datas = arrayListOf<DbMatchModel>()
    private lateinit var adapter: DbMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fav_next, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getDataFav()
        adapter = DbMatchAdapter(datas) {
            val intent = Intent(activity?.applicationContext, FavoriteDetailActivity::class.java)
            intent.putExtra(KeyUtil.KEYFAVORITE, it)
            startActivity(intent)
        }
        adapter.notifyDataSetChanged()
        rv_favn_match.layoutManager = LinearLayoutManager(activity)
        rv_favn_match.adapter = adapter
    }

    fun getDataFav() {
        datas.clear()
        context?.database?.use {
            val result = select(DbMatchUtil.TABLE_FAVORITE)
            val favorites = result.parseList(classParser<DbMatchModel>())
            for (model in favorites) {
                if (model.tipe == "next") {
                    datas.add(model)
                }
            }
            Log.i("DATA DB NEXT", datas.toString())

            if (datas.isEmpty()) {
                tv_nothing_fav.visibility = View.VISIBLE
            }
        }
    }

    override fun onResume() {
        getDataFav()
        adapter.notifyDataSetChanged()
        super.onResume()
    }

}
