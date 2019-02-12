package task.interview.com.dynamicviewpager

data class BOXTable(var Box_Value:Int,var Box_Position:Int,var Box_IsSelected:Boolean,var TICKET_ID:String){

    companion object {
        val TABLE_NAME = "TICKETS"

        val COLUMN_ID = "ID"

        val COLUMN_BOX_VALUE = "Box_Value"

        val COLUMN_BOX_POSITION = "Box_Position"

        val COLUMN_BOX_IS_SELECTED = "Box_IS_Selected"

        val COLUMN_TICKET_ID = "TICKET_ID"

        fun getQuery():String{
            return (" CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_BOX_VALUE + " INTEGER,"
                    + COLUMN_BOX_POSITION + " INTEGER,"
                    + COLUMN_BOX_IS_SELECTED + " INTEGER,"
                    + COLUMN_TICKET_ID + " TEXT"
                    + ")")
        }
    }
}