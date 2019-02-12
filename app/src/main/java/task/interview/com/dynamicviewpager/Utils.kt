package task.interview.com.dynamicviewpager

import java.util.*


class Utils{

    companion object {
        val RESULT_NOT_DEFINED = 0
        val RESULT_SUCCESS = 1
        val RESULT_ERROR = 2

        fun generateTicketID(): String {
            val OTPCharacters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
            val RANDOM = Random()
            val sb = StringBuilder(6)
            for (i in 0..4) {
                sb.append(OTPCharacters[RANDOM.nextInt(OTPCharacters.length)])
            }
            return sb.toString()
        }
    }



}