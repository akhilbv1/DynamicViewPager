package task.interview.com.dynamicviewpager

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    lateinit var sqliteHelper: SqliteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sqliteHelper = SqliteHelper(this@MainActivity)
        val btnInsert: Button = findViewById(R.id.btnInsert)
        val btnGet: Button = findViewById(R.id.btnGet)
        btnInsert.setOnClickListener {
            insertData()
        }

        btnGet.setOnClickListener {
            getData()
        }

        val btnNext: Button = findViewById(R.id.btnNext)
        val etCount: EditText = findViewById(R.id.etCount)
        btnNext.setOnClickListener {
            if (etCount.text.toString().trim().toInt() > 4) {
                Toast.makeText(this@MainActivity, "Please enter only upto 4", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this@MainActivity, DynamicViewPagerActivity::class.java)
                intent.putExtra("Count", etCount.text.toString().trim().toInt())
                startActivity(intent)
            }

        }
    }

    fun insertData() {
        sqliteHelper.insertTickets(prepareData()).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Int> {
                override fun onSuccess(t: Int) {
                    Log.i("result", "" + t);
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    Toast.makeText(this@MainActivity, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            })
    }

    fun getData() {
        sqliteHelper.getTicketsByTicketID("Ticket_1234").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<BOXTable>> {
                override fun onSuccess(t: List<BOXTable>) {
                    Log.i("size", "" + t.size)
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                    Toast.makeText(this@MainActivity, e.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun prepareData(): MutableList<BOXTable> {
        val list: MutableList<BOXTable> = ArrayList()
        for (i in 1..30) {
            list.add(BOXTable(i + 20, i, true, "Ticket_1234"))
        }
        return list
    }
}
