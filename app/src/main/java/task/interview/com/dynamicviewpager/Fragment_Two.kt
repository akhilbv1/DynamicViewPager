package task.interview.com.dynamicviewpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class Fragment_Two : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragments, container, false)
        val tvFragmentNumber: TextView = view.findViewById(R.id.tvFramgmentNumber)
        tvFragmentNumber.text = "Fragment_Two"
        return view
    }
}