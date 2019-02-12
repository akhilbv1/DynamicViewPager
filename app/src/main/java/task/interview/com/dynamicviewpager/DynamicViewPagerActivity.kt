package task.interview.com.dynamicviewpager

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.TableLayout

class DynamicViewPagerActivity : AppCompatActivity() {
    lateinit var vp:ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vp)
        val count:Int = intent.getIntExtra("Count",3)
        vp = findViewById(R.id.vp)
        val fragManager:FragmentManager = supportFragmentManager
        val dynamicViewPagerAdapter = DynamicViewPagerAdapter(fragManager,count)
        vp.adapter = dynamicViewPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabMode)
        tabs.setupWithViewPager(vp)
    }

}