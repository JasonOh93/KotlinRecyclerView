package com.jasonoh.day0630ex91kotlinrecyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_item.view.*

class MyAdapter constructor(val context: Context, var items: ArrayList<ItemVO>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val itemView : View = LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false)
        val holder : VH = VH(itemView)

        return holder
        //return VH( LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false) )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        var vh : VH? = null
//        if(holder is VH) {
//            // 내가 한 것
//            vh = holder
//            vh.getVH()
//
//        }
        //==================================================================================

        //교수님께서 알려주는 방법
        val vh : VH = holder as VH //코틀린 형변환 연산자 as

        //현재번째 아이템
        val item = items.get(position)
        vh.itemView.tvTitle.setText(item.title)
        //vh.itemView.tvMsg.setText(item.msg)
        //코틀린에서는 set메소드들을 싫어함!!
        //대신 set 해서 설정할 값을 프로퍼티(속성)로 만들어 놓음
        vh.itemView.tvMsg.text = item.msg
        //Glide와 같은 역할을 하는 Picasso 라이브러리 사용
        Picasso.get().load(item.img).into(vh.itemView.iv)

        //코틀린에서는 이 위치에서 itemView의 클릭이벤트 처리를 함
//        vh.itemView.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?) {
//                // 아이템의 상세 정보 페이지 화면으로 이동
//            }
//        } )


    }

    //inner class ViewHolder : itemView안의 뷰들을 관리하는 클래스
    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // 교수님께서 알려주신 것
//        val tvTitle = itemView.tvTitle
//        val tvMsg = itemView.tvMsg
//        val iv = itemView.iv

        init {
            //원래 자바에서는 이 생성자에서 getLayoutPosition() 메소드로
            //클릭한 아이템을 구별했었음..
            // 코틀린에서는 getLayoutPosition() 메소드 대신에
            //이 아답터의 멤버변수로 layoutPosition 이 존재함
            // 효율적으로는 이 곳이 BindViewHolder() 보다 더 좋다!!
            itemView.setOnClickListener(object : View.OnClickListener{
                override fun onClick(v: View?) {
                    // 아이템의 상세 정보 페이지 화면으로 이동
                    //Toast.makeText(context, "" + layoutPosition, Toast.LENGTH_SHORT).show()

                    val intent: Intent = Intent(context, ItemActivity::class.java)
                    intent.putExtra("title", items.get(layoutPosition).title)
                    intent.putExtra("msg", items.get(layoutPosition).msg)
                    intent.putExtra("img", items.get(layoutPosition).img)

                    context.startActivity( intent )
                }
            } )

            //그래서 아이템의 위치가 존재하는 bindViewHolder()에서 클릭 이벤트 처리
        }

        //======================================================================================

        //내가 한것

        fun getVH(){
            itemView.tvTitle.setText( items.get( layoutPosition ).title )
            itemView.tvMsg.setText( items.get( layoutPosition ).msg )
            Glide.with(context).load(items.get( layoutPosition ).img).into(itemView.iv)

            itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    if(v != null) {
                        //Toast.makeText(context, "" + v.tvTitle.text.toString(), Toast.LENGTH_SHORT).show()
                        Toast.makeText(context, "" + layoutPosition, Toast.LENGTH_SHORT).show()
                        val intent: Intent = Intent(context, ItemActivity::class.java)
                        intent.putExtra("Title", v.tvTitle.text.toString())
                        intent.putExtra("Msg", v.tvMsg.text.toString())
                        intent.putExtra("Img", items.get(layoutPosition).img)

                        context.startActivity( intent )
                    }
                }
            })

        }//getVH()

    }


}