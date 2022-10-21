package com.sample.utils.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdrafi.utils.*
import com.sample.utils.R
import com.sample.utils.databinding.FragmentDateTimeBinding

class DateTimeFragment : Fragment() {

    private lateinit var binding: FragmentDateTimeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDateTimeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        formatTimeDate()
    }

    private fun formatTimeDate() {

        //"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" - > "dd/MM/YYYY"
        val input = "2011-11-02T02:50:12.208Z"
        binding.dateFromUtcTV.text = String.format(
            getString(R.string.date_time_format), input,
            findDateFromUTC(input)
        )

        //"yyyy-MM-dd'T'HH:mm:ss'Z'" - > "dd/MM/YYYY \n hh:mm aa"
        val input2 = "2011-11-02T02:50:12Z"
        binding.timeFromUtcTV.text = String.format(
            getString(R.string.date_time_format), input2,
            findDateAndTimeFromUTC(input2)
        )

        // "yyyy-MM-dd'T'HH:mm:ss'Z'" - > "MMMM dd, yyyy hh:mm aa
        val input3 = "2011-11-02T02:50:12Z"
        binding.date2FromUtcTV.text = String.format(
            getString(R.string.date_time_format), input3,
            findDateTimeInMMddyyFormat(input3)
        )

        //get date from time stamp
        val currentTimeStamp = System.currentTimeMillis()
        binding.getDataFromTimestampTV.text =
            String.format(
                getString(R.string.date_time_format),
                currentTimeStamp,
                getDateFromTimestamp(currentTimeStamp)
            )

        //get time stamp from utc
        binding.getTimeStampFromDateTV.text =
            String.format(
                getString(R.string.date_time_format),
                input2,
                findTimeStampFromDate(input2)
            )

        //get time from time stamp
        // 1628347729-> "HH:mm:ss"
        binding.getTimeFromTimestampTv.text =
            String.format(
                getString(R.string.date_time_format),
                currentTimeStamp,
                getTimeFromTimestamp(currentTimeStamp)
            )
    }

}