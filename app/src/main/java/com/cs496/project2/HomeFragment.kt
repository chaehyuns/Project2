package com.cs496.project2

import android.graphics.Insets
import android.graphics.Insets.add
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cs496.project2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    //리사이클러 뷰에 담길 더미 데이터 앨범 선언
    private var albumDatas = ArrayList<Album>()
    //lateinit var recyclerview: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        //더미데이터 생성
        albumDatas.apply {
            add(Album("butter", "방탄소년단", R.drawable.img_album_exp))
            add(Album("Lilac", "아이유(IU)", R.drawable.img_album_exp2))
            add(Album("NextLevel", "에스파", R.drawable.img_album_exp3))
            add(Album("Boy with Luv", "방탄소년단", R.drawable.img_album_exp4))
        }


        //만들었던 데이터를 리사이클러뷰에 줌
        val albumRVAdapter = AlbumRVAdapter(albumDatas)
        //어뎁터 연결
        binding.recyclerView.adapter = albumRVAdapter
        //레이아웃매니져 설정
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        //리사이클러뷰의 클릭 리스너 구현
        albumRVAdapter.setMyItemClickListener(object: AlbumRVAdapter.MyItemClickListener{
            //번들을 사용하여 앨범의 데이터를 함께 전달
            override fun onItemClick(album: Album) {
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, AlbumFragment())
                    .commitAllowingStateLoss()
            }
        })

        //recyclerview = content.findViewById<RecyclerView>(R.id.recycleView)
        //recyclerview.layoutManager = LinearLayoutManager(inflater.context)

        return binding.root
    }

}