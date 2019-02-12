package task.interview.com.dynamicviewpager

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import io.reactivex.Single

class SqliteHelper(context: Context) :
    SQLiteOpenHelper(context, DataBaseConfiguration.DATABASE_NAME, null, DataBaseConfiguration.DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BOXTable.getQuery())
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
      db?.execSQL("DROP TABLE IF EXISTS "+BOXTable.TABLE_NAME)
        onCreate(db)
    }

    fun getTicketsByTicketID(ticketId: String): Single<MutableList<BOXTable>> {
        return Single.create { emitter ->
            try {
                val ticketsList: MutableList<BOXTable> = ArrayList()
                val sqliteDatabase: SQLiteDatabase = this.writableDatabase
                val cursor = sqliteDatabase.query(
                    BOXTable.TABLE_NAME,
                    arrayOf("*"),
                    BOXTable.COLUMN_TICKET_ID + " =? ",
                    arrayOf(ticketId),
                    null,
                    null,
                    null
                )
                if (cursor.moveToNext()) {
                    do {
                        val value = cursor.getInt(cursor.getColumnIndex(BOXTable.COLUMN_BOX_VALUE))
                        val position = cursor.getInt(cursor.getColumnIndex(BOXTable.COLUMN_BOX_POSITION))
                        val ticket_Id = cursor.getString(cursor.getColumnIndex(BOXTable.COLUMN_TICKET_ID))
                        val isSelected = cursor.getInt(cursor.getColumnIndex(BOXTable.COLUMN_BOX_IS_SELECTED)) == 1
                        val obj = BOXTable(value, position, isSelected, ticket_Id)
                        ticketsList.add(obj)
                    } while (cursor.moveToNext())
                }
                emitter.onSuccess(ticketsList)
                cursor.close()
                sqliteDatabase.close()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                emitter.onError(e)
            }

        }
    }

    fun insertTickets(ticketsList: List<BOXTable>): Single<Int> {
        return Single.create { emitter ->
            try {
                val sqliteDatabase: SQLiteDatabase = this.writableDatabase
                for (obj in ticketsList) {
                    val contentValues = ContentValues()
                    contentValues.put(BOXTable.COLUMN_BOX_VALUE, obj.Box_Value)
                    contentValues.put(BOXTable.COLUMN_BOX_POSITION, obj.Box_Position)
                    contentValues.put(BOXTable.COLUMN_TICKET_ID, obj.TICKET_ID)
                    contentValues.put(BOXTable.COLUMN_BOX_IS_SELECTED, obj.Box_IsSelected)
                    val result = sqliteDatabase.insert(BOXTable.TABLE_NAME, null, contentValues)
                    if (result.toInt() != -1)
                        emitter.onSuccess(Utils.RESULT_SUCCESS)
                    else emitter.onSuccess(Utils.RESULT_ERROR)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emitter.onError(e)
            }

        }
    }
}