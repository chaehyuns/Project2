package com.cs496.project2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cs496.project2.databinding.ItemAlbumBinding
import java.util.ArrayList

class AlbumRVAdapter(private val albumList: ArrayList<Album>) : RecyclerView.Adapter<AlbumRVAdapter.ViewHolder>(){

    //reCyclerView에는 클릭리스너가 내장되어있지 않기 때문에추가로 클릭리스너 역할을 하는 인터페이스를 만들어야함
    // 클릭 인터페이스 정의
    interface MyItemClickListener{
        //외부와 내부에 전달받는 객체가 필요
        fun onItemClick(album: Album)
        //fun onRemoveAlbum(position: Int)
    }
    // 리스너 객체를 전달받는 함수랑 리스너 객체를 저장할 변수
    private lateinit var mItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    // 뷰홀더를 생성해줘야 할 때 호출되는 함수 => 아이템 뷰 객체를 만들어서 재활용하기위해 뷰홀더에 던져줍니다.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AlbumRVAdapter.ViewHolder {
        val binding: ItemAlbumBinding = ItemAlbumBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    fun addItem(album: Album){
        albumList.add(album)
        notifyDataSetChanged()
    }
/*
    fun removeItem(position: Int){
        albumList.removeAt(position)
        notifyDataSetChanged()
    }
*/
    // 뷰홀더에 데이터를 바인딩해줘야 할 때마다 호출되는 함수 => 사용자가 위아래로 스크롤할떄마다 엄청나게 많이 호출
    override fun onBindViewHolder(holder: AlbumRVAdapter.ViewHolder, position: Int) {
        holder.bind(albumList[position])

        //recyclerView의 clickEvent
        //onBindViewHolder가 클릭 이벤트값을 가지고 있기 때문에 여기다가 recyclerView의 클릭이벤트를 작성하는 것이 좋음
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(albumList[position]) }
//      holder.binding.itemAlbumTitleTv.setOnClickListener { mItemClickListener.onRemoveAlbum(position) } //삭제됐을 때

        //recyclerView의 clickEvent
        //onBindViewHolder가 클릭 이벤트값을 가지고 있기 때문에 여기다가 recyclerView의 클릭이벤트를 작성하는 것이 좋음
    }

    // 데이터 세트 크기를 알려주는 함수 => 리사이클러뷰가 마지막이 언제인지를 알게 된다.
    override fun getItemCount(): Int = albumList.size

    // 뷰홀더 (아이템뷰가 날라가지 않도록 담아주는 그릇)
    inner class ViewHolder(val binding: ItemAlbumBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(album: Album){
            binding.itemAlbumTitleTv.text = album.title
            binding.itemAlbumSingerTv.text = album.singer
            binding.itemAlbumCoverImgIv.setImageResource(album.coverImg!!)
        }
    }
}