package com.jasonoh.day0630ex91kotlinrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //대량의 데이터를 property로 만들기
    var items = ArrayList<ItemVO>()

    //내가 한것
    var adapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //원래는서버에서 데이터를 읽어와야 하지만 시간상
        // 그냥 대량의 데이터 추가
        items.add( ItemVO("sam", "Hello Kotlin", R.drawable.flag_australia) )
        items.add( ItemVO("robin", "Hard Kotlin", R.drawable.flag_belgium) )
        items.add( ItemVO("tom", "Seek Kotlin", R.drawable.flag_canada) )
        items.add( ItemVO("lee", "Snake Kotlin", R.drawable.flag_france) )
        items.add( ItemVO("rosa", "Good Kotlin", R.drawable.flag_germany) )
        items.add( ItemVO("hong", "Bye Kotlin", R.drawable.flag_ghana) )
        items.add( ItemVO("man", "Opps Kotlin", R.drawable.flag_korea) )

        items.add( ItemVO("sam", "Hello Kotlin", R.drawable.flag_australia) )
        items.add( ItemVO("robin", "Hard Kotlin", R.drawable.flag_belgium) )
        items.add( ItemVO("tom", "Seek Kotlin", R.drawable.flag_canada) )
        items.add( ItemVO("lee", "Snake Kotlin", R.drawable.flag_france) )
        items.add( ItemVO("rosa", "Good Kotlin", R.drawable.flag_germany) )
        items.add( ItemVO("hong", "Bye Kotlin", R.drawable.flag_ghana) )
        items.add( ItemVO("man", "Opps Kotlin", R.drawable.flag_korea) )

        //내가 한것
//        adapter = MyAdapter(this, items)
//
//        recycler.adapter = adapter

        //교수님 께서 알려주시는 방법

        //코틀린의 Recycler 는 setAdapter()를 사용하지 않고
        //RecyclerView 의 프로퍼티로서 adaper를 가지고 있음
        // 그래서 아답터를 굳이 멤버변수로 만들 필요가 없음!!
        recycler.adapter = MyAdapter(this, items)

        //리사이클러뷰에 레이아웃 매니져 설정
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) //세번째 파라미터 : 역순으로? 해줄지 물어볼때

    }

    override fun onResume() {
        super.onResume()

        //리사이클러뷰 갱신하기
        //리사이클러뷰 안에 있는 adapter 가 null인지를 체크한 후에 명령 요청
        // 멤버변수가 null 이 아닌지 체크해주고 실행 하는 키워드 : !! (not이 아니냐고 물어보는 것)
        // !! (좀더 확인해봐야하는 특징임)키워드의 특징 : 혹시 null이면 메소드 실행하지 않는다!!
        // (즉 null Error가 나지 않는다.) (자동으로 Try..Catch를 해주는 의미이다.)
        recycler.adapter!!.notifyDataSetChanged()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.menu_aaa -> Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show()
            R.id.menu_bbb -> Toast.makeText(this, "bbb", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(this, "else", Toast.LENGTH_SHORT).show()

        }

        return super.onOptionsItemSelected(item)
    }

}
