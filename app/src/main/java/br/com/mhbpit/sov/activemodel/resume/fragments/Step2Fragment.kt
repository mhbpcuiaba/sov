package br.com.mhbpit.sov.activemodel.resume.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.mhbpit.sov.R

class Step2Fragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater?.inflate(R.layout.fragment_resume_step_2, container, false)
        /*
        do stuff,
        add events,
        fill forms
         */

        return view
    }

    companion object {

        fun newInstance(): Step2Fragment {
            val fragment = Step2Fragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}